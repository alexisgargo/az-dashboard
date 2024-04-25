package com.AZDash2.entity;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "release_historical")
public class ReleaseHistorical {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_release_historical;

    @NotNull(message = "Version is mandatory")
    @Size(min = 1, max = 50, message = "Version must be between 1 and 50 characters")
    private String version;

    @ManyToOne
    @JoinColumn(name = "id_release")
    @NotNull(message = "Release is mandatory")
    private Release release;

    @NotNull(message = "Record date is mandatory")
    @PastOrPresent(message = "Record date must be in the past or present")
    @Column(name = "record_date")
    private Date recordDate;

    @NotNull(message = "Record time is mandatory")
    @PastOrPresent(message = "Record time must be in the past or present")
    @Column(name = "record_time")
    private Time recordTime;

    @NotNull(message = "QA Percent is mandatory")
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_qa;

    @NotNull(message = "UAT Percent is mandatory")
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_uat;

    @NotNull(message = "Third Party Percent is mandatory")
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_third_party;

    @NotNull(message = "PT Percent is mandatory")
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_pt;

    public ReleaseHistorical(String version, Long id_release_historical,
            @NotNull(message = "Release is mandatory") Release release,
            @NotNull(message = "Record date is mandatory") @PastOrPresent(message = "Record date must be in the past or present") Date recordDate,
            @NotNull(message = "Record time is mandatory") @PastOrPresent(message = "Record time must be in the past or present") Time recordTime,
            @NotNull(message = "QA Percent is mandatory") BigDecimal percent_qa,
            @NotNull(message = "UAT Percent is mandatory") BigDecimal percent_uat,
            @NotNull(message = "Third Party Percent is mandatory") BigDecimal percent_third_party,
            @NotNull(message = "PT Percent is mandatory") BigDecimal percent_pt) {
        this.version = version;
        this.id_release_historical = id_release_historical;
        this.release = release;
        this.recordDate = recordDate;
        this.recordTime = recordTime;
        this.percent_qa = percent_qa;
        this.percent_uat = percent_uat;
        this.percent_third_party = percent_third_party;
        this.percent_pt = percent_pt;
    }

    public ReleaseHistorical() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId_release_historical() {
        return id_release_historical;
    }

    public void setId_release_historical(Long id_release_historical) {
        this.id_release_historical = id_release_historical;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Time getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Time recordTime) {
        this.recordTime = recordTime;
    }

    public BigDecimal getPercent_qa() {
        return percent_qa;
    }

    public void setPercent_qa(BigDecimal percent_qa) {
        this.percent_qa = percent_qa;
    }

    public BigDecimal getPercent_uat() {
        return percent_uat;
    }

    public void setPercent_uat(BigDecimal percent_uat) {
        this.percent_uat = percent_uat;
    }

    public BigDecimal getPercent_third_party() {
        return percent_third_party;
    }

    public void setPercent_third_party(BigDecimal percent_third_party) {
        this.percent_third_party = percent_third_party;
    }

    public BigDecimal getPercent_pt() {
        return percent_pt;
    }

    public void setPercent_pt(BigDecimal percent_pt) {
        this.percent_pt = percent_pt;
    }
    
}
