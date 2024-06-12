package com.AZDash2.repository;

import com.AZDash2.entity.Release;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
  // @Query("SELECT r.name, r.version FROM Release r WHERE r.status = 'progress'")
  // List<Object[]> findNamesAndVersionsByStatus();
  @Query("SELECT r FROM Release r WHERE r.id_release = :idRelease")
  Release findByIdRelease(@Param("idRelease") Long idRelease);

  @Query("SELECT COUNT(r.is_hotfix) FROM Release r WHERE r.is_hotfix = true AND r.creation_date >= :startDate AND r.curr_release_date <= :endDate AND r.curr_release_date <= :currentDate")
  Long countByIsHotfix(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
      @Param("currentDate") Date currentDate);

  @Query("SELECT COUNT(r.is_rollback) FROM Release r WHERE r.is_rollback = true AND r.creation_date >= :startDate AND r.curr_release_date <= :endDate AND r.curr_release_date <= :currentDate")
  Long countByIsRollback(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
      @Param("currentDate") Date currentDate);

  @Query("SELECT COUNT(r.id_release) FROM Release r WHERE r.is_rollback = false AND r.creation_date >= :startDate AND r.curr_release_date <= :endDate AND r.curr_release_date <= :currentDate")
  Long countByIsNotHotfixAndIsNotRollback(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
      @Param("currentDate") Date currentDate);

  List<Release> findByStatus(String status);

  @Transactional
  @Modifying
  @Query("UPDATE Release r SET r.name = :#{#release.name}, r.version = :#{#release.version}, " +
      "r.code_cutoff = :#{#release.code_cutoff}, r.init_release_date = :#{#release.init_release_date}, " +
      "r.curr_release_date = :#{#release.curr_release_date}, r.creation_date = :#{#release.creation_date}, " +
      "r.last_modification_date = :#{#release.last_modification_date}, r.is_hotfix = :#{#release.is_hotfix}, " +
      "r.status = :#{#release.status}, r.is_rollback = :#{#release.is_rollback}, r.release_note = :#{#release.release_note}, "
      +
      "r.engineer = :#{#release.engineer} " +
      "WHERE r.id_release = :idRelease")
  int updateReleaseById(Long idRelease, Release release);
}
