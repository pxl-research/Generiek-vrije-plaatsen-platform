package be.PXLResearch.code4belgium.escaperooms.repository;

import be.PXLResearch.code4belgium.escaperooms.domain.EscapeRoomRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscapeRoomRoomRepository extends JpaRepository<EscapeRoomRoom, Long> {
}
