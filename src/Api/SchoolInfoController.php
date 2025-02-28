<?php

namespace App\Api;

use App\Entity\School;
use App\Model\Api\Details;
use App\Model\Api\Phases\Phases;
use App\Model\Api\Levels\Levels;
use App\Repository\SchoolGradeRepository;
use App\Repository\SchoolPhaseRepository;
use App\Repository\SchoolLevelRepository;
use Doctrine\ORM\EntityManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
class SchoolInfoController extends AbstractController
{
    private SchoolGradeRepository $schoolGradeRepository;
    private SchoolLevelRepository $schoolLevelRepository;
    private SchoolPhaseRepository $phaseRepository;

    public function __construct(SchoolGradeRepository $schoolGradeRepository, SchoolLevelRepository $schoolLevelRepository, SchoolPhaseRepository $phaseRepository)
    {
        $this->schoolGradeRepository = $schoolGradeRepository;
        $this->schoolLevelRepository = $schoolLevelRepository;
        $this->phaseRepository = $phaseRepository;
    }
    /**
     * @Route("/api/v2/grades", name="api_get_grades", methods={"GET"})
     */
    public function getGrades(string $internalName)
    {
        return new JsonResponse($this->schoolGradeRepository->findOneByInternalName($internalName));
    }

    /**
     * @Route("/api/v2/levels", name="api_get_levels", methods={"GET"})
     */
    public function getAllLevels()
    {
        $levels = $this->schoolLevelRepository->findAll();

        $data = [];
        foreach ($levels as $level) {
            $details = new Details();
            $details->id = $level->getId();
            $details->label = $level->getName();

            $data[] = $details;
        }

        $gradesResponse = new Levels();
        $gradesResponse->levels = $data;

        return new JsonResponse($gradesResponse);
    }

    /**
     * @Route("/api/v2/phases", name="api_get_phases", methods={"GET"})
     */
    public function getPhase()
    {
        $phases = $this->phaseRepository->findAll();

        $data = [];
        foreach ($phases as $phase) {
            $details = new Details();
            $details->id = $phase->getId();
            $details->label = $phase->getName();

            $data[] = $details;
        }

        $phasesResponse = new Phases();
        $phasesResponse->phases = $data;

        return new JsonResponse($phasesResponse);
    }
}