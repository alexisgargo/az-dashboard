package com.AZDash2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AZDash2.entity.Engineer;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long>{

}
