package be.PXLResearch.code4belgium.escaperooms.repository;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscapeRoomRepository extends JpaRepository<EscapeRoom, Long> {
    //TODO: specific CRUD operations
}
