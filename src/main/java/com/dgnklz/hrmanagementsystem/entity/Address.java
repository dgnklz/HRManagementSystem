package com.dgnklz.hrmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="region")
    private String region;

    @Column(name="zipCode")
    private int zipCode;
}
