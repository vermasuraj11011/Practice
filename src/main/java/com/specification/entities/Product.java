package com.specification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int p_id;
    private String p_name;
    private String category;
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;
}
