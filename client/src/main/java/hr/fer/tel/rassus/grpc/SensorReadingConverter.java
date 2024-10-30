package hr.fer.tel.rassus.grpc;

import hr.fer.tel.rassus.HttpClient.repo.SensorReading;

import java.util.function.Function;


public class SensorReadingConverter {
    private static final Function<Double, Double> NULL_TO_ZERO = x -> (x == null) ? 0.0 : x;
    private static final Function<Double, Double> ZERO_TO_NULL = x -> (x == 0.0) ? null : x;

    public static ReadingResponse toReadingResponse(SensorReading reading) {
        return ReadingResponse.newBuilder().
                setTemperature((reading.getTemperature())).
                setHumidity((reading.getHumidity())).
                setPressure((reading.getPressure())).
                setNo2(NULL_TO_ZERO.apply(reading.getNo2())).
                setCo(NULL_TO_ZERO.apply(reading.getCo())).
                setSo2(NULL_TO_ZERO.apply(reading.getSo2())).
                build();
    }

    public static SensorReading toSensorReading(ReadingResponse readingResponse) {
        return SensorReading.builder().
                temperature(readingResponse.getTemperature()).
                humidity(readingResponse.getHumidity()).
                pressure(readingResponse.getPressure()).
                no2(ZERO_TO_NULL.apply(readingResponse.getNo2())).
                so2(ZERO_TO_NULL.apply(readingResponse.getSo2()))
                .co(ZERO_TO_NULL.apply(readingResponse.getCo())).
                build();
    }


}
