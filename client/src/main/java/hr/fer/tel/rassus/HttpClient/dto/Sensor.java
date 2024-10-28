package hr.fer.tel.rassus.HttpClient.dto;

import java.util.Objects;

public class Sensor {
    private double latitude;
    private double longitude;
    private String ip;
    private int port;

    public Sensor(double latitude, double longitude, String ip, int port) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ip = ip;
        this.port = port;
    }

    public Sensor() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Double.compare(latitude, sensor.latitude) == 0 && Double.compare(longitude, sensor.longitude) == 0 && port == sensor.port && Objects.equals(ip, sensor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, ip, port);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
