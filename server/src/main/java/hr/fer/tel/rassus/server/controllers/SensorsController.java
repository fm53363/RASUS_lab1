package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.dto.SensorDTO;
import hr.fer.tel.rassus.server.entity.Sensor;
import hr.fer.tel.rassus.server.services.SensorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("sensors")
public class SensorsController {

    private final SensorRepository sensorRepository;

    public SensorsController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable("id") Long id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        return sensor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    //  TODO 4.1  Registracija
    @PostMapping
    public ResponseEntity<String> postSensor(@RequestBody Sensor sensor) {
        try {
            var savedSensor = sensorRepository.save(sensor);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Location", "/sensors/" + savedSensor.getId());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(String.format("Sensor with ip=%s and port=%s already exists", sensor.getIp(), sensor.getPort()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    //  TODO 4.4  Popis senzora
    @GetMapping(value = "/")
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();

    }

    //  TODO 4.2  Najbliži susjed
    @GetMapping("/closest/{id}")
    public ResponseEntity<SensorDTO> getClosestSensor(@PathVariable Long id) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Sensor> sensors = sensorRepository.findAll();

        var sensorOpt = sensors.stream().
                filter((s) -> !s.getId().equals(id)).
                min((s1, s2) -> Double.compare(haversineFormula(s1, sensor), haversineFormula(s2, sensor)));

        if (sensorOpt.isPresent()) {
            var dto = SensorDTO.builder().
                    ip(sensorOpt.get().getIp()).
                    port(sensorOpt.get().getPort()).
                    latitude(sensorOpt.get().getLatitude()).
                    longitude(sensorOpt.get().getLongitude())
                    .build();
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


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


}
