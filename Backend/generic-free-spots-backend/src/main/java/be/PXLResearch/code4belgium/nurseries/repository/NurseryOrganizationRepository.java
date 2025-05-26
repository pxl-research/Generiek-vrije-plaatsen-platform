package be.PXLResearch.code4belgium.nurseries.repository;

import be.PXLResearch.code4belgium.nurseries.domain.NurseryOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseryOrganizationRepository extends JpaRepository<NurseryOrganization, Long> {

}
