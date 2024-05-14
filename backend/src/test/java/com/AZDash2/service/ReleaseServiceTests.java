package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.ReleaseRepository;

@SpringBootTest
public class ReleaseServiceTests {

    @Mock
    ReleaseRepository releaseRepository;

    @InjectMocks
    ReleaseService releaseService;


    @Test
    void testSum() {
        int a = 1;
        int b = 2;

        int expected = 3;
        int result = 3;

        assertEquals(result, expected);
    }

    // @Test
    // void testGetReleaseById() {
    //     // given
    //     Engineer engineer = new Engineer(1L, "John Doe");
    //     Date date = Date.valueOf("2021-01-01");
    //     Admin admin = new Admin(1L, "Jane Doe", "123", date);
    //     Release expectedRelease = new Release(1L, "AZPRO", "1.0", engineer, admin, date, date,
    //             date, date, date, true, "In Progress", true, "Release Note");
    //     given(releaseRepository.findByIdRelease(1L)).willReturn(expectedRelease);

    //     // when 
    //     Long idRelease = 1L;
    //     Release actualRelease = releaseService.getReleaseById(idRelease);

    //     // then
    //     assertEquals(expectedRelease, actualRelease);

    // }

    // void testWrongGetReleaseById() {
    //     // given
    //     Engineer engineer = new Engineer(1L, "John Doe");
    //     Date date = Date.valueOf("2021-01-01");
    //     Admin admin = new Admin(1L, "Jane Doe", "123", date);
    //     Release expectedRelease = new Release(1L, "AZPRO", "1.0", engineer, admin, date, date,
    //             date, date, date, true, "In Progress", true, "Release Note");
    //     given(releaseRepository.findByIdRelease(1L)).willReturn(expectedRelease);

    //     // when 
    //     Long idRelease = 2L;
    //     Exception exception = assertThrows(, null);

    //     // then
    //     assertNull(exception);
    // }
}


/*
 * 1. comprobar que dando un id de release, se obtiene el release correcto
 * 2. comprobar que dando un id de release que no existe, se obtiene null o el mensaje de erros
 * 3. comprobar que dando un id 
 * 4. 
 */