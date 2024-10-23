package hr.fer.tel.rassus.server.beans;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Reading {
    @Id
    @GeneratedValue
    private Long id;

    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Reading(Long id, Double latitude, Double longitude, String ip, Integer port, Sensor sensor) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ip = ip;
        this.port = port;
        this.sensor = sensor;
    }

    public Reading() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return Objects.equals(id, reading.id) && Objects.equals(latitude, reading.latitude) && Objects.equals(longitude, reading.longitude) && Objects.equals(ip, reading.ip) && Objects.equals(port, reading.port) && Objects.equals(sensor, reading.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, ip, port, sensor);
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", sensor=" + sensor +
                '}';
    }
}

