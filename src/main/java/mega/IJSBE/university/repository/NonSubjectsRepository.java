package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.UniversityNonsubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonSubjectsRepository extends JpaRepository<UniversityNonsubject,Long> {
}
