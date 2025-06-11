package houseplant.dto;

import java.time.LocalDate;

public class InputPlantDTO {
    private String name;
    private LocalDate plantDate;
    private String sunlightType;

    public InputPlantDTO(String name, LocalDate plantDate, String sunlightType) {
        this.name = name;
        this.plantDate = plantDate;
        this.sunlightType = sunlightType;
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

    public String getSunlightType() {
        return sunlightType;
    }

    public void setSunlightType(String sunlightType) {
        this.sunlightType = sunlightType;
    }
}
