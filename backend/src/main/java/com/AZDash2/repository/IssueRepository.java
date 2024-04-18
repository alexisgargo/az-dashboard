package com.AZDash2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AZDash2.entity.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("SELECT p FROM Issue p WHERE p.date <= ?1 AND p.idRelease = ?2")
    List<Issue>findByDateBeforeAndIdRelease(Date date, Long idRelease);
    
}

