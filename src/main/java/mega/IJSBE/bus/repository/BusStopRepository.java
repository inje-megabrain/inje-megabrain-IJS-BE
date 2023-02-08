package mega.IJSBE.bus.repository;

import mega.IJSBE.bus.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop,Long> {

    Optional<BusStop> findByNodeid (String nodeid);
}
