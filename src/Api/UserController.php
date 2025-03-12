<?php


namespace App\Api;

use Exception;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use App\Service\Auth0\UserRepository;
use App\Model\Request\CreateUserRequest;
use App\Model\User;
use App\Service\Transformer\UserTransformer;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;

class UserController extends AbstractController
{
    private UserRepository $userRepository;
    private UserTransformer $transformer;
    private SerializerInterface $serializer;

    public function __construct(
        UserRepository $userRepository,
        UserTransformer $transformer,
        SerializerInterface $serializer
    )
    {
        $this->userRepository = $userRepository;
        $this->transformer = $transformer;
        $this->serializer = $serializer;
    }

  /**
     * @Route("/api/v2/users", name="api_get_users", methods={"GET"})
     */
    public function GetAllUser()
    {
        $users = $this->userRepository->findAll();

        $allUsers = [];

        foreach ($users as $user) {
            $allUsers[] = $this->transformer->transformModelToArray($user);
        }

        return new JsonResponse($allUsers);
    }

    /**
     * @Route("/api/v2/users/{userId}", name="api_get_users_by_id", methods={"GET"})
     * @throws Exception
     */
    public function GetUserById(string $userId)
    {
        $user = $this->userRepository->findById($userId, true);
        return new JsonResponse($this->transformer->transformModelToArray($user));
        // TEST USER ID VOOR IN POSTMAN: auth0|67caff3bacd0dcd6433d90d2
    }

    /**
     * @Route("/api/v2/users/create", name="api_create_user", methods={"POST"})
     */
    public function CreateUser(Request $request)
    {

        $data = json_decode($request->getContent(), true);

        if (!isset($data['firstName'], $data['lastName'], $data['email'])) {
            return new JsonResponse(['error' => 'Missing required fields'], 400);
        }

        $newUser = new User($data['email']);
        $newUser->setFirstName($data['firstName']);
        $newUser->setLastName($data['lastName']);
        $newUser->setEmail($data['email']);
        $newUser->setPassword($data['password'] ?? uniqid());
        $newUser->setExternalRole($data['externalRole'] ?? null);

        $createUserRequest = $this->transformer->transformModelToCreateRequest($newUser);

        return new JsonResponse($this->userRepository->createUser($createUserRequest));
    }

    /**
     * @Route("/api/v2/users/{userId}/update", name="api_update_users", methods={"PATCH"})
     */
    public function UpdateUser(string $userId, Request $request)
    {
        // Decode request body
        $data = json_decode($request->getContent(), true);

        // Fetch the existing user
        $existingUserResponse = $this->userRepository->findById($userId);
        if (!$existingUserResponse) {
            return new JsonResponse(['error' => 'User not found'], 404);
        }

        $existingUser = $this->transformer->transformResponseToModel($existingUserResponse);

        if (isset($data['firstName'])) {
            $existingUser->setFirstName($data['firstName']);
        }
        if (isset($data['lastName'])) {
            $existingUser->setLastName($data['lastName']);
        }
        if (isset($data['email'])) {
            $existingUser->setEmail($data['email']);
        }

        $updateUserRequest = $this->transformer->transformModelToUpdateRequest($existingUser);

        return new JsonResponse($this->userRepository->updateUser($userId, $updateUserRequest));
    }

    /**
    * @Route("/api/v2/users/{userId}", name="api_delete_users_by_id", methods={"DELETE"})
    */
   public function DeleteUser(string $userId)
   {
       return new JsonResponse($this->userRepository->deleteUser($userId));
   }

      /**
     * @Route("/api/v2/users/{userId}/reset-password", name="api_users_reset_password", methods={"PUT"})
     */
    public function ResetPasswordUser(string $userId, Request $request)
    {
        $data = json_decode($request->getContent(), true);

        if (!isset($data['email'])) {
            return new JsonResponse(['error' => 'Email is required'], 400);
        }

        $this->userRepository->resetPassword($data['email']);
        return new JsonResponse(['message' => 'Password reset email sent'], 200);
    }

}
