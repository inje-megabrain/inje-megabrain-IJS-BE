package mega.IJSBE.bus.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import mega.IJSBE.bus.entity.BusStop;
import mega.IJSBE.bus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class BusController {
    @Autowired
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }
    @GetMapping("/bus/nearBy")
    @Operation(summary = "학교 근처 버스 정류장 찾기 api",description = "ArrayList 형태")
    public ResponseEntity findToNearByBusStop() {
        try{
            List<BusStop> busStops = busService.findToNearBusStop();
            return ResponseEntity.ok().body(busStops);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }
}
