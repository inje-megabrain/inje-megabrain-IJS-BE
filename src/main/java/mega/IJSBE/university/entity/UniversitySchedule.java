package mega.IJSBE.university.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="university_schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversitySchedule {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date startAt;
    private Date endAt;
    @Column(columnDefinition = "boolean default false")
    private Boolean isStartDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean isEnDate;
}
