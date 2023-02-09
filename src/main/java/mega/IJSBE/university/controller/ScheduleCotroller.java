package mega.IJSBE.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.university.entity.SchoolBusSchedule;
import mega.IJSBE.university.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ScheduleCotroller {
    @Autowired
    private final ScheduleService scheduleService;

    public ScheduleCotroller(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
@GetMapping("/school/find/toschool")
@Operation(summary = "인제대역 출발 학교행 통학 버스")
    public ResponseEntity findToSchool(){
        try{
            List<SchoolBusSchedule> schedules = scheduleService.findToBus();
            return ResponseEntity.ok().body(schedules);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
    @GetMapping("/school/find/tostation")
    @Operation(summary = "학교출발 인제대역행 통학 버스")
    public ResponseEntity findToStation(){
        try{
            List<SchoolBusSchedule> schedules = scheduleService.findToStation();
            return ResponseEntity.ok().body(schedules);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
