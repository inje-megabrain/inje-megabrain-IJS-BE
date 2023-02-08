package mega.IJSBE.bus.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusStopDetails {

    private String routeno; //노선번호
    private String vehicletp; //도착예정버스 차량유형
    private String routetp; //노선유형
    private String arrprevstationcnt; //도착예정버스 남은 정류장 수
    @Id
    private Long arrtime; //도착예정버스 도착예상시간
    private String nodeNm; //정류장 이름
    private String nodeId; //정류장 고유 id
    @ManyToOne
    @JoinColumn(name ="busstop_nodeid")
    private BusStop busStop;

}
