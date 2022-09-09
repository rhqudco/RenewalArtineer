package com.artineer.artineer.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Birth {
    private String year;
    private String month;
    private String day;

    protected Birth() {
    }

    public Birth(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}