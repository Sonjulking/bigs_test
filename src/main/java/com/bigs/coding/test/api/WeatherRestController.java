package com.bigs.coding.test.api;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class WeatherRestController {

  private final RestTemplate restTemplate;
  private final Gson gson;

  @Autowired
  private WeatherService weatherService;
  @Autowired
  private WeatherApiRepository weatherApiRepository;

  //PostMapping일때 API 호출 후 DB 적재
  @PostMapping("weather")
  public String weather(@RequestParam("date") String inputDate) throws Exception { //date 입력받음.

    String date = ""; //값 초기화
    date = inputDate.replace("-", ""); //input = "date" 가 0000-00-00 이런 형식으로 받아주기 때문에 "-" 제거
    //System.out.println(date);
    weatherService.InputWeather(date); //원하는 날짜에 데이트를 입력.

    return "자료가 저장되었습니다.";
  }


}
