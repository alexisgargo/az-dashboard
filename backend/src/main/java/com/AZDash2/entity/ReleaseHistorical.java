package com.AZDash2.entity;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "release_historical")
public class ReleaseHistorical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_release_historical;

    @ManyToOne
    @JoinColumn(name = "id_release")
    private Release release;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "record_time")
    private Time recordTime;

    @Column(precision = 10, scale = 0)
    private BigDecimal percent_qa;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_uat;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_third_party;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_pt;
    public ReleaseHistorical(Long id_release_historical, Release release, Date recordDate, Time recordTime,
            BigDecimal percent_qa, BigDecimal percent_uat, BigDecimal percent_third_party, BigDecimal percent_pt) {
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
