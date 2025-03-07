<?php

namespace App\Api;

use App\Entity\School;
use App\Entity\SchoolEducation;
use App\Entity\SchoolYear;
use App\Form\School\SchoolEducationsCloneType;
use App\Form\School\SchoolEducationsType;
use App\Form\School\SchoolNumbersOverviewType;
use App\Model\Form\SchoolEducationsData;
use App\Model\Form\SchoolNumbersData;
use App\Model\Role;
use App\Model\User;
use App\Repository\SchoolEducationRepository;
use App\Repository\SchoolLevelRepository;
use App\Repository\SchoolOfficialEducationRepository;
use App\Repository\SchoolRepository;
use App\Repository\SchoolYearRepository;
use App\Service\Educations\EducationCloner;
use App\Service\Educations\EducationNumbersCsvGenerator;
use App\Service\Educations\EducationsFormDataTransformer;
use App\Service\Educations\EducationsService;
use Doctrine\ORM\QueryBuilder;
use EasyCorp\Bundle\EasyAdminBundle\Collection\FieldCollection;
use EasyCorp\Bundle\EasyAdminBundle\Collection\FilterCollection;
use EasyCorp\Bundle\EasyAdminBundle\Config\Action;
use EasyCorp\Bundle\EasyAdminBundle\Config\Actions;
use EasyCorp\Bundle\EasyAdminBundle\Config\Crud;
use EasyCorp\Bundle\EasyAdminBundle\Config\MenuItem;
use EasyCorp\Bundle\EasyAdminBundle\Config\Option\EA;
use EasyCorp\Bundle\EasyAdminBundle\Context\AdminContext;
use EasyCorp\Bundle\EasyAdminBundle\Controller\AbstractCrudController;
use EasyCorp\Bundle\EasyAdminBundle\Dto\EntityDto;
use EasyCorp\Bundle\EasyAdminBundle\Dto\MenuItemDto;
use EasyCorp\Bundle\EasyAdminBundle\Dto\SearchDto;
use EasyCorp\Bundle\EasyAdminBundle\Exception\ForbiddenActionException;
use EasyCorp\Bundle\EasyAdminBundle\Field\ChoiceField;
use EasyCorp\Bundle\EasyAdminBundle\Field\CollectionField;
use EasyCorp\Bundle\EasyAdminBundle\Field\EmailField;
use EasyCorp\Bundle\EasyAdminBundle\Field\TextField;
use EasyCorp\Bundle\EasyAdminBundle\Router\AdminUrlGenerator;
use Symfony\Component\HttpFoundation\HeaderUtils;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Symfony\Contracts\Translation\TranslatorInterface;

use Symfony\Component\Routing\Annotation\Route;

class SchoolCrudController extends AbstractCrudController
{
    
    public const YEAR_ID = 'yearId';

