package com.AZDash2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AZDash2.entity.Release;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long>{
    @Query("SELECT r FROM Release r WHERE r.id_release = :idRelease")
    Release findByIdRelease(@Param("idRelease") Long idRelease);
}
