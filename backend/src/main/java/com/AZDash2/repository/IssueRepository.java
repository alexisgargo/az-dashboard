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
    @Query("SELECT MAX(i.record_time) FROM Issue i WHERE i.record_date <= :date AND i.release.id_release = :idRelease")
    Time findLatestTimeByDateAndIdRelease(@Param("date") Date date, @Param("idRelease") Long idRelease);

    @Query("SELECT i FROM Issue i WHERE i.record_date <= :date AND i.release.id_release = :idRelease AND i.record_time <= :time")
    List<Issue> findByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
            @Param("time") Time time);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.record_date <= :date AND i.release.id_release = :idRelease AND i.record_time <= :time AND i.is_feature = true  ")
    long countTicketByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
        @Param("time") Time time);

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.record_date <= :date AND i.release.id_release = :idRelease AND i.record_time <= :time AND i.is_feature = false AND i.close_date IS  NULL")
    long countBugByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
     @Param("time") Time time);

     @Query("SELECT i FROM Issue i WHERE i.record_date = :date AND i.release.id_release = :idRelease AND i.record_time <= :time AND i.is_feature = true  ")
     List<Issue> findIssuesByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
         @Param("time") Time time);

     @Query("SELECT i FROM Issue i WHERE i.record_date = :date AND i.release.id_release = :idRelease AND i.record_time <= :time AND i.is_feature = false AND i.close_date IS  NULL")
        List<Issue> findBugByDateAndIdReleaseAndTime(@Param("date") Date date, @Param("idRelease") Long idRelease,
          @Param("time") Time time);

    @Query("SELECT i FROM Issue i WHERE i.issue_number = :issueNumber")
    List<Issue> findByIssueNumber(@Param("issueNumber") String issueNumber);

}

