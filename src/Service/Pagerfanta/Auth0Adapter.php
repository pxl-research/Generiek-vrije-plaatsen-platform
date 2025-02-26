<?php

declare(strict_types=1);

namespace App\Service\Pagerfanta;

use App\Model\Auth0Filter;
use App\Service\Auth0\Auth0RepositoryInterface;
use App\Service\Auth0\FilterService;
use Pagerfanta\Adapter\AdapterInterface;

class Auth0Adapter implements AdapterInterface
{
    private Auth0RepositoryInterface $repository;
    private Auth0Filter $filter;
    private FilterService $filterService;

    public function __construct(Auth0RepositoryInterface $repository, Auth0Filter $filter)
    {
        $this->repository = $repository;
        $this->filter = $filter;
        $this->filterService = new FilterService();
    }

    public function getNbResults(): int
    {
        $query = $this->getQuery();

        return \count((array) $this->repository->findByQuery($query));
    }

    /**
     * @param int $offset
     * @param int $length
     *
     * @return array<array-key, mixed>
     */
    public function getSlice($offset, $length): array
    {
        $query = $this->getQuery();

        return $this->repository->findByPage($this->filter->getPage(), $length, $query) ?? [];
    }

    private function getQuery(): string
    {
        return $this->filterService->getFilterString($this->filter);
    }
}
