package by.academy.it.travelcompany.entity;

import by.academy.it.travelcompany.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ComplexObject {
    private ArrayList<Film> films;

    private List<Film> watchedFilms;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;



}
