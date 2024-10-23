package hr.fer.tel.rassus.server.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;

    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double so2;

    @OneToMany(mappedBy = "sensor")
    List<Reading> readings;

    public Sensor(Long id, Double temperature, Double pressure, Double humidity, Double co, Double so2, List<Reading> readings) {
        this.id = id;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.co = co;
        this.so2 = so2;
        this.readings = readings;
    }

    public Sensor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public List<Reading> getReadings() {
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(temperature, sensor.temperature) && Objects.equals(pressure, sensor.pressure) && Objects.equals(humidity, sensor.humidity) && Objects.equals(co, sensor.co) && Objects.equals(so2, sensor.so2) && Objects.equals(readings, sensor.readings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, pressure, humidity, co, so2, readings);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", co=" + co +
                ", so2=" + so2 +
                ", readings=" + readings +
                '}';
    }
}
