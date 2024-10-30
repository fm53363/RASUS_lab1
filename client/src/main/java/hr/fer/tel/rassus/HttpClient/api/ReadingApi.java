package hr.fer.tel.rassus.HttpClient.api;

import hr.fer.tel.rassus.HttpClient.repo.SensorReading;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReadingApi {
    @POST("/readings/{id}")
    Call<Void> postReading(@Path("id") Long id, @Body SensorReading reading);

}
