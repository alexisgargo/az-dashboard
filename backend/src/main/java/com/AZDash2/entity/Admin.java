package com.AZDash2.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "admins")
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_admin;

    @NotNull(message = "Admin name is mandatory")
    @Size(min = 1, max = 50, message = "Admin name must be between 1 and 50 characters")
    @Column(name = "admin_name")  
    private String username;

    @NotNull(message = "Password is mandatory")
    @Size(min = 1, max = 200, message = "Password must be between 1 and 200 characters")
    @Column(name = "admin_password")
    private String adminPassword;

    @NotNull(message = "Creation date is mandatory")
    @PastOrPresent(message = "Creation date must be in the past or present")
    @Column(name = "creation_date")
    private Date creationDate;

    public Admin(Long id_admin,
            @NotNull(message = "Admin name is mandatory") @Size(min = 1, max = 50, message = "Admin name must be between 1 and 50 characters") String admin_name,
            @NotNull(message = "Password is mandatory") @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters") String admin_password,
            @NotNull(message = "Creation date is mandatory") @PastOrPresent(message = "Creation date must be in the past or present") Date creation_date) {
        this.id_admin = id_admin;
        this.username = username;
        this.adminPassword = adminPassword;
        this.creationDate = creationDate;
    }

  public Admin(Long id_admin) {
    this.id_admin = id_admin;
  }

  public Admin() {}

  public Long getId_admin() {
    return id_admin;
  }

  public void setId_admin(Long id_admin) {
    this.id_admin = id_admin;
  }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonGetter
    public String getAdminPassword() {
        return adminPassword;
    }

    @JsonSetter
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
