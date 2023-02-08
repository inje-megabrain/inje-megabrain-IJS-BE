package mega.IJSBE.bus.service;

import mega.IJSBE.bus.entity.BusStop;
import mega.IJSBE.bus.repository.BusStopRepository;
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

@Service
public class BusService {
    @Autowired
    private final BusStopRepository busStopRepository;
    @Value("${spring.tago.key}")
    private String serviceKey;
    @Value("${spring.tago.gpsLati}")
    private String gpsLati;
    @Value("${spring.tago.gpsLong}")
    private String gpsLong;

    public BusService(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    public List<BusStop> findToNearBusStop() {
        ArrayList<BusStop> list = new ArrayList<BusStop>();
     try{
         StringBuilder builder= new StringBuilder("http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList");
         builder.append("?"+ URLEncoder.encode("serviceKey","UTF-8")+"="+serviceKey);
         builder.append("&"+ URLEncoder.encode("pageNo","UTF-8")+"=1");
         builder.append("&"+ URLEncoder.encode("numOfRows","UTF-8")+"=10");
         builder.append("&"+ URLEncoder.encode("_type","UTF-8")+"=json");
         builder.append("&"+ URLEncoder.encode("gpsLati","UTF-8")+"="+gpsLati);
         builder.append("&"+ URLEncoder.encode("gpsLong","UTF-8")+"="+gpsLong);
         System.out.println("builder = " + builder);
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
         JSONObject response = obj.getJSONObject("response").getJSONObject("body").getJSONObject("items");
         JSONArray item = response.getJSONArray("item");
         for(int i=0;i<item.length();i++){
             JSONObject get = item.getJSONObject(i);
             BusStop busStop = BusStop.builder()
                     .id(Long.valueOf(get.get("citycode").toString()))
                     .gpslati(get.get("gpslati").toString())
                     .gpslong(get.get("gpslong").toString())
                     .nodenm(get.get("nodenm").toString())
                     .nodeno(get.get("nodeno").toString())
                     .nodeid(get.get("nodeid").toString())
                     .build();
             list.add(busStop);
             busStopRepository.save(busStop);

         }
    } catch (Exception e) {
        e.printStackTrace();
    }
     return list;
    }
 }
