package com.food.ordering.system.domain.valueObject;

import java.util.UUID;

import com.food.ordering.system.valueObject.BaseId;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID id) {
        super(id);
    }

}