    public static function getEntityFqcn(): string
    {
        return School::class;
    }

    
     /**
     * @Route("/api/v2/editeducations", name="export_v2_editeducations", methods={"PUT"})
     */
    public function editEducations(AdminContext $context)
    {
        $school = $context->getEntity()->getInstance();
        $year = $this->get(SchoolYearRepository::class)->find($context->getRequest()->get(self::YEAR_ID));

        if (!$year instanceof SchoolYear || !$school instanceof School || !$this->isGranted(Role::ROLE_EDIT_SCHOOL_EDUCATIONS)) {
            throw new ForbiddenActionException($context);
        }

        $transformer = $this->get(EducationsFormDataTransformer::class);
        $educationsData = $transformer->transform($school, $year);
        $form = $this->createForm(SchoolEducationsType::class, $educationsData, [
            'types' => $school->getSchoolLevelTypes(),
            'levels' => $school->getSchoolLevelLevels(),
            'year' => $year,
        ]);

        $educationCount = 0;

        array_walk_recursive($educationsData, function ($education) use (&$educationCount) {
            if ($education instanceof SchoolEducation) {
                $educationCount = $educationCount + 1;
            }
        });

        $schoolYears = array_filter($this->get(SchoolYearRepository::class)->findActiveBackendSchoolYears(), function ($schoolYear) use ($year) {
            return $year !== $schoolYear;
        });

        $cloneForm = $this->createForm(SchoolEducationsCloneType::class, [], [
             'schoolYears' => $schoolYears,
        ]);

        $form->handleRequest($context->getRequest());

        if ($form->isSubmitted()) {
            if ($form->isValid()) {
                $educationsService = $this->get(EducationsService::class);
                $educationsService->saveEducations($school, $year, $form->getData());

                $this->addFlash('success', 'app.admin.schools.educations.success');

                return new RedirectResponse($context->getRequest()->getUri());
            }

            $this->addFlash('danger', 'app.admin.schools.educations.error');
        }

        $cloneForm->handleRequest($context->getRequest());

        if ($cloneForm->isSubmitted()) {
            if ($cloneForm->isValid()) {
                $educationsService = $this->get(EducationsService::class);
                $cloneService = $this->get(EducationCloner::class);
                $schoolEducationRepository = $this->get(SchoolEducationRepository::class);

                $educationsService->saveEducations($school, $year, new SchoolEducationsData());

                $educations = $school->getEducationsForYear($cloneForm->get('year')->getData());

                foreach ($educations as $education) {
                    $newEducation = $cloneService->clone($education, $year);
                    $schoolEducationRepository->persist($newEducation);
                }

                $schoolEducationRepository->flush();

                $this->addFlash('success', 'app.admin.schools.educations.success');

                return new RedirectResponse($context->getRequest()->getUri());
            }
        }

        return new JsonResponse([
            'school' => $school,
            'schoolYear' => $year,
            'form' => $form->createView(),
            'cloneForm' => $cloneForm->createView(),
            'educationCount' => $educationCount,
            'schoolYearLinks' => $this->getSchoolYearLinks($school, 'editEducations'),
            'importUrl' => \count($educationsData->getEducationsCollections()) <= 0 ? $this->get(AdminUrlGenerator::class)
                ->set(EA::CRUD_ACTION, 'importEducations')
                ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
                ->set(EA::ENTITY_ID, $school->getId())
                ->set(self::YEAR_ID, $year->getId())
                ->generateUrl() : false,
            'subMenu' => $this->getSubMenu($context, $school),
            'exportUrl' => $this->get(AdminUrlGenerator::class)
                ->set(EA::CRUD_ACTION, 'exportNumbers')
                ->set(EA::CRUD_CONTROLLER_FQCN, __CLASS__)
                ->set(EA::ENTITY_ID, $school->getId())
                ->set(self::YEAR_ID, $context->getRequest()->get(self::YEAR_ID))
                ->set(EA::SUBMENU_INDEX, 4)
                ->generateUrl(),
        ]);
    }

    
     /**
     * @Route("/api/v2/importeducations", name="export_v2_importeducations", methods={"GET"})
     */
    public function importEducations(AdminContext $context)
    {
        $school = $context->getEntity()->getInstance();
        $year = $this->get(SchoolYearRepository::class)->find($context->getRequest()->get(self::YEAR_ID));
        if (!$year instanceof SchoolYear || !$school instanceof School || !$this->isGranted(Role::ROLE_IMPORT_SCHOOL_EDUCATIONS)) {
            throw new ForbiddenActionException($context);
        }

        $redirectUrl = $this->get(AdminUrlGenerator::class)
            ->set(EA::CRUD_ACTION, 'editEducations')
            ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
            ->set(EA::ENTITY_ID, $school->getId())
            ->set(self::YEAR_ID, $year->getId())
            ->set(EA::SUBMENU_INDEX, 1)
            ->generateUrl();

        $levels = $this->get(SchoolLevelRepository::class)->findByLevelsTypes($school->getSchoolLevelLevels(), $school->getSchoolLevelTypes());
        $officialEducations = $this->get(SchoolOfficialEducationRepository::class)->findByLevelsEstablishmentNumbers($levels, $school->getEstablishmentNumbers());

        if (\count($officialEducations) > 0) {
            foreach ($officialEducations as $officialEducation) {
                $education = new SchoolEducation();
                $education->setYear($year);
                $education->setSchool($school);
                $education->setName($officialEducation->getName());
                $education->setLevel($officialEducation->getLevel());
                $education->setFormType($officialEducation->getFormType());

                $school->addEducation($education);
            }

            $this->get(SchoolRepository::class)->save($school);

            return $this->redirect($redirectUrl);
        }

        $this->addFlash('danger', $this->get(TranslatorInterface::class)->trans('app.admin.schools.educations.import.error', [
            '%establishmentNumber%' => implode(', ', $school->getEstablishmentNumbers()),
        ]));

        return new JsonResponse($redirectUrl);
    }


