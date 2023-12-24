package btl.demo2.model;

public class Temperature {
    long id;
    double value;
    long createdAt;

    public Temperature(double value, long createdAt) {
        this.value = value;
        this.createdAt = createdAt;
    }
}
