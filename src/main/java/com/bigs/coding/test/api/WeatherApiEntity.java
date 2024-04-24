package com.bigs.coding.test.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather")
@Getter
@Setter
public class WeatherApiEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private String baseDate;

  private String baseTime;

  private String category;

  private String fcstDate;

  private String fcstTime;

  private String fcstValue;

  private String nx;

  private String ny;

}
