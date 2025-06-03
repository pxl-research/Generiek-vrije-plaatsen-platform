package be.PXLResearch.code4belgium.schools.repository;

import be.PXLResearch.code4belgium.schools.domain.SchoolRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRoomRepository extends JpaRepository<SchoolRoom, Long> {
}
