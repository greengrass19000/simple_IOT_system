package btl.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import btl.demo2.dto.TemperatureResponse;
import btl.demo2.service.TemperatureService;

@RestController
public class TemperatureController {

    @Autowired
    TemperatureService service;

    @GetMapping("/")
    public @ResponseBody TemperatureResponse getData() {
        return new TemperatureResponse(service.getData());
    }
}
