package com.AZDash2.entity;

import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime record_date;

    @Column(precision = 10, scale = 0)
    private BigDecimal percent_qa;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_uat;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_third_party;
    @Column(precision = 10, scale = 0)
    private BigDecimal percent_pt;

    public ReleaseHistorical(Long id_release_historical, Release release, LocalDateTime record_date,
            BigDecimal percent_qa, BigDecimal percent_uat, BigDecimal percent_third_party, BigDecimal percent_pt) {
        this.id_release_historical = id_release_historical;
        this.release = release;
        this.record_date = record_date;
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

    public LocalDateTime getRecord_date() {
        return record_date;
    }

    public void setRecord_date(LocalDateTime record_date) {
        this.record_date = record_date;
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
