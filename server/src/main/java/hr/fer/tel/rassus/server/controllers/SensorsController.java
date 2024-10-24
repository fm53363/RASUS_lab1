package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.beans.Sensor;
import hr.fer.tel.rassus.server.services.SensorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("sensors")
public class SensorsController {

    private final SensorRepository sensorRepository;

    public SensorsController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    //  TODO 4.1  Registracija
    @PostMapping(value = "/")
    public ResponseEntity<String> postSensor(@RequestBody Sensor sensor) {
        try {
            var savedSensor = sensorRepository.save(sensor);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI("/sensors/" + savedSensor.getId()));
            return new ResponseEntity<>("created sensor with id:" + savedSensor.getId(), headers, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(String.format("Sensor with ip=%s and port=%s already exists", sensor.getIp(), sensor.getPort()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    //  TODO 4.4  Popis senzora

    //  TODO 4.2  Najbliži susjed
    @GetMapping("/closest/{id}")
    public ResponseEntity<Sensor> getClosestSensor(@PathVariable Long id) {
        return findClosestNeighbour(id);
    }

    private double haversineFormula(Sensor s1, Sensor s2) {
        double lat1 = Math.toRadians(s1.getLatitude());
        double lon1 = Math.toRadians(s1.getLongitude());
        double lat2 = Math.toRadians(s2.getLatitude());
        double lon2 = Math.toRadians(s2.getLongitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        // Haversine formula
        double a = Math.pow(Math.sin(dlat / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

    private ResponseEntity<Sensor> findClosestNeighbour(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Sensor> sensors = sensorRepository.findAll();

        var sensorOpt = sensors.stream().
                filter((s) -> !s.getId().equals(id)).
                min((s1, s2) -> Double.compare(haversineFormula(s1, sensor), haversineFormula(s2, sensor)));

        return sensorOpt.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));


    }

}
