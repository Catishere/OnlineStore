package com.sap.trashproject.onlinestore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity(name="store_account")
@Data
@Getter
@NoArgsConstructor
public class StoreAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Double money;
    Date date;

    public StoreAccount(Double money, Date date) {
        this.money = money;
        this.date = date;
    }
}
