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

    // private final ReleaseRepository releaseRepository;

    // @Autowired
    // public ReleaseService(ReleaseRepository releaseRepository) {
    //     this.releaseRepository = releaseRepository;
    // }

    public Release saveRelease(Release release) {
        return releaseRepository.save(release);
    }

    public int updateRelease(Long idRelease, Release release) {
        return releaseRepository.updateReleaseById(idRelease, release);
    }

}

