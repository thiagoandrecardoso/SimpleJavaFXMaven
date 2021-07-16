package main.java.org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Date dateEvent;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "events")
    private Artist artist;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "events")
    private Cities circle;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "events")
    private EventType eventType;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Cities getCircle() {
        return circle;
    }

    public void setCircle(Cities circle) {
        this.circle = circle;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
