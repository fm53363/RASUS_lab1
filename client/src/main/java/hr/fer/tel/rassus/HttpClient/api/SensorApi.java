package hr.fer.tel.rassus.HttpClient.api;

import hr.fer.tel.rassus.HttpClient.dto.Sensor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface SensorApi {

    @POST("/sensors")
    Call<Void> postSensor(@Body Sensor sensor);

    @GET("/sensors/closest/{id}")
    Call<Sensor> getSensor(@Path("id") String id);


}
