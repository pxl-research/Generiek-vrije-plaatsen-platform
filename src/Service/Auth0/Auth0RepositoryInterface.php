<?php

declare(strict_types=1);

namespace App\Service\Auth0;

use App\Model\Response\ResponseInterface;

interface Auth0RepositoryInterface
{
    /**
     * @return ResponseInterface[]|null
     */
    public function findAll(): ?array;

    /**
     * @return ResponseInterface[]|null
     */
    public function findByQuery(?string $query): ?array;

    public function getMaxPerPage(): int;

    /**
     * @return ResponseInterface[]|null
     */
    public function findByPage(int $page, int $length, ?string $query): ?array;
}
