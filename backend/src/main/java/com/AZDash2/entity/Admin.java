package com.AZDash2.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;
    private String admin_name;
    private String admin_password;
    private Date creation_date;

    public Admin(Long id_admin, String admin_name, String admin_password, Date creation_date) {
        this.id_admin = id_admin;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.creation_date = creation_date;
    }

    public Admin() {
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
