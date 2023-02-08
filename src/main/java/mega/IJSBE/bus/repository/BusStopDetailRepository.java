package mega.IJSBE.bus.repository;

import mega.IJSBE.bus.entity.BusStopDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusStopDetailRepository extends JpaRepository<BusStopDetails,Long> {

    List<BusStopDetails> findByNodeId (String nodeId);
}
