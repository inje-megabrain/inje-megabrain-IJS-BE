package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.UniversityNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRespository extends JpaRepository<UniversityNotice,Long> {

}
