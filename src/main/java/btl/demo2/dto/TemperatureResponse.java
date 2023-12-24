package btl.demo2.dto;

import btl.demo2.model.Temperature;

public class TemperatureResponse {
    private Iterable<Temperature> data;

    public TemperatureResponse(Iterable<Temperature> data) {
        this.data = data;
    }

    public Iterable<Temperature> getData() {
        return this.data;
    }
}
