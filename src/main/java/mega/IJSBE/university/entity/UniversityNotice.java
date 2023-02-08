package mega.IJSBE.university.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="university_notice")
public class UniversityNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long noticeId;
    private String title;
    private String authorNickname;
    private Date writeAt;

    private Enum category;

}
enum NoticeCategory {
    CS("컴공") ,
    HS("학사") ,
    GN("일반");
    @Getter
    private final String value;
    NoticeCategory(String value){
        this.value = value;
    }
}
