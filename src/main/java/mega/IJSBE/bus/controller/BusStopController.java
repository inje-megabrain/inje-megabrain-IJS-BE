package mega.IJSBE.bus.controller;

import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.bus.entity.BusStop;
import mega.IJSBE.bus.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BusStopController {
    @Autowired
    private final BusStopService busStopService;

    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }
    @PostMapping("/busstop/add")
    @Operation(summary = "학교 근처 버스 정류장 저장 api",description = "DB에 저장")
    public ResponseEntity findToByBusStop() {
        try{
            busStopService.addToNearBusStop();
            return ResponseEntity.ok().body("저장됨");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
    @GetMapping("/busstop/find/all")
    @Operation(summary = "학교 근처 버스 정류장 전체조회 api",description = "nodeno = 버스 정류장 고유 번호," +
            "id = 김해시 고유 번호, gpslati = 위도, gpslong = 경도, nodenm = 정류장 이름, nodeid = 정류장 고유 id ")
    public ResponseEntity findAllBusStop(){
        try{
            List<BusStop> busStops = busStopService.findAllBusStop();
            return ResponseEntity.ok().body(busStops);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
    @GetMapping("/busstop/find/details")
    @Operation(summary = "버스정류장 상세 조회 api")
    public ResponseEntity findToBusStop(@RequestParam("BusStop_Name")String name){
        try{
           BusStop busStops = busStopService.findToBusStop(name);
            return ResponseEntity.ok().body(busStops);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
