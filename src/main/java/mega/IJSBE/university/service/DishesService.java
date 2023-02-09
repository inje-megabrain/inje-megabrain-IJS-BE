package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniverysityDishes;
import mega.IJSBE.university.repository.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishesService {
    @Autowired
    private final DishesRepository dishesRepository;

    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }
    /*********** 오늘의 학식을 찾아서 리턴 *************/
    public UniverysityDishes findToDish(){
        List<UniverysityDishes> dishes = dishesRepository.findAll();
        UniverysityDishes dish =null;
        for(UniverysityDishes i : dishes){
            LocalDate end = LocalDate.ofInstant(i.getProvideAt().toInstant(), ZoneId.systemDefault());
            int time = end.compareTo(LocalDate.now());
            if(time==0){
                dish=i;
            }
        }
        return dish;
    }
}
