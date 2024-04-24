package com.bigs.coding.test.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final RestTemplate restTemplate;
  private final Gson gson;

  @Autowired
  private WeatherApiRepository weatherApiRepository;

  //날씨 자료 저장
  public void InputWeather(String date) throws Exception {
    String apiUrl ="key"
            + date + "&base_time=0500&nx=55&ny=127"; //date는 form에서 받아줌.
    //보안이슈로 키값은 제외했습니다.
    //메일에 따로 첨부하겠습니다.
    URI uri = new URI(apiUrl); //URI 생성

    try {

      //RestTemplate을 사용하여, Get요청 후 String으로 응답받음.
      ResponseEntity<String> rep = restTemplate.getForEntity(uri, String.class);

      //데이터를 String 형식으로저장
      String repBody = rep.getBody();

      //json을 자바객체로 저장
      JsonObject jsonObject = gson.fromJson(repBody, JsonObject.class);

      //response 부분을 자바 객체로 저장
      JsonObject response = jsonObject.getAsJsonObject("response");

      //response안에 있는 body 부분을 자바 객체로 저장
      JsonObject body = response.getAsJsonObject("body");

      //response안에 있는 body 안에 있는 items를  자바 객체로 저장
      JsonObject items = body.getAsJsonObject("items");

      //response안에 있는 body 안에 있는 items안에 있는 item을  자바 객체로 저장
      JsonArray item = items.getAsJsonArray("item");

      System.out.println(item);

      for (int i = 0; i < item.size(); i++) {

        //i번째 json객체 집어넣어줌
        JsonObject weatherItem = item.get(i).getAsJsonObject();

        //각각 키값에 맞는 value값 추출.
        String baseTime = weatherItem.get("baseTime").getAsString();
        String baseDate = weatherItem.get("baseDate").getAsString();
        String category = weatherItem.get("category").getAsString();
        String fcstDate = weatherItem.get("fcstDate").getAsString();
        String fcstTime = weatherItem.get("fcstTime").getAsString();
        String fcstValue = weatherItem.get("fcstValue").getAsString();
        String nx = weatherItem.get("nx").getAsString();
        String ny = weatherItem.get("ny").getAsString();

        //entity 생성후 필드마다 값을 삽입.
        WeatherApiEntity entity = new WeatherApiEntity();
        entity.setBaseTime(baseTime);
        entity.setBaseDate(baseDate);
        entity.setCategory(category);
        entity.setFcstDate(fcstDate);
        entity.setFcstTime(fcstTime);
        entity.setFcstValue(fcstValue);
        entity.setNx(nx);
        entity.setNy(ny);

        //db에 저장
        weatherApiRepository.save(entity);

      }

    } catch (Exception e) {

      throw new Exception(e);

    }
  }

  //DB에 있는 날씨 자료 가져오기
  public List<WeatherApiEntity> getWeather() {

    //List<WeatherApiEntity> entityList = weatherApiRepository.findAll();

    return weatherApiRepository.findAll();

  }
}
