package hr.fer.tel.rassus.server.beans;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;
    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;

    @OneToMany(mappedBy = "sensor")
    List<Reading> readings;


}

