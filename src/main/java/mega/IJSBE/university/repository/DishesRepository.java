package mega.IJSBE.university.repository;

import mega.IJSBE.university.entity.UniverysityDishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<UniverysityDishes,Long> {
}
