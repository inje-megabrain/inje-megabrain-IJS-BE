package mega.IJSBE.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.university.entity.UniversityNonsubject;
import mega.IJSBE.university.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subject/find")
    @Operation(summary = "비교과 프로그램 조회 api",description = "contentUrl = 이미지, endAt= 마감날짜")
    public ResponseEntity findToSub(){
        try{
            List<UniversityNonsubject> sub = subjectService.findToSubject();
            return ResponseEntity.ok().body(sub);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }

}
