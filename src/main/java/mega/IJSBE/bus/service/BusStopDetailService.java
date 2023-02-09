package mega.IJSBE.bus.service;

import mega.IJSBE.bus.entity.BusStop;
import mega.IJSBE.bus.entity.BusStopDetails;
import mega.IJSBE.bus.repository.BusStopDetailRepository;
import mega.IJSBE.bus.repository.BusStopRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service @EnableScheduling
public class BusStopDetailService {
    @Autowired
    private final BusStopRepository busStopRepository;
    @Autowired
    private final BusStopDetailRepository busStopDetailRepository;
    @Value("${spring.tago.key}")
    private String serviceKey;
    @Value("${spring.tago.cityCode}")
    private String cityCode;

    public BusStopDetailService(BusStopRepository busStopRepository, BusStopDetailRepository busStopDetailRepository) {
        this.busStopRepository = busStopRepository;
        this.busStopDetailRepository = busStopDetailRepository;
    }
    @Scheduled(cron = "0/300 * * * * *")
    public void updateToDetails(){
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("dateTime = " + dateTime);
        List<BusStop> busStops = busStopRepository.findAll();
        busStopDetailRepository.deleteAll();
        for(int i =0;i<busStops.size();i++){
            try{
                StringBuilder builder= new StringBuilder("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList");
                builder.append("?"+ URLEncoder.encode("serviceKey","UTF-8")+"="+serviceKey);
                builder.append("&"+ URLEncoder.encode("pageNo","UTF-8")+"=1");
                builder.append("&"+ URLEncoder.encode("numOfRows","UTF-8")+"=10");
                builder.append("&"+ URLEncoder.encode("_type","UTF-8")+"=json");
                builder.append("&"+ URLEncoder.encode("cityCode","UTF-8")+"="+cityCode);
                builder.append("&"+ URLEncoder.encode("nodeId","UTF-8")+"="+busStops.get(i).getNodeid());

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
                JSONObject obj = new JSONObject(sb.toString());

                if(obj.getJSONObject("response").getJSONObject("body").get("totalCount").equals(0)){
                    busStopDetailRepository.deleteAll();
                    throw new RuntimeException("등록된 버스 정보가 없습니다.");
                }
                else{
                    JSONObject response = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items");
                    JSONArray item = response.getJSONArray("item");
                    for(int j=0;j<item.length();j++){
                        JSONObject get = item.getJSONObject(j);
                        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
                        LocalDateTime now = LocalDateTime.now().plusSeconds(get.getInt("arrtime")%60).plusMinutes(get.getInt("arrtime")/60);
                        now.atZone(ZoneId.of("+02:00"));
                        System.out.println("get.getInt(\"arrtime\")/60 = " + now);
                        BusStopDetails details = BusStopDetails.builder()
                                .arrprevstationcnt(get.get("arrprevstationcnt").toString())
                                .arrtime(now.toString())
                                .routeno((get.get("routeno").toString()))
                                .routetp(get.get("routetp").toString())
                                .vehicletp(get.get("vehicletp").toString())
                                .nodeNm(get.get("nodenm").toString())
                                .nodeId(get.get("nodeid").toString())
                                .build();

                        busStopDetailRepository.save(details);
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public List<BusStopDetails> findToBusList(String nodeid){
        return busStopDetailRepository.findByNodeId(nodeid);
    }
}
