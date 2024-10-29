package com.hossainalamin.SpringBootProject.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse{
    private Current current;
    @Data
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
    }

}




