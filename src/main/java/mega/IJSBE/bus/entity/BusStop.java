package mega.IJSBE.bus.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {
    @Id
    @Column(name="busstop_citycode")
    private Long id;
    @Column(name="busstop_gpslati")
    private String gpslati; //위도
    @Column(name="busstop_gpslong")
    private String gpslong; //경도

    @Column(name="busstop_name")
    private String nodenm; //정류장 이름

    @Column(name="busstop_nodeid")
    private String nodeid; //고유 id

    @Column(name="busstop_nodeno")
    private String nodeno; //고유 넘버
}
