package com.AZDash2.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.AZDash2.entity.ReleaseHistorical;

@Repository
public interface ReleaseHistoricalRepository extends JpaRepository<ReleaseHistorical, Long> {
  @Query("SELECT r FROM ReleaseHistorical r WHERE r.recordDate = :date AND r.release.id_release = :idRelease ORDER BY r.recordTime DESC")
  List<ReleaseHistorical> findByDateAndIdRelease(@Param("date") Date date, @Param("idRelease") Long idRelease);

  @Query("SELECT r FROM ReleaseHistorical r WHERE r.recordDate <= :date AND r.release.id_release = :idRelease ORDER BY r.recordDate DESC, r.recordTime DESC")
  List<ReleaseHistorical> findByDateBeforeAndReleaseIdOrderByRecordDateDescRecordTimeDesc(@Param("date") Date date,
      @Param("idRelease") Long idRelease);

  @Query(value = "SELECT r FROM ReleaseHistorical r WHERE r.release.id_release = :idRelease ORDER BY r.recordDate DESC, r.recordTime DESC")
  List<ReleaseHistorical> findTopByReleaseOrderByRecordDateDescRecordTimeDesc(@Param("idRelease") Long idRelease);
}
