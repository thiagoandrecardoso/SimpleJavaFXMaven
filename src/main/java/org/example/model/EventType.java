package main.java.org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "eventType")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Events events;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.ORDINAL)
    public EnumEventTypes enumEventTypes;

    public EnumEventTypes getEventTypes() {
        return enumEventTypes;
    }

    public void setEventTypes(EnumEventTypes enumEventTypes) {
        this.enumEventTypes = enumEventTypes;
    }
}
