package mega.IJSBE.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.university.entity.UniverysityDishes;
import mega.IJSBE.university.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DishesController {
    @Autowired
    private final DishesService dishesService;

    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }
    @GetMapping("/dishs/today")
    @Operation(summary = "today 학식 찾기 API",description = "현재 날짜와 비교해서 같은 학식만 리턴")
    public ResponseEntity findTodayDish(){
        try{
            List<UniverysityDishes> dishe = dishesService.findToDish();
            return ResponseEntity.ok().body(dishe);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
