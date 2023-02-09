package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniversitySchedule;
import mega.IJSBE.university.repository.UniScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniScheduleService {
    @Autowired
    private final UniScheduleRepository uniScheduleRepository;

    public UniScheduleService(UniScheduleRepository uniScheduleRepository) {
        this.uniScheduleRepository = uniScheduleRepository;
    }

    public List<UniversitySchedule> findToList(){
        List<UniversitySchedule> list = uniScheduleRepository.findAll(Sort.by("isStartDate").descending());
        return list;
    }
}
