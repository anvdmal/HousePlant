package houseplant.entity.enums;

public enum SunlightType {
    SHADE("Тень"),
    PARTIAL("Полутень"),
    SUN("Постоянное солнце");

    private final String value;

    SunlightType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
