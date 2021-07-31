package com.heateos.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Table(name = "product")
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Min(value = 1l, message = "Deve ser maior ou igual a 1")
    @NotBlank
    @Column(name = "price")
    private String price;

}