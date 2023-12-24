package btl.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import btl.demo2.model.Temperature;
import btl.demo2.repository.TemperatureRepository;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureRepository repo;

    public Iterable<Temperature> getData() {
        return repo.findAll();
    }
}
