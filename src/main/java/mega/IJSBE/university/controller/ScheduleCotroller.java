package mega.IJSBE.university.controller;

import mega.IJSBE.university.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ScheduleCotroller {
    @Autowired
    private final ScheduleService scheduleService;

    public ScheduleCotroller(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public ResponseEntity findToSchoolBus(){
        return null;
    }
}
