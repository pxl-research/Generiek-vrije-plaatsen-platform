<?php

namespace App\Api;

use EasyCorp\Bundle\EasyAdminBundle\Controller\AbstractDashboardController;
use EasyCorp\Bundle\EasyAdminBundle\Router\AdminUrlGenerator;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use EasyCorp\Bundle\EasyAdminBundle\Config\Dashboard;
use App\Model\User;
use App\Controller\Admin\SchoolCrudController;

class DashboardController extends AbstractDashboardController
{
    
    //  /**
    //  * @Route("/api/v2/dashboard, name="export_v2_dashboard", methods={"GET"})
    //  */
    // public function GetDashboard()
    // {
    //     $routeBuilder = $this->get(AdminUrlGenerator::class);

    //     return new JsonResponse($routeBuilder->setController(SchoolCrudController::class)->generateUrl());
    // }
    // /**
    //  * @Route("/api/v2/configuredashboard, name="export_v2_configured_dashboard", methods={"GET"})
    //  */
    // public function configureDashboard()
    // {
    //     return new JsonResponse( Dashboard::new()
    //         ->setTitle('Naar School In Vlaanderen'));
    // }

    //    /**
    //  * @Route("/api/v2/configureusermenu, name="export_v2_configureusermenu", methods={"GET"})
    //  */
    // public function configureUserMenu(UserInterface $user)
    // {
    //     if ($user instanceof User) {
    //         return parent::configureUserMenu($user)
    //             ->setName($user->getEmail());
    //     }

    //     return new JsonResponse(parent::configureUserMenu($user));
    // }
}