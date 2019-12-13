package com.sap.trashproject.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name="events")
@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String image;

    @OneToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}