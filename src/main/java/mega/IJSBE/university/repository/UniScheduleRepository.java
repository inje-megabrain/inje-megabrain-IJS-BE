package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.UniversitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniScheduleRepository extends JpaRepository<UniversitySchedule,Long> {

}
