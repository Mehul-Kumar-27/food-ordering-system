package com.food.ordering.system.domain.valueObject;

import java.util.UUID;

public class StreetAddress {
    private final UUID id;
    public UUID getId() {
        return id;
    }

    private final String street;
    public String getStreet() {
        return street;
    }

    private final String city;
    public String getCity() {
        return city;
    }

    private final String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public StreetAddress(UUID id, String street, String city, String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

}
