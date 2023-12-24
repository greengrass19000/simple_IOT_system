package btl.demo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import btl.demo2.dto.TemperatureRes;
import btl.demo2.service.TemperatureService;

@Controller
public class TemperatureController {

    Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    @Autowired
    TemperatureService service;

    @MessageMapping("/temperature")
    @SendTo("/topic/temperature")
    public @ResponseBody TemperatureRes handle(Double value) {
        service.createRecord(value);
        return new TemperatureRes(service.getData());
    }
}
