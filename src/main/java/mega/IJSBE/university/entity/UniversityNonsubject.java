package mega.IJSBE.university.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="university_nonsubjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityNonsubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contentUrl;
    private Date endAt;

}
