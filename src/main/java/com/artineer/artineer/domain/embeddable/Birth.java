package com.artineer.artineer.domain.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Birth {
    private String year;
    private String month;
    private String day;

    protected Birth() {
    }

    public static Birth createBirth(String year, String month, String day) {
        return new Birth(year, month, day);
    }
}