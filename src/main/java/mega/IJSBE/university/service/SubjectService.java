package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniversityNonsubject;
import mega.IJSBE.university.repository.NonSubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private final NonSubjectsRepository nonSubjectsRepository;

    public SubjectService(NonSubjectsRepository nonSubjectsRepository) {
        this.nonSubjectsRepository = nonSubjectsRepository;
    }
    /************ 현재 시간을 지난 비교과 프로그램은 담지 않음 ***************/
    public List<UniversityNonsubject> findToSubject(){
        List<UniversityNonsubject> sub = nonSubjectsRepository.findAll(Sort.by("endAt").descending());
        List<UniversityNonsubject> list = new ArrayList<>();
        for(UniversityNonsubject i : sub){
            LocalDate end = LocalDate.ofInstant(i.getEndAt().toInstant(), ZoneId.systemDefault());
            int time = end.compareTo(LocalDate.now());
            if(time == 0){
                list.add(i);
            }
            else if(time>0){
                list.add(i);
            }
        }
        return list;
    }
}
