package hr.fer.tel.rassus.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Reading {
    @Id
    @GeneratedValue
    private Long id;


    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double so2;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;


}
