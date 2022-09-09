package com.artineer.artineer.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Phone {
    private String first;
    private String middle;
    private String last;

    protected Phone() {
    }

    public Phone(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }
}