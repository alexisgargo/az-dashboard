package com.AZDash2.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AZDash2.entity.ReleaseHistorical;

@Repository
public interface ReleaseHistoricalRepository extends JpaRepository<ReleaseHistorical, Long> {
    @Query("SELECT r FROM release_historical r WHERE r.record_date = ?1 AND r.id_release = ?2 ORDER BY r.record_time DESC LIMIT 1")
    ReleaseHistorical findByDateBeforeAndIdRelease(Date date, Long idRelease);

}
