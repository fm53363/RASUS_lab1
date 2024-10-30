package hr.fer.tel.rassus.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reading {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Double temperature;
    @NotNull
    private Double pressure;
    @NotNull
    private Double humidity;

    private Double co;
    private Double no2;
    private Double so2;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;


}
