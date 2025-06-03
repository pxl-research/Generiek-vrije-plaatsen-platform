package be.PXLResearch.code4belgium.nurseries.repository;

import be.PXLResearch.code4belgium.nurseries.domain.NurseryRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseryRoomRepository extends JpaRepository<NurseryRoom, Long> {
}
