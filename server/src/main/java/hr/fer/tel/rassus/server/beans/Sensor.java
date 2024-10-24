package hr.fer.tel.rassus.server.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "sensor", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ip", "port"})
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;


    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    List<Reading> readings;


}

