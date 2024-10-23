package hr.fer.tel.rassus.server.beans;

import jakarta.persistence.*;
import lombok.Data;

import java.net.URI;
import java.util.List;

@Entity
@Data
@Table(name = "sensor", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ip", "port"})
})
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

    public URI getUrl() {
        // Construct the URL based on the IP address and port
        return URI.create("http://" + ip + ":" + port); // Assuming getId() gives the unique identifier

    }


}

