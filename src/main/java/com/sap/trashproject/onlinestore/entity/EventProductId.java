package com.sap.trashproject.onlinestore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class EventProductId implements Serializable {
    private Long event;
    private Long product;
}
