package com.AZDash2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.service.IssueService;
import com.AZDash2.valueobject.Issue;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SpringBootTest
class AzDash2ApplicationTests {
	@Autowired
	IssueService issue;
	
	@Test
	void lastIssueCommentTest() throws URISyntaxException, IOException, InterruptedException {

		Issue expected = new Issue("DAS-21", null, null,"Ignacio Soto" , "Pull ticket's last comment", "María del Cielo Ramírez Zavala", "2024-04-09T09:24:25.765-0600", "Unfinished", "perro 2");
		
		List<Issue> result = issue.getAllBugsOrIssues("%20in%20(story%2C%20task)","DAS");

		Issue issue13 = result.get(12);

		assertEquals(expected, issue13, "Se esperaba que regresara el ultimo elemento de la lista");
		
	}
	
}


