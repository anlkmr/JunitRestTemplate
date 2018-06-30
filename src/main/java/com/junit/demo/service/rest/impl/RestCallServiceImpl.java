package com.junit.demo.service.rest.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.demo.model.rest.Employee;
import com.junit.demo.service.rest.RestCallService;

@Service
public class RestCallServiceImpl implements RestCallService {

	@Autowired 
	RestTemplateAutoConfiguration restTemplateconfig;

	final String ROOT_URI = "http://localhost:8070/v1/test";

	
	@Override
	public List<Employee> getEmployees() {
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<Employee> resemployeesList = null;
		List<Employee> emplistreturn = new ArrayList<>();
		// TODO Auto-generated method stub
		//employeesList.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		//RestTemplate rt = restTemplateconfig.restTemplateBuilder().build();
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> js = rt.getForEntity(ROOT_URI, String.class);
		try {
			Employee e = mapper.readValue(js.getBody().toString(), Employee.class);
			emplistreturn.add(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emplistreturn;
		
	}

}
