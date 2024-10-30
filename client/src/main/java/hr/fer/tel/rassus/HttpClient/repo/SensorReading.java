package hr.fer.tel.rassus.HttpClient.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.BiFunction;

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

    public SensorReading calibrate(SensorReading other) {
        BiFunction<Double, Double, Double> averageOrNull = (first, second) -> {
            if (first == null && second == null) {
                return null;
            } else if (first == null) {
                return second;
            } else if (second == null) {
                return first;
            } else {
                return (first + second) / 2;
            }
        };

        SensorReading newSensorReading = new SensorReading();
        newSensorReading.setTemperature(averageOrNull.apply(temperature, other.temperature));
        newSensorReading.setPressure(averageOrNull.apply(pressure, other.pressure));
        newSensorReading.setHumidity(averageOrNull.apply(humidity, other.humidity));
        newSensorReading.setCo(averageOrNull.apply(co, other.co));
        newSensorReading.setNo2(averageOrNull.apply(no2, other.no2));
        newSensorReading.setSo2(averageOrNull.apply(so2, other.so2));
        return newSensorReading;

    }


}
