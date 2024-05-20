package com.AZDash2.repository;

import com.AZDash2.entity.Release;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
  // @Query("SELECT r.name, r.version FROM Release r WHERE r.status = 'progress'")
  // List<Object[]> findNamesAndVersionsByStatus();
  @Query("SELECT r FROM Release r WHERE r.id_release = :idRelease")
  Release findByIdRelease(@Param("idRelease") Long idRelease);

  @Query("SELECT r.id_release FROM Release r WHERE r.curr_release_date >= :date AND r.creation_date <= :date")
  List<Long> findReleasesByReleaseDateAfterAndCreationDateBefore(Date date);

  List<Release> findByStatus(String status);

};
