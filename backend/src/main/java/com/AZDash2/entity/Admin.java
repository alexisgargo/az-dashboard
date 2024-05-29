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
  private String admin_name;

  @NotNull(message = "Password is mandatory")
  @Size(min = 1, max = 200, message = "Password must be between 1 and 200 characters")
  @JsonIgnore
  private String admin_password;

  @NotNull(message = "Creation date is mandatory")
  @PastOrPresent(message = "Creation date must be in the past or present")
  private Date creation_date;

  public Admin(
      Long id_admin,
      @NotNull(message = "Admin name is mandatory")
          @Size(min = 1, max = 50, message = "Admin name must be between 1 and 50 characters")
          String admin_name,
      @NotNull(message = "Password is mandatory")
          @Size(min = 1, max = 50, message = "Password must be between 1 and 50 characters")
          String admin_password,
      @NotNull(message = "Creation date is mandatory")
          @PastOrPresent(message = "Creation date must be in the past or present")
          Date creation_date) {
    this.id_admin = id_admin;
    this.admin_name = admin_name;
    this.admin_password = admin_password;
    this.creation_date = creation_date;
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

  public String getAdmin_name() {
    return admin_name;
  }

  public void setAdmin_name(String admin_name) {
    this.admin_name = admin_name;
  }

  public String getAdmin_password() {
    return admin_password;
  }

  public void setAdmin_password(String admin_password) {
    this.admin_password = admin_password;
  }

  public Date getCreation_date() {
    return creation_date;
  }

  public void setCreation_date(Date creation_date) {
    this.creation_date = creation_date;
  }
}
