package be.PXLResearch.code4belgium.escaperooms.repository;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscapeRoomOrganizationRepository extends JpaRepository<EscapeRoomOrganization, Long> {
}
