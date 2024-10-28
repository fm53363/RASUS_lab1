package hr.fer.tel.rassus.HttpClient;

import hr.fer.tel.rassus.HttpClient.api.ReadingApi;
import hr.fer.tel.rassus.HttpClient.api.SensorApi;
import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RetrofitService {

    private String baseUrl;
    private ReadingApi readingApi;
    private SensorApi sensorApi;

    public RetrofitService(String baseUrl) {
        this.baseUrl = baseUrl;
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).
                build();
        readingApi = retrofit.create(ReadingApi.class);
        sensorApi = retrofit.create(SensorApi.class);
    }


    public Long register(Sensor currentSensor) {
        try {
            Response<Void> response = sensorApi.postSensor(currentSensor).execute();
            if (response.isSuccessful()) {
                String location = response.headers().get("Location");
                if (location != null) {
                    try {
                        return Long.parseLong(location.substring(location.lastIndexOf('/') + 1));
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Failed to parse ID from Location header: " + location, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occured while  registering sensor", e);
        }
        return null;
    }


}
