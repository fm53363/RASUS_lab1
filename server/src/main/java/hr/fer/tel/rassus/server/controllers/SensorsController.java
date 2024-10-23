package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.beans.Sensor;
import hr.fer.tel.rassus.server.services.SensorRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorsController {

    private final SensorRepository sensorRepository;

    public SensorsController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    //  TODO 4.1  Registracija
    @PostMapping(value = "/")
    public ResponseEntity<?> postSensor(@RequestBody Sensor sensor) {
        try {
            var savedSensor = sensorRepository.save(sensor);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(savedSensor.getUrl());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //  TODO 4.4  Popis senzora

    //  TODO 4.2  Najbliži susjed

}
