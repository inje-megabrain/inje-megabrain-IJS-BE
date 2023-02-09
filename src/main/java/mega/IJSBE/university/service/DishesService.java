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
    public List<UniverysityDishes> findToDish(){
        List<UniverysityDishes> dishes = dishesRepository.findAll();
        return dishes;
    }
}
