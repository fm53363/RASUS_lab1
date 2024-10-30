package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.entity.Reading;
import hr.fer.tel.rassus.server.entity.Sensor;
import hr.fer.tel.rassus.server.services.ReadingRepository;
import hr.fer.tel.rassus.server.services.SensorRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("readings")
public class ReadingController {

    private final SensorRepository sensorRepository;
    private final ReadingRepository readingRepository;

    public ReadingController(SensorRepository sensorRepository, ReadingRepository readingRepository) {
        this.sensorRepository = sensorRepository;
        this.readingRepository = readingRepository;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Reading> getSensorById(@PathVariable("id") Long id) {
        Optional<Reading> reading = readingRepository.findById(id);
        return reading.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // TODO 4.3  Spremanje očitanja pojedinog senzora
    @PostMapping(value = "/{id}")
    public ResponseEntity<?> postReading(@PathVariable Long id, @RequestBody Reading reading) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        reading.setSensor(sensor);
        Reading savedReading = readingRepository.save(reading);
        sensor.getReadings().add(savedReading);
        sensorRepository.save(sensor);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "/readings/" + savedReading.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    // TODO 4.5  Popis očitanja pojedinog senzora
    @GetMapping(value = "sensor/{id}")
    public ResponseEntity<List<Reading>> getSensorReadings(@PathVariable("id") Long id) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(id);
        return optionalSensor.map(opt -> new ResponseEntity<>(opt.getReadings(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}