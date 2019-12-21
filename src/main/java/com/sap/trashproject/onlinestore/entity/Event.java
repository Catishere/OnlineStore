package com.sap.trashproject.onlinestore.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

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

    @OneToMany(mappedBy = "event")
    private List<EventProduct> productAssoc;

    private boolean active = false;
}