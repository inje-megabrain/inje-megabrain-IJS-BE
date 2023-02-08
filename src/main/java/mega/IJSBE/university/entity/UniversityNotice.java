package mega.IJSBE.university.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="university_notice")
@Builder
public class UniversityNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String noticeId; // 작성번호
    private String title; //제목
    private String authorNickname; //작성자
    private Date writeAt; //작성일

    private Enum category; //분류

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
