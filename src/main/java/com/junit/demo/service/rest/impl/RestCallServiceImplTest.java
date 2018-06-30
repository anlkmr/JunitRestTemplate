package com.junit.demo.service.rest.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.demo.model.rest.Employee;
import com.junit.demo.service.rest.RestCallService;

public class RestCallServiceImplTest {

	@Autowired
    RestTemplate restTemplate;
	
	@MockBean
    private ObjectMapper objectMapper;
	
	@MockBean
    RestCallService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		objectMapper = mock(ObjectMapper.class);
		restTemplate = mock(RestTemplate.class);
		service = new RestCallServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetEmployees() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"id\": 1,");
		sb.append(" \"firstName\":\"lokesh\", \"lastName\": \"gupta\", \"email\":\"howtodoinjava@gmail.com\"}");
		//String body = "{"id": 1, "firstName":"lokesh", "lastName": "gupta", "email":"howtodoinjava@gmail.com"}";
		ResponseEntity<String> responseEntityString = new ResponseEntity<String>(sb.toString() , HttpStatus.OK);
		Employee e = new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com");
		
		try {
			Mockito.when(objectMapper.readValue(responseEntityString.getBody().toString(), Employee.class))        
					.thenReturn(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final String ROOT_URI = "http://localhost:8070/v1/test";
		Mockito.when(restTemplate.getForEntity( ROOT_URI, String.class))
                .thenReturn(responseEntityString);
		
		List<Employee> employees2 = service.getEmployees().subList(0, 1);
		//employees2.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		System.out.println(employees2);
		//assertThat(employees2.get(0).getId()).isEqualTo(1);
		assertNotNull(employees2);
		assertEquals(employees2.get(0).getId(), new Integer(1));
		
		
		
		
	}

	

}
