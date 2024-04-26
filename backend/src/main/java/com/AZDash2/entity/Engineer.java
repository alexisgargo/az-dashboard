package com.AZDash2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "release_engineers")
public class Engineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_engineer;

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    public Engineer(Long id_engineer) {
        this.id_engineer = id_engineer;
    }

    public Engineer(Long id_engineer,
            @NotNull(message = "Name is mandatory") @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters") String name) {
        this.id_engineer = id_engineer;
        this.name = name;
    }

    public Engineer() {
    }

    public Long getId_engineer() {
        return id_engineer;
    }

    public void setId_engineer(Long id_engineer) {
        this.id_engineer = id_engineer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    }
