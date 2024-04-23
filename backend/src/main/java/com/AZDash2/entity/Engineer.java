package com.AZDash2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "release_engineers")
public class Engineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_engineer;
    private String name;

    public Engineer() {
    }

    public Engineer(Long id, String name) {
        this.id_engineer = id;
        this.name = name;
    }

    public Engineer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id_engineer;
    }

    public void setId(Long id) {
        this.id_engineer = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
