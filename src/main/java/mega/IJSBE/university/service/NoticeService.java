package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniversityNotice;
import mega.IJSBE.university.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<UniversityNotice> findToNotice(){
        /*List<UniversityNotice> notices = noticeRepository.findByCategory(NoticeCategory.valueOf("컴공"));
        System.out.println("notices = " + NoticeCategory.CS);
        return notices;*/
        return null;
    }
}
