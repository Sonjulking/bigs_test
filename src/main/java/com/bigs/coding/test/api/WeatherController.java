package com.bigs.coding.test.api;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WeatherController {

  private final WeatherService weatherService;

  @GetMapping("")
  public String main() {

    return "main";

  }

  @GetMapping("/weather")
  public String weather(Model model, HttpServletResponse response) {

    List<WeatherApiEntity> entities = weatherService.getWeather(); //자료를 불러옴

    if (entities == null || entities.isEmpty()) { //자료가 없다면

      response.setStatus(HttpServletResponse.SC_NO_CONTENT); //204에러 보내줌.

    } else {

      model.addAttribute("list", entities); //list라는 변수로 자료를 보내줌.

    }

    return "weather";

  }


}