     /**
     * @Route("/api/v2/exportnumbers", name="export_v2_exportnumbers", methods={"POST"})
     */
    public function exportNumbers(AdminContext $context)
    {
        $school = $context->getEntity()->getInstance();
        $year = $this->get(SchoolYearRepository::class)->find($context->getRequest()->get(self::YEAR_ID));
        if (!$year instanceof SchoolYear || !$school instanceof School || !$this->isGranted(Role::ROLE_EXPORT_SCHOOL_EDUCATION_NUMBERS)) {
            throw new ForbiddenActionException($context);
        }

        $transformer = $this->get(EducationsFormDataTransformer::class);
        $schoolNumbersData = $transformer->transform($school, $year, SchoolNumbersData::class);

        $response = new Response($this->get(EducationNumbersCsvGenerator::class)->generate($schoolNumbersData));
        $disposition = HeaderUtils::makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            sprintf('%s %s-%s %s.csv', $school->getName() ?? '', $year->getStartYear(), $year->getEndYear(), (new \DateTime())->format('d-m-Y H:i:s')),
        );

        $response->headers->set('Content-Disposition', $disposition);
        $response->headers->set('Content-Type', 'text/csv');

        return new JsonResponse($response);
    }


     /**
     * @Route("/api/v2/editnumbers", name="export_v2_editnumbers", methods={"PUT"})
     */
    public function editNumbers(AdminContext $context)
    {
        $request = $context->getRequest();
        $school = $context->getEntity()->getInstance();
        $year = $this->get(SchoolYearRepository::class)->find($request->get(self::YEAR_ID));

        if (!$year instanceof SchoolYear || !$school instanceof School || !$this->isGranted(Role::ROLE_EDIT_SCHOOL_EDUCATION_NUMBERS)) {
            throw new ForbiddenActionException($context);
        }

        $transformer = $this->get(EducationsFormDataTransformer::class);
        $form = $this->createForm(SchoolNumbersOverviewType::class, $transformer->transform($school, $year, SchoolNumbersData::class), [
            'types' => $school->getSchoolLevelTypes(),
            'levels' => $school->getSchoolLevelLevels(),
            'year' => $year,
        ]);

        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            if ($form->isValid()) {
                $educationsService = $this->get(EducationsService::class);
                $educationsService->saveNumbers($form->getData());

                $this->addFlash('success', 'app.admin.schools.numbers.success');

                return new RedirectResponse($request->getUri());
            }

            $this->addFlash('danger', 'app.admin.schools.numbers.error');
        }

        return new JsonResponse( [
            'school' => $school,
            'schoolYear' => $year,
            'schoolYearLinks' => $this->getSchoolYearLinks($school, 'editNumbers'),
            'form' => $form->createView(),
            'subMenu' => $this->getSubMenu($context, $school),
            'exportUrl' => $this->get(AdminUrlGenerator::class)
                ->set(EA::CRUD_ACTION, 'exportNumbers')
                ->set(EA::CRUD_CONTROLLER_FQCN, __CLASS__)
                ->set(EA::ENTITY_ID, $school->getId())
                ->set(self::YEAR_ID, $context->getRequest()->get(self::YEAR_ID))
                ->set(EA::SUBMENU_INDEX, 4)
                ->generateUrl(),
        ]);
    }

