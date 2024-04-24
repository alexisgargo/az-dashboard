package com.AZDash2.service;

import com.AZDash2.entity.Release;
import com.AZDash2.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {

    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Release saveRelease(Release release) {
        return releaseRepository.save(release);
    }
}
