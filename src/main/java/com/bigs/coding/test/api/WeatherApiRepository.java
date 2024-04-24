package com.bigs.coding.test.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherApiRepository extends JpaRepository<WeatherApiEntity, String> {

}
