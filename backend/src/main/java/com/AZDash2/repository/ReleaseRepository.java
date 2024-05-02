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
    //@Query("SELECT r.name, r.version FROM Release r WHERE r.status = 'progress'")
    //List<Object[]> findNamesAndVersionsByStatus();
 
    List<Release> findByStatus(String status);


}
