package mega.IJSBE.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.university.entity.UniversityNotice;
import mega.IJSBE.university.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api")
@RestController
public class NoticeController {
    @Autowired
    public final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice/find/cs")
    @Operation(summary = "컴공 공지 조회 API",description = "컴공 Notice")
    public ResponseEntity findToNoticeCs(){
        try{
            List<UniversityNotice> notices = noticeService.findToNoticeCs();
            return ResponseEntity.ok().body(notices);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }

    @GetMapping("/notice/find/other")
    @Operation(summary = "학사,일반 공지 조회 API",description = " 학사,일반은 같이")
    public ResponseEntity findToNoticeOther(){
        try{
            List<UniversityNotice> notices = noticeService.findToNotice();
            return ResponseEntity.ok().body(notices);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
