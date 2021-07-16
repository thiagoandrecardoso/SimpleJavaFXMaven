package main.java.org.example.model;

public enum EnumEventTypes {
    MUSIC_SHOW("Show de Música"),
    THEATER("Teatro"),
    RALLIES("Comicios"),
    SPORTS("Esportes");

    private final String event;

    private EnumEventTypes(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return this.event;
    }
}
