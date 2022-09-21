package com.artineer.artineer.domain.embeddable;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Phone {
    private String firstNumber;
    private String middleNumber;
    private String lastNumber;

    protected Phone() {
    }

    public Phone(String firstNumber, String middleNumber, String lastNumber) {
        this.firstNumber = firstNumber;
        this.middleNumber = middleNumber;
        this.lastNumber = lastNumber;
    }
}