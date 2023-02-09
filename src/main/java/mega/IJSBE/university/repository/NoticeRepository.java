package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.NoticeCategory;
import mega.IJSBE.university.entity.UniversityNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<UniversityNotice,Long> {
    List<UniversityNotice> findByCategory (NoticeCategory name);
}
