package com.sap.trashproject.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity(name="products")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String type;

    private String image;

    private Double price;

    private String description;

    private int quantity;

    @OneToMany(mappedBy = "product")
    private List<EventProduct> eventAssoc;
}