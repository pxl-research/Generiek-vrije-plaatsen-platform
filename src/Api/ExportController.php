<?php

namespace App\Api;

use App\Repository\SchoolYearRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Model\Export\Kiesjeschool;
use App\Service\Exports\KiesjeschoolCsvGenerator;
use App\Model\Role;
use Symfony\Component\HttpFoundation\HeaderUtils;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use App\Service\Exports\KiesjeschoolDataTransformer;
use App\Repository\SchoolRepository;
use App\Service\Transformer\UserTransformer;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

class ExportController extends AbstractController
{
    private SchoolYearRepository $schoolYearRepository;
    private UserTransformer $transformer;
    public const YEAR_ID = 'yearId';

     /**
     * @ORM\OneToMany(targetEntity=SchoolYear::class, mappedBy="institution")
     */
    private Collection $schoolYears;
    public function __construct(
        SchoolYearRepository $schoolYearRepository,
        UserTransformer $transformer
    ) 
    {
        $this->schoolYearRepository = $schoolYearRepository;
        $this->transformer = $transformer;
        $this->schoolYears = new ArrayCollection();
    }
    
       /**
     * @Route("/api/v2/schoolyears", name="export_v2_schoolyears", methods={"GET"})
     */
    public function getActiveSchoolYears(): JsonResponse
    {
        $schoolYears = $this->schoolYearRepository->findAll();
        $data = [];

        foreach ($schoolYears as $year) {
            $data[] = [
                'startyear' => $year->getStartYear(),
                'endyear' => $year->getEndYear()
            ];
        }

        return new JsonResponse($data);
    }

     /**
     * @Route("/api/v2/exports/kiesjeschool", name="api_v2_kiesjeschool", methods={"POST"})
     */
    public function kiesjeschool(Request $request)
    {
        $this->denyAccessUnlessGranted(Role::ROLE_SUPER_ADMIN);

        $schools = $this->get(SchoolRepository::class)->findAll();
        $year = $this->get(SchoolYearRepository::class)->find($request->get(self::YEAR_ID));
        $transformer = $this->get(KiesjeschoolDataTransformer::class);

        $data = [];
        foreach ($schools as $school) {
            $schoolData = $transformer->transform($school, $year);
            if (!$schoolData instanceof Kiesjeschool) {
                continue;
            }
            $data[] = $transformer->transform($school, $year);
        }

        $response = new Response($this->get(KiesjeschoolCsvGenerator::class)->generate($data));

        $disposition = HeaderUtils::makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            sprintf(
                'NSIV %s-%s %s.csv',
                $year->getStartYear(),
                $year->getEndYear(),
                (new \DateTime())->format('d-m-Y H:i:s')
            ),
        );

        $response->headers->set('Content-Disposition', $disposition);
        $response->headers->set('Content-Type', 'text/csv');

        return new jsonresponse($response);
    }

}