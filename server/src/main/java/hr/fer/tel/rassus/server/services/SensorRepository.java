package hr.fer.tel.rassus.server.services;

import hr.fer.tel.rassus.server.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    //  TODO
}
