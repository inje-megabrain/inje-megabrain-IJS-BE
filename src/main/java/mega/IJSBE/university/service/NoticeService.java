package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniversityNotice;
import mega.IJSBE.university.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<UniversityNotice> findToNoticeCs(){
        List<UniversityNotice> notices = noticeRepository.findByCategory("컴공");
        return notices;
    }
    public List<UniversityNotice> findToNotice(){
        List<UniversityNotice> list = new ArrayList<>();
        List<UniversityNotice> notices = noticeRepository.findByCategory("학사");
        List<UniversityNotice> notice = noticeRepository.findByCategory("일반");
        for(UniversityNotice i: notices) list.add(i);
        for(UniversityNotice i: notice) list.add(i);
        return list;
    }
}
