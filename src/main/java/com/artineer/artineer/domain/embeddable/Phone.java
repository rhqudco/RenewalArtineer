package com.artineer.artineer.domain.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
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