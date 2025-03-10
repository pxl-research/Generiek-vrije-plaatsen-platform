<?php

namespace App\Api;

use App\Entity\School;
use App\Model\Form\SchoolNumbersData;
use App\Model\Api\Request\PostSchoolNumbersRequest;
use App\Repository\SchoolRepository;
use App\Repository\SchoolYearRepository;
use App\Service\Api\PostSchoolNumbersService;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use App\Service\Api\Transformer\ApiViewTransformer;
use App\Service\Educations\EducationsFormDataTransformer;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\SchoolYear;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Validator\Validator\ValidatorInterface;
use App\Service\Transformer\UserTransformer;

class SchoolNumbersController extends AbstractController {

    private SerializerInterface $serializer;
    private ValidatorInterface $validator;
    private SchoolRepository $schoolRepository;
    private SchoolYearRepository $schoolYearRepository;
    private EducationsFormDataTransformer $dataTransformer;
    private ApiViewTransformer $apiViewTransformer;
    private PostSchoolNumbersService $postSchoolNumbersService;
    private UserTransformer $transformer;

    public function __construct(
        SerializerInterface $serializer,
        ValidatorInterface $validator,
        SchoolRepository $schoolRepository,
        SchoolYearRepository $schoolYearRepository,
        EducationsFormDataTransformer $dataTransformer,
        ApiViewTransformer $apiViewTransformer,
        PostSchoolNumbersService $postSchoolNumbersService,
        UserTransformer $transformer
    ) {
        $this->serializer = $serializer;
        $this->validator = $validator;
        $this->schoolRepository = $schoolRepository;
        $this->schoolYearRepository = $schoolYearRepository;
        $this->dataTransformer = $dataTransformer;
        $this->apiViewTransformer = $apiViewTransformer;
        $this->postSchoolNumbersService = $postSchoolNumbersService;
        $this->transformer = $transformer;
    }

    /**
     * @Route("/api/v2/schools", name="api_get_school_numbers_acs", methods={"GET"})
     */
    public function getAllSchools() {
        $schools = $this->schoolRepository->findAll();
        $allschools = [];

        foreach ($schools as $school) {
            $allschools[] = $this->transformer->transformModelToArray($school);
        }

        return new JsonResponse($allschools);
    }

    /**
     * @Route("/api/v2/schools/{schoolId}", name="api_get_school_by_id", methods={"GET"})
     */
    public function getSchoolById(int $schoolId) {
        $school = $this->schoolRepository->find($schoolId);
        return new JsonResponse($this->transformer->transformModelToArray($school));
    }

      /**
     * @Route("/api/v2/schools/{establishmentNumber}/{startYear}/{endYear}", name="api_get_school_by_EstablishmentNumber_and_StartAndEndYear", methods={"GET"})
     */
    public function getSchoolByEstablishmentNumberAndStartAndEndYear(string $establishmentNumber, int $startYear, int $endYear) {

        $schools = $this->schoolRepository->findByEstablishmentNumber($establishmentNumber);
        $year = $this->schoolYearRepository->findYearByStartAndEndYear($startYear, $endYear);

        if (!$year instanceof SchoolYear) {
            return new JsonResponse('Year not found.', 404);
        }

        if (0 === \count($schools)) {
            return new JsonResponse('School not found.', 404);
        }

        $data = [];
        foreach ($schools as $school) {
            $schoolNumbersData = $this->dataTransformer->transform($school, $year, SchoolNumbersData::class);
            $data[] = $this->apiViewTransformer->getView($school, $year, $schoolNumbersData);
        }

        return new JsonResponse($data);
    }

    /**
    * @Route("/api/v2/schools", name="api_create_school", methods={"POST"})
    */
   public function createSchoolRequest(Request $request) {

    $input = $this->serializer->deserialize($request->getContent(), PostSchoolNumbersRequest::class, 'json');
    $errors = $this->validator->validate($input);
    if ($errors->count()) {
        $result = [];
        foreach ($errors as $error) {
            $result[$error->getPropertyPath()] = $error->getMessage();
        }

        return new JsonResponse($result);
    }

    try {
        $this->postSchoolNumbersService->handleRequest($input);
    } catch (\Exception $exception) {
        if ($exception instanceof \InvalidArgumentException) {
            return new JsonResponse($exception->getMessage(), 404);
        }

        return new JsonResponse('Something went wrong.', 404);
    }

    return new JsonResponse('Success');
    }
}
