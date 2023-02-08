package mega.IJSBE.bus.service;

import mega.IJSBE.bus.entity.BusStop;
import mega.IJSBE.bus.repository.BusStopRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusStopService {
    @Autowired
    private final BusStopRepository busStopRepository;
    @Value("${spring.tago.key}")
    private String serviceKey;
    @Value("${spring.tago.gpsLati}")
    private String gpsLati;
    @Value("${spring.tago.gpsLong}")
    private String gpsLong;

    public BusStopService(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    /**************** 버스 정류장 DB 저장 서비스 *******************/
    public void addToNearBusStop(String nodeNo) {
        try{
            StringBuilder builder= new StringBuilder("http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList");
            builder.append("?"+ URLEncoder.encode("serviceKey","UTF-8")+"="+serviceKey);
            builder.append("&"+ URLEncoder.encode("pageNo","UTF-8")+"=1");
            builder.append("&"+ URLEncoder.encode("numOfRows","UTF-8")+"=10");
            builder.append("&"+ URLEncoder.encode("_type","UTF-8")+"=json");
            builder.append("&"+ URLEncoder.encode("gpsLati","UTF-8")+"="+gpsLati);
            builder.append("&"+ URLEncoder.encode("gpsLong","UTF-8")+"="+gpsLong);
            URL url = new URL(builder.toString());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while((line = rd.readLine())!=null){
                sb.append(line);
            }
            rd.close();
            connection.disconnect();
            System.out.println("sb = " + sb.toString());
            JSONObject obj = new JSONObject(sb.toString());
            JSONObject response = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items");
            JSONArray item = response.getJSONArray("item");
            for(int i=0;i<item.length();i++){
                JSONObject get = item.getJSONObject(i);
                if(get.get("nodenm").toString().equals("인제대학교")){
                    BusStop busStop = new BusStop(
                            get.get("gpslati").toString(),
                            get.get("gpslong").toString(),
                            get.get("nodenm").toString(),
                            get.get("nodeid").toString(),
                            Long.valueOf(get.get("nodeno").toString())
                    );
                    busStopRepository.save(busStop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 }
 /**************** 버스 정류장 전체 조회 서비스 *******************/
    public List<BusStop> findAllBusStop(){
        List<BusStop> busStops =busStopRepository.findAll();
        return busStops;
    }
    /**************** 버스 정류장 상세 조회 서비스 *******************/
    public BusStop findToBusStop(String nodeid){
        Optional<BusStop> busStop = busStopRepository.findByNodeid(nodeid);
        busStop .orElseThrow(()->{
            throw new RuntimeException("해당 버스 정류장이 없습니다.");
        });
        BusStop busStops = BusStop.builder()
                .gpslati(busStop.get().getGpslati())
                .gpslong(busStop.get().getGpslong())
                .nodeid(busStop.get().getNodeid())
                .nodenm(busStop.get().getNodenm())
                .nodeno(busStop.get().getNodeno())
                .build();
        return busStops;
    }
}
