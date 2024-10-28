package hr.fer.tel.rassus.HttpClient.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SensorReading {
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double so2;


}
