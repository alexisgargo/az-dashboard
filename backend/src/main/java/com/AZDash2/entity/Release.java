package com.AZDash2.entity;

import jakarta.persistence.*;
import java.sql.Date;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "releases")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_release;

    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Label is mandatory")
    @Size(min = 1, max = 50, message = "Label must be between 1 and 50 characters")
    private String label;

    @ManyToOne
    @JoinColumn(name = "id_engineer")
    @NotNull(message = "Engineer is mandatory")
    private Engineer engineer;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    @NotNull(message = "Admin is mandatory")
    private Admin admin;

    @NotNull(message = "Code cutoff is mandatory")
    private Date code_cutoff;

    @NotNull(message = "Init Date is mandatory")
    private Date init_release_date;

    @NotNull(message = "Curr Date is mandatory")
    private Date curr_release_date;

    @NotNull(message = "Creation Date is mandatory")
    private Date creation_date;

    @NotNull(message = "Last Modification Date is mandatory")
    private Date last_modification_date;

    @NotNull(message = "Is Hotfix is mandatory")
    @Column(columnDefinition = "BIT(1)")
    private boolean is_hotfix;

    @NotNull(message = "Status is mandatory")
    @Size(min = 1, max = 50, message = "Status must be between 1 and 50 characters")
    private String status;

    @NotNull(message = "Is Rollback is mandatory")
    @Column(columnDefinition = "BIT(1)")
    private boolean is_rollback;

    @Size(min = 1, max = 140, message = "Release note must be between 1 and 140 characters")
    private String release_note;

    public Release(Long id_release,
            @NotNull(message = "Name is mandatory") @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters") String name,
            @NotNull(message = "Label is mandatory") @Size(min = 1, max = 50, message = "Label must be between 1 and 50 characters") String label,
            @NotNull(message = "Engineer is mandatory") Engineer engineer,
            @NotNull(message = "Admin is mandatory") Admin admin,
            @NotNull(message = "Code cutoff is mandatory") Date code_cutoff,
            @NotNull(message = "Init Date is mandatory") Date init_release_date,
            @NotNull(message = "Curr Date is mandatory") Date curr_release_date,
            @NotNull(message = "Creation Date is mandatory") Date creation_date,
            @NotNull(message = "Last Modification Date is mandatory") Date last_modification_date,
            @NotNull(message = "Is Hotfix is mandatory") boolean is_hotfix,
            @NotNull(message = "Status is mandatory") @Size(min = 1, max = 50, message = "Status must be between 1 and 50 characters") String status,
            @NotNull(message = "Is Rollback is mandatory") boolean is_rollback,
            @Size(min = 1, max = 140, message = "Release note must be between 1 and 140 characters") String release_note) {
        this.id_release = id_release;
        this.name = name;
        this.label = label;
        this.engineer = engineer;
        this.admin = admin;
        this.code_cutoff = code_cutoff;
        this.init_release_date = init_release_date;
        this.curr_release_date = curr_release_date;
        this.creation_date = creation_date;
        this.last_modification_date = last_modification_date;
        this.is_hotfix = is_hotfix;
        this.status = status;
        this.is_rollback = is_rollback;
        this.release_note = release_note;
    }

    public Release() {
    }

    public Long getId_release() {
        return id_release;
    }

    public void setId_release(Long id_release) {
        this.id_release = id_release;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getCode_cutoff() {
        return code_cutoff;
    }

    public void setCode_cutoff(Date code_cutoff) {
        this.code_cutoff = code_cutoff;
    }

    public Date getInit_release_date() {
        return init_release_date;
    }

    public void setInit_release_date(Date init_release_date) {
        this.init_release_date = init_release_date;
    }

    public Date getCurr_release_date() {
        return curr_release_date;
    }

    public void setCurr_release_date(Date curr_release_date) {
        this.curr_release_date = curr_release_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getLast_modification_date() {
        return last_modification_date;
    }

    public void setLast_modification_date(Date last_modification_date) {
        this.last_modification_date = last_modification_date;
    }

    public boolean isIs_hotfix() {
        return is_hotfix;
    }

    public void setIs_hotfix(boolean is_hotfix) {
        this.is_hotfix = is_hotfix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIs_rollback() {
        return is_rollback;
    }

    public void setIs_rollback(boolean is_rollback) {
        this.is_rollback = is_rollback;
    }

    public String getRelease_note() {
        return release_note;
    }

    public void setRelease_note(String release_note) {
        this.release_note = release_note;
    }

}