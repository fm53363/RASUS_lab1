package hr.fer.tel.rassus.server.services;

import hr.fer.tel.rassus.server.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    //  TODO
}
