package com.AZDash2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.AZDash2.entity.Engineer;
import com.AZDash2.service.EngineerService;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EngineerControllerTest {

  private EngineerService engineerService;
  private EngineerController engineerController;

  @BeforeEach
  public void setup() {
    engineerService = mock(EngineerService.class);
    engineerController = new EngineerController();

    try {
      Field field = engineerController.getClass().getDeclaredField("engineerService");
      field.setAccessible(true);
      field.set(engineerController, engineerService);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetEngineers() {
    List<Engineer> expectedEngineers = new ArrayList<>();
    expectedEngineers.add(new Engineer(1L, "John Doe"));
    expectedEngineers.add(new Engineer(2L, "Jane Smith"));

    when(engineerService.getEngineers()).thenReturn(expectedEngineers);

    ResponseEntity<List<Engineer>> response = engineerController.getEngineers();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedEngineers, response.getBody());
  }
}
