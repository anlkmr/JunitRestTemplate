package com.junit.demo.controller.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.junit.demo.model.rest.Employee;
import com.junit.demo.service.rest.RestCallService;

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(value = Rcontroller.class, secure = false)
public class RcontrollerTest {

	List<Employee> original = null;
	
	@Autowired 
	private MockMvc mockMvc;

	@Mock
	private Rcontroller rc;
	
	@MockBean
	private RestCallService rcservice;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		original = new ArrayList<>();
		original.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetEmployees() throws Exception {
		//fail("Not yet implemented"); // TODO
//		Mockito.when(
//				rc.getEmployees()).thenReturn(original);
		Mockito.when(
				rcservice.getEmployees()).thenReturn(original);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "[{id:1, firstName:lokesh, lastName:gupta, email:howtodoinjava@gmail.com}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		
	}

}
