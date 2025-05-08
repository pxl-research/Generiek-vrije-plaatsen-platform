package be.PXLResearch.code4belgium.escaperooms.repository;

import be.PXLResearch.code4belgium.escaperooms.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    //TODO: specific CRUD operations
}
