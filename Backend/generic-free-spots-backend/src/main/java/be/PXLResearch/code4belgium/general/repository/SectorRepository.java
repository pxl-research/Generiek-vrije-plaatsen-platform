package be.PXLResearch.code4belgium.general.repository;

import be.PXLResearch.code4belgium.general.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
