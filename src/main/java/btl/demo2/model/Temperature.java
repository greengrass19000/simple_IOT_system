package btl.demo2.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "temperatures")
public class Temperature {
    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    private Double value;

    @CreationTimestamp
    private Instant createdAt;

    public Integer getId() {
        return this.id;
    }

    public Double getValue() {
        return this.value;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
