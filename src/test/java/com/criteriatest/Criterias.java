package com.criteriatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.awt.PageAttributes.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.HibernateFullApplication;
import com.entity.User;
import com.query.NumberType;
import com.query.StringType;
import com.query.UserQ;
import com.service.Criteria_3_Service;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = { HibernateFullApplication.class })	// as it is an integration test, load the whole application
@AutoConfigureMockMvc
//@TestPropertySource(locations="classpath:application-test.properties")	// this works but dont want to deal with it now
public class Criterias {

	@Autowired
	MockMvc mockmvc;
	
	@Autowired
	Criteria_3_Service c3s;
	
	@Test
	public void testTheService() {
		
		UserQ uq = new UserQ();
		uq.user = new User();
		
		uq.user.setEmail("e");
		uq.emailType = StringType.LIKE;
		
		uq.user.setHeight(170);
		uq.heightType = NumberType.GREATER;		
		
//		c3s.findUser(uq);		
	}
	
	@Test
	public void testTheController() throws Exception {
		
		MvcResult mvc_result =  mockmvc.perform(get("/crit_5")).andReturn();			
		
		System.out.println(mvc_result.getResponse().getContentAsString());
	}
	
}