/**
     * @return array<array-key, MenuItemDto>
     */
    public function getSubMenu(AdminContext $context, School $school): array
    {
        $items = [];
        $items[] = MenuItem::linkToUrl('app.admin.menu.edit_educations', 'fas fa-list', $this->get(AdminUrlGenerator::class)
            ->set(EA::CRUD_ACTION, 'editEducations')
            ->set(EA::CRUD_CONTROLLER_FQCN, __CLASS__)
            ->set(EA::ENTITY_ID, $school->getId())
            ->set(self::YEAR_ID, $context->getRequest()->get(self::YEAR_ID))
            ->set(EA::SUBMENU_INDEX, 1)
            ->generateUrl()
        )->setPermission(Role::ROLE_EDIT_SCHOOL_EDUCATIONS)->getAsDto();

        $items[] = MenuItem::linkToUrl('app.admin.menu.edit_numbers', 'fas fa-table', $this->get(AdminUrlGenerator::class)
            ->set(EA::CRUD_ACTION, 'editNumbers')
            ->set(EA::CRUD_CONTROLLER_FQCN, __CLASS__)
            ->set(EA::ENTITY_ID, $school->getId())
            ->set(self::YEAR_ID, $context->getRequest()->get(self::YEAR_ID))
            ->set(EA::SUBMENU_INDEX, 2)
            ->generateUrl()
        )->setPermission(Role::ROLE_EDIT_SCHOOL_EDUCATION_NUMBERS)->getAsDto();

        $items[] = MenuItem::linkToUrl('app.admin.menu.edit_school', 'fas fa-edit', $this->get(AdminUrlGenerator::class)
            ->set(EA::CRUD_ACTION, Crud::PAGE_EDIT)
            ->set(EA::CRUD_CONTROLLER_FQCN, __CLASS__)
            ->set(EA::ENTITY_ID, $school->getId())
            ->set(EA::SUBMENU_INDEX, 3)
            ->generateUrl()
        )->setPermission(Role::ROLE_EDIT_SCHOOL)->getAsDto();

        return $items;
    }


    public function configureActions(Actions $actions): Actions
    {
        $actions = parent::configureActions($actions);

        $activeYears = $this->get(SchoolYearRepository::class)->findActiveBackendSchoolYears();

        foreach ($activeYears as $activeYear) {
            $dividerAction = Action::new('divider' . $activeYear->getId(), '', '')
                ->linkToUrl('#')->setTemplatePath('Admin/School/dropdown_divider.html.twig');

            $actions->add(Crud::PAGE_INDEX, $dividerAction);

            $label = $this->get(TranslatorInterface::class)->trans('app.admin.schools.index.export_numbers', [
                '%startYear%' => $activeYear->getStartYear(),
                '%endYear%' => $activeYear->getEndYear(),
            ]);

            $exportNumbersAction = Action::new('exportNumbers' . $activeYear->getId(), $label, 'fa fa-download')
                ->linkToUrl(function (School $school) use ($activeYear): string {
                    return $this->get(AdminUrlGenerator::class)
                        ->set(EA::CRUD_ACTION, 'exportNumbers')
                        ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
                        ->set(EA::ENTITY_ID, $school->getId())
                        ->set(self::YEAR_ID, $activeYear->getId())
                        ->set(EA::SUBMENU_INDEX, 3)
                        ->generateUrl();
                });

            $actions->add(Crud::PAGE_INDEX, $exportNumbersAction);
            $actions->setPermission('exportNumbers' . $activeYear->getId(), Role::ROLE_EXPORT_SCHOOL_EDUCATION_NUMBERS);

            $label = $this->get(TranslatorInterface::class)->trans('app.admin.schools.index.edit_educations', [
                '%startYear%' => $activeYear->getStartYear(),
                '%endYear%' => $activeYear->getEndYear(),
            ]);

            $editEducationsAction = Action::new('editEducations' . $activeYear->getId(), $label, 'fa fa-list')
                ->linkToUrl(function (School $school) use ($activeYear): string {
                    return $this->get(AdminUrlGenerator::class)
                        ->set(EA::CRUD_ACTION, 'editEducations')
                        ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
                        ->set(EA::ENTITY_ID, $school->getId())
                        ->set(self::YEAR_ID, $activeYear->getId())
                        ->set(EA::SUBMENU_INDEX, 1)
                        ->generateUrl();
                });

            $actions->add(Crud::PAGE_INDEX, $editEducationsAction);
            $actions->setPermission('editEducations' . $activeYear->getId(), Role::ROLE_EDIT_SCHOOL_EDUCATION_NUMBERS);

            $label = $this->get(TranslatorInterface::class)->trans('app.admin.schools.index.edit_numbers', [
                '%startYear%' => $activeYear->getStartYear(),
                '%endYear%' => $activeYear->getEndYear(),
            ]);

            $editNumbersAction = Action::new('editNumbers' . $activeYear->getId(), $label, 'fa fa-table')
                ->linkToUrl(function (School $school) use ($activeYear): string {
                    return $this->get(AdminUrlGenerator::class)
                        ->set(EA::CRUD_ACTION, 'editNumbers')
                        ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
                        ->set(EA::ENTITY_ID, $school->getId())
                        ->set(self::YEAR_ID, $activeYear->getId())
                        ->set(EA::SUBMENU_INDEX, 2)
                        ->generateUrl();
                });

            $actions->setPermission('editNumbers' . $activeYear->getId(), Role::ROLE_EDIT_SCHOOL_EDUCATION_NUMBERS);
            $actions->add(Crud::PAGE_INDEX, $editNumbersAction);

            $translation = $activeYear->isCurrent() ? 'app.admin.schools.index.current_year' : 'app.admin.schools.index.year';
            $label = $this->get(TranslatorInterface::class)->trans($translation, [
                '%startYear%' => $activeYear->getStartYear(),
                '%endYear%' => $activeYear->getEndYear(),
            ]);

            $schoolYearAction = Action::new('schoolYear' . $activeYear->getId(), $label, '')
                ->linkToUrl('#')->setTemplatePath('Admin/School/dropdown_header.html.twig');

            $actions->add(Crud::PAGE_INDEX, $schoolYearAction);
        }

        $deleteAction = $actions->getAsDto(Crud::PAGE_INDEX)->getAction(Crud::PAGE_INDEX, Action::DELETE);
        if (null !== $deleteAction) {
            $deleteAction->setLabel('app.admin.schools.index.delete');
        }

        $editAction = $actions->getAsDto(Crud::PAGE_INDEX)->getAction(Crud::PAGE_INDEX, Action::EDIT);
        if (null !== $editAction) {
            $editAction->setLabel('app.admin.schools.index.edit');
        }

        $saveAction = $actions->getAsDto(Crud::PAGE_NEW)->getAction(Crud::PAGE_NEW, Action::SAVE_AND_RETURN);
        if (null !== $saveAction) {
            $saveAction->setLabel('app.admin.schools.edit.save');
        }

        $newAction = $actions->getAsDto(Crud::PAGE_INDEX)->getAction(Crud::PAGE_INDEX, Action::NEW);
        if (null !== $newAction) {
            $newAction->setLabel('app.admin.schools.create.save');
        }

        $actions->disable(Action::SAVE_AND_CONTINUE, Action::SAVE_AND_ADD_ANOTHER);

        $actions->setPermission(Action::NEW, Role::ROLE_ADD_SCHOOL);
        $actions->setPermission(Action::DELETE, Role::ROLE_DELETE_SCHOOL);
        $actions->setPermission(Action::EDIT, Role::ROLE_EDIT_SCHOOL);

        return $actions;
    }

     /**
     * @return array<array-key, array{url: string, year: SchoolYear}>
     */
    private function getSchoolYearLinks(School $school, string $crudAction): array
    {
        $schoolYears = $this->get(SchoolYearRepository::class)->findActiveBackendSchoolYears();

        $links = [];
        foreach ($schoolYears as $schoolYear) {
            $links[] = [
                'url' => $this->get(AdminUrlGenerator::class)
                    ->set(EA::CRUD_ACTION, $crudAction)
                    ->set(EA::CRUD_CONTROLLER_FQCN, self::class)
                    ->set(EA::ENTITY_ID, $school->getId())
                    ->set(self::YEAR_ID, $schoolYear->getId())
                    ->set(EA::SUBMENU_INDEX, 2)
                    ->generateUrl(),
                'year' => $schoolYear,
            ];
        }

        return $links;
    }
}