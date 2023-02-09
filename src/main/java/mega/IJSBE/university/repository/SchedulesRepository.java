package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.SchoolBusSchedule;
import mega.IJSBE.university.entity.UniversitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<SchoolBusSchedule,Long> {

    List<SchoolBusSchedule> findByToSchool (Boolean toSchool);

}
