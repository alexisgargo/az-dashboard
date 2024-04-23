package com.AZDash2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.entity.Engineer;

import java.util.List;


@Service
public class EngineerService {
    @Autowired
    EngineerRepository engineerRepository;

    public void saveEngineer(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    public List<Engineer> getEngineers() {
        return engineerRepository.findAll();
    }

}
