package houseplant.entity.enums;

public enum PlantStatus {
    ALIVE("ALIVE"),
    WILTED("WILTED");
    private final String value;

    PlantStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}