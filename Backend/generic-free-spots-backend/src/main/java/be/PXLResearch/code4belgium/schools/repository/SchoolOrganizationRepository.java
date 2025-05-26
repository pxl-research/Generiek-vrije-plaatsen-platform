package be.PXLResearch.code4belgium.schools.repository;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import be.PXLResearch.code4belgium.schools.domain.SchoolOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolOrganizationRepository extends JpaRepository<SchoolOrganization, Long> {
}
