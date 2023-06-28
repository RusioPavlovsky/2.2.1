package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    @Column(name = "model")
    private String model;

    @Id
    @Column(name = "series")
    private int series;
    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User owner;

    public Car(){}

    public Car (String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString () {
        return this.model + ", " + this.series;
    }
}
