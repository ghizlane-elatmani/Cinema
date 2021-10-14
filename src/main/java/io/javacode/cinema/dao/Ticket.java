package io.javacode.cinema.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameClient;
    private double prix;
    private int codePaiement;
    private boolean reserve;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;

}