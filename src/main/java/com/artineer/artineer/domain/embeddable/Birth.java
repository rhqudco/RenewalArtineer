package com.artineer.artineer.domain.embeddable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
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