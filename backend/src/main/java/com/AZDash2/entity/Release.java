package com.AZDash2.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "releases")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_release;
    private String name;
    private String label;

    @ManyToOne
    @JoinColumn(name = "id_engineer")
    private Engineer engineer;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Admin admin;

    private Date code_cutoff;
    private Date init_release_date;
    private Date curr_release_date;
    private Date creation_date;
    private Date last_modification_date;

    @Column(columnDefinition = "BIT(1)")
    private boolean is_hotfix;

    private String status;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean is_rollback;

    private String release_note;

    public Release(Long id_release, String name, String label, Engineer engineer, Admin admin, Date code_cutoff,
            Date init_release_date, Date curr_release_date, Date creation_date, Date last_modification_date,
            boolean is_hotfix, String status, boolean is_rollback, String release_note) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_release == null) ? 0 : id_release.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((engineer == null) ? 0 : engineer.hashCode());
        result = prime * result + ((admin == null) ? 0 : admin.hashCode());
        result = prime * result + ((code_cutoff == null) ? 0 : code_cutoff.hashCode());
        result = prime * result + ((init_release_date == null) ? 0 : init_release_date.hashCode());
        result = prime * result + ((curr_release_date == null) ? 0 : curr_release_date.hashCode());
        result = prime * result + ((creation_date == null) ? 0 : creation_date.hashCode());
        result = prime * result + ((last_modification_date == null) ? 0 : last_modification_date.hashCode());
        result = prime * result + (is_hotfix ? 1231 : 1237);
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + (is_rollback ? 1231 : 1237);
        result = prime * result + ((release_note == null) ? 0 : release_note.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Release other = (Release) obj;
        if (id_release == null) {
            if (other.id_release != null)
                return false;
        } else if (!id_release.equals(other.id_release))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (engineer == null) {
            if (other.engineer != null)
                return false;
        } else if (!engineer.equals(other.engineer))
            return false;
        if (admin == null) {
            if (other.admin != null)
                return false;
        } else if (!admin.equals(other.admin))
            return false;
        if (code_cutoff == null) {
            if (other.code_cutoff != null)
                return false;
        } else if (!code_cutoff.equals(other.code_cutoff))
            return false;
        if (init_release_date == null) {
            if (other.init_release_date != null)
                return false;
        } else if (!init_release_date.equals(other.init_release_date))
            return false;
        if (curr_release_date == null) {
            if (other.curr_release_date != null)
                return false;
        } else if (!curr_release_date.equals(other.curr_release_date))
            return false;
        if (creation_date == null) {
            if (other.creation_date != null)
                return false;
        } else if (!creation_date.equals(other.creation_date))
            return false;
        if (last_modification_date == null) {
            if (other.last_modification_date != null)
                return false;
        } else if (!last_modification_date.equals(other.last_modification_date))
            return false;
        if (is_hotfix != other.is_hotfix)
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (is_rollback != other.is_rollback)
            return false;
        if (release_note == null) {
            if (other.release_note != null)
                return false;
        } else if (!release_note.equals(other.release_note))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Release [id_release=" + id_release + ", name=" + name + ", label=" + label + ", engineer=" + engineer
                + ", admin=" + admin + ", code_cutoff=" + code_cutoff + ", init_release_date=" + init_release_date
                + ", curr_release_date=" + curr_release_date + ", creation_date=" + creation_date
                + ", last_modification_date=" + last_modification_date + ", is_hotfix=" + is_hotfix + ", status="
                + status + ", is_rollback=" + is_rollback + ", release_note=" + release_note + "]";
    }

    

}