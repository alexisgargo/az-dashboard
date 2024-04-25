package com.AZDash2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AZDash2.entity.Release;
import com.AZDash2.repository.ReleaseRepository;

@Service
public class ReleaseService {

    @Autowired
    ReleaseRepository releaseRepository;

    public Release getReleaseById(Long idRelease) {
        return releaseRepository.findByIdRelease(idRelease);
    }

    public int sum(int a, int b) {
        return a + b;
    }
}