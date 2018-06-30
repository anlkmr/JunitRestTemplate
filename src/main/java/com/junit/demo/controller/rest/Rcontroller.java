package com.junit.demo.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.demo.model.rest.Employee;
import com.junit.demo.service.rest.RestCallService;

@RestController
public class Rcontroller 
{
	@Autowired
	private RestCallService rCallService;
	
	@RequestMapping("/v1")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = rCallService.getEmployees();
		
		return employeesList;
    }
}