package hr.fer.tel.rassus.HttpClient.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorReading {
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double no2;
    private Double so2;


}
