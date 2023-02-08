package mega.IJSBE.university.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="university_dishes")
public class UniverysityDishes {
    @Id
    private Long id;
    @Column(nullable = true)
    private String aMenu ; // nullable

    @Column(nullable = true)
    private String bMenu; // nullable

    @Column(nullable = true)
    private  String cMenu; // nullable

    private Date provideAt;
}
