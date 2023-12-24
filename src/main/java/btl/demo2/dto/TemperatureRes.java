package btl.demo2.dto;

import btl.demo2.model.Temperature;

public class TemperatureRes {
    private Iterable<Temperature> data;

    public TemperatureRes(Iterable<Temperature> data) {
        this.data = data;
    }

    public Iterable<Temperature> getData() {
        return this.data;
    }
}
