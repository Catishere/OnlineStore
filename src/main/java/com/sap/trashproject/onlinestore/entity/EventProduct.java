package com.sap.trashproject.onlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "event_product")
@IdClass(EventProductId.class)
@Data
@NoArgsConstructor
@Getter
public class EventProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @Column(name = "price")
    Double price;

    public EventProduct(Event event, Product product, Double price) {
        this.event = event;
        this.product = product;
        this.price = price;
    }
}