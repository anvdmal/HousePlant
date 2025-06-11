package houseplant.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "watering")
public class Watering extends BaseEntity {
    private Plant plant;
    private LocalDate wateredDate;

    public Watering(Plant plant, LocalDate wateredDate) {
        this.plant = plant;
        this.wateredDate = wateredDate;
    }

    protected Watering() {
    }

    @ManyToOne(targetEntity = Plant.class)
    @JoinColumn(name = "plant_id", referencedColumnName = "id", nullable = false)
    public Plant getPlant() {
        return plant;
    }

    @Column(name = "time", nullable = false)
    public LocalDate getWateredDate() {
        return wateredDate;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void setWateredDate(LocalDate wateredDate) {
        this.wateredDate = wateredDate;
    }
}