package hr.fer.tel.rassus.HttpClient;

import hr.fer.tel.rassus.HttpClient.api.ReadingApi;
import hr.fer.tel.rassus.HttpClient.api.SensorApi;
import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import hr.fer.tel.rassus.HttpClient.repo.SensorReading;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class MyHttpClient {

    private static final Logger logger = Logger.getLogger(MyHttpClient.class.getName());
    private static final Log log = LogFactory.getLog(MyHttpClient.class);


    private String baseUrl;
    private ReadingApi readingApi;
    private SensorApi sensorApi;

    public MyHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).
                build();
        readingApi = retrofit.create(ReadingApi.class);
        sensorApi = retrofit.create(SensorApi.class);
    }


    public void postReadingForSensor(Long id, SensorReading reading) {
        try {
            Response<Void> response = readingApi.postReading(id, reading).execute();
            String location = response.headers().get("Location");
            long readingId = Long.parseLong(location.substring(location.lastIndexOf('/') + 1));
            logger.info("saved reading with id:" + readingId + "\n");


        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving reading", e);

        }
    }

    public Long register(Sensor currentSensor) {
        try {
            Response<Void> response = sensorApi.postSensor(currentSensor).execute();
            String location = response.headers().get("Location");
            long id = Long.parseLong(location.substring(location.lastIndexOf('/') + 1));
            logger.info("Current sensor registered  with ID:" + id + "\n");
            return id;


        } catch (Exception e) {
            throw new RuntimeException("Error occurred while  registering sensor", e);

        }


    }

    public Sensor findClosestSensor(Long id) {
        try {
            Sensor s = sensorApi.getClosestSensor(id.toString()).execute().body();
            logger.info("Closest sensor is:" + s + "\n");
            return s;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
