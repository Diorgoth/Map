package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer stateNumber;

    @Column(nullable = false)
    private Integer madeYear;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    private Region region;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userss user;



}
