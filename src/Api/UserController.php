<?php


namespace App\Api;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use App\Service\Auth0\UserRepository;
use App\Model\Request\CreateUserRequest;
use App\Model\User;
use App\Service\Transformer\UserTransformer;

class UserController extends AbstractController
{
    private UserRepository $userRepository;
    private UserTransformer $transformer;
    public function __construct(
        UserRepository $userRepository,
        UserTransformer $transformer
    )
    {
        $this->userRepository = $userRepository;
        $this->transformer = $transformer;
    }

  /**
     * @Route("/api/v2/users", name="api_get_users", methods={"GET"})
     */
    public function GetAllUser()
    {
        return new JsonResponse($this->userRepository->findAll());
    }

    /**
     * @Route("/api/v2/users/{Id}", name="api_get_users_by_id", methods={"GET"})
     */
    public function GetUserById(string $Id)
    {
        return new JsonResponse($this->userRepository->findById($Id));
    }

    /**
     * @Route("/api/v2/users/create", name="api_create_user", methods={"POST"})
     */
    public function CreateUser(User $newUser)
    {

        $createUserRequest = $this->transformer->transformModelToCreateRequest($newUser);

        return new JsonResponse($this->userRepository->createUser($createUserRequest));
    }
    
    /**
     * @Route("/api/v2/users/update", name="api_update_users", methods={"UPDATE"})
     */
    public function UpdateUser(string $userId, User $updatedUser)
    {
        $updateUserRequest = $this->transformer->transformModelToUpdateRequest($updatedUser);
        
        return new JsonResponse($this->userRepository->updateUser($userId, $updateUserRequest));
    }

    /**
    * @Route("/api/v2/users", name="api_delete_users_by_id", methods={"DELETE"})
    */
   public function DeleteUser(string $userId)
   {
       return new JsonResponse($this->userRepository->deleteUser($userId));
   }
    
      /**
     * @Route("/api/v2/users/reset-password", name="api_users_reset_password", methods={"PUT"})
     */
    public function ResetPasswordUser(string $email)
    {
        return new JsonResponse($this->userRepository->resetPassword($email));
    }

}