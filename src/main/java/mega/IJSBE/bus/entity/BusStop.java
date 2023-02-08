package mega.IJSBE.bus.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class BusStop {
    @Id
    @Column(name="busstop_nodeno")
    private Long nodeno; //버스 정류장 고유 번호
    @Column(name="busstop_gpslati")
    private String gpslati; //위도
    @Column(name="busstop_gpslong")
    private String gpslong; //경도

    @Column(name="busstop_name")
    private String nodenm; //정류장 이름

    @Column(name="busstop_nodeid")
    private String nodeid; //고유 id


    @Builder
    public BusStop(String gpslati, String gpslong,String nodenm,String nodeid, Long nodeno){
        this.gpslati = gpslati;
        this.nodeid = nodeid;
        this.gpslong = gpslong;
        this. nodenm = nodenm;
        this.nodeno = nodeno;
    }
}
