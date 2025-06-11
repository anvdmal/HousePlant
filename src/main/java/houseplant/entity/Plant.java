package houseplant.entity;

import houseplant.entity.enums.PlantStatus;
import houseplant.entity.enums.SunlightType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plants")
public class Plant extends BaseEntity {
    private String name;
    private LocalDate plantDate;
    private PlantStatus plantStatus;
    private SunlightType sunlightType;
    private List<Watering> wateringHistory;

    public Plant(String name, LocalDate plantDate, SunlightType sunlightType) {
        this.name = name;
        this.plantDate = plantDate;
        this.plantStatus = PlantStatus.ALIVE;
        this.sunlightType = sunlightType;
        this.wateringHistory = new ArrayList<>();
    }

    protected Plant() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "plant_date")
    public LocalDate getPlantDate() {
        return plantDate;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public PlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(PlantStatus status) {
        this.plantStatus = status;
    }

    @Column(name = "sunlight")
    @Enumerated(EnumType.STRING)
    public SunlightType getSunlightType() {
        return sunlightType;
    }

    @OneToMany(mappedBy = "plant", targetEntity = Watering.class)
    public List<Watering> getWateringHistory() {
        return wateringHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlantDate(LocalDate plantDate) {
        this.plantDate = plantDate;
    }

    public void setSunlightType(SunlightType sunlight) {
        this.sunlightType = sunlight;
    }

    public void setWateringHistory(List<Watering> wateringHistory) {
        this.wateringHistory = wateringHistory;
    }

    public void markAsAlive() {
        if (this.plantStatus == PlantStatus.ALIVE) {
            throw new IllegalStateException("Растение итак живое.");
        }
        this.plantStatus = PlantStatus.ALIVE;
    }

    public void markAsWilted() {
        if (this.plantStatus == PlantStatus.WILTED) {
            throw new IllegalStateException("Растение уже увяло.");
        }
        this.plantStatus = PlantStatus.WILTED;
    }
}