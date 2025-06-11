package houseplant.dto;

import java.time.LocalDate;
import java.util.UUID;

public class OutputPlantDTO {
    private UUID id;
    private String name;
    private LocalDate plantDate;
    private String plantStatus;
    private String sunlightType;
    private LocalDate lastWatered;
    private int daysSinceWatered;

    public OutputPlantDTO(UUID id, String name, LocalDate plantDate, String plantStatus, String sunlightType, LocalDate lastWatered, int daysSinceWatered) {
        this.id = id;
        this.name = name;
        this.plantDate = plantDate;
        this.plantStatus = plantStatus;
        this.sunlightType = sunlightType;
        this.lastWatered = lastWatered;
        this.daysSinceWatered = daysSinceWatered;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(LocalDate plantDate) {
        this.plantDate = plantDate;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public String getSunlightType() {
        return sunlightType;
    }

    public void setSunlightType(String sunlightType) {
        this.sunlightType = sunlightType;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }

    public int getDaysSinceWatered() {
        return daysSinceWatered;
    }

    public void setDaysSinceWatered(int daysSinceWatered) {
        this.daysSinceWatered = daysSinceWatered;
    }
}
