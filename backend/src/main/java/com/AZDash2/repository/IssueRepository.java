package com.AZDash2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.sql.Time;
import com.AZDash2.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("SELECT MAX(i.recordTime) FROM Issue i WHERE i.recordDate = :date AND i.release.id_release = :idRelease")
    Time findLatestTimeByDateAndIdRelease(@Param("date") Date date, @Param("idRelease") Long idRelease);

    @Query("SELECT i FROM Issue i WHERE i.recordDate = :date AND i.release.id_release = :idRelease AND i.recordTime = :time")
    List<Issue> findByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
            @Param("time") Time time);
}

