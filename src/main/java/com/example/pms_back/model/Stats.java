package com.example.pms_back.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "stats", schema = "public")
public class Stats{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;

    @Column(name="user_id")
    private String userId;

    @Column(name="date")
    private ZonedDateTime date;

    @Column(name="duration")
    private int duration;

    public Stats() {}

    public Stats(String id, String userId, ZonedDateTime date, int duration) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.duration = duration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Stats fill(Stats stats) {
        if (stats.getDate() != null)
            this.setDate(stats.getDate());
        this.setDuration(stats.getDuration());

        return this;
    }
}

