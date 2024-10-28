package hr.fer.tel.rassus.HttpClient.factory;

import hr.fer.tel.rassus.HttpClient.dto.Sensor;

import java.util.Random;

public class SensorFactory {
    private static final double MIN_LONGITUDE = 15.87;
    private static final double MAX_LONGITUDE = 16.0;
    private static final double MIN_LATITUDE = 45.75;
    private static final double MAX_LATITUDE = 45.85;

    private static final Random random = new Random();

    public static Sensor createRandomSensor(int port) {
        double latitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * random.nextDouble();
        double longitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * random.nextDouble();

        // Set static values for IP and port
        String ip = "127.0.0.1";

        return new Sensor(latitude, longitude, ip, port);
    }
}
