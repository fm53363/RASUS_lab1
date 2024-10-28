package hr.fer.tel.rassus.server.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDTO {

    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;


}
