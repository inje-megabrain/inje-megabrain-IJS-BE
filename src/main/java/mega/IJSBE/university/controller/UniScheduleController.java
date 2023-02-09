package mega.IJSBE.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.university.entity.UniversitySchedule;
import mega.IJSBE.university.service.UniScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniScheduleController {
    @Autowired
    private final UniScheduleService uniScheduleService;

    public UniScheduleController(UniScheduleService uniScheduleService) {
        this.uniScheduleService = uniScheduleService;
    }

    @GetMapping("university/schedule")
    @Operation(summary = "학교 달력 조회 api")
    public ResponseEntity findToUniversity(){
        try{
            List<UniversitySchedule> list = uniScheduleService.findToList();
            return ResponseEntity.ok().body(list);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
