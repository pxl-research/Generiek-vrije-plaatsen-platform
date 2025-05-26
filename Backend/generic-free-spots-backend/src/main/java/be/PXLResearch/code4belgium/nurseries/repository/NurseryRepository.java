package be.PXLResearch.code4belgium.nurseries.repository;

import be.PXLResearch.code4belgium.nurseries.domain.Nursery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseryRepository extends JpaRepository<Nursery, Long> {
}
