package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.SchoolBusSchedule;
import mega.IJSBE.university.entity.UniversityNonsubject;
import mega.IJSBE.university.entity.UniversitySchedule;
import mega.IJSBE.university.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ScheduleService {
    @Autowired
    private final SchedulesRepository schedulesRepository;

    public ScheduleService(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    public List<SchoolBusSchedule> findToBus() {
        List<SchoolBusSchedule> list = schedulesRepository.findAll(Sort.by("departureTime").ascending());
        List<SchoolBusSchedule> schedules = new ArrayList<>();
        for (SchoolBusSchedule i : list) {
            LocalTime end = LocalTime.ofInstant(i.getDepartureTime().toInstant(), ZoneId.of("Asia/Seoul"));
            int time = end.compareTo(LocalTime.now());
            if(time >0){
                if (i.getToSchool() == true) {
                    schedules.add(i);
                }
            }
        }
        return schedules;
    }

    public List<SchoolBusSchedule> findToStation() {
        List<SchoolBusSchedule> list = schedulesRepository.findAll(Sort.by("departureTime").ascending());
        List<SchoolBusSchedule> schedules = new ArrayList<>();
        for (SchoolBusSchedule i : list) {

            LocalTime end = LocalTime.ofInstant(i.getDepartureTime().toInstant(), ZoneId.of("Asia/Seoul"));
            int time = end.compareTo(LocalTime.now());
            if(time >0){
                if (i.getToSchool() == false) {
                    System.out.println(i.getDepartureTime());
                    schedules.add(i);
                }
            }
        }
        return schedules;
    }
}
