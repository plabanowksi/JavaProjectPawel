package pd15.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    public Current current;

    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {

        @JsonProperty("temperature_2m")
        public double temperature;

        @JsonProperty("wind_speed_10m")
        public double windSpeed;
    }
}