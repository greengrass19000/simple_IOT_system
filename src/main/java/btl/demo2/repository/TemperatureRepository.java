package btl.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import btl.demo2.model.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

}
