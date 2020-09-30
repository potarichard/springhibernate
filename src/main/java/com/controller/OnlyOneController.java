package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.query.NumberType;
import com.query.StringType;
import com.query.UserQ;
import com.service.Criteria_0_Service;
import com.service.Criteria_1_Service;
import com.service.Criteria_2_Service;
import com.service.Criteria_3_Service;

// focusing mainly findall, but with clever criterias!

@RestController
@RequestMapping("/")
public class OnlyOneController {

	@Autowired
	Criteria_0_Service c0s;
	@Autowired
	Criteria_1_Service c1s;
	@Autowired
	Criteria_2_Service c2s;	
	@Autowired
	Criteria_3_Service c3s;


	// normal way, get all
	@GetMapping("/all")
	public List<User> getUsersCriteria() {
		return c0s.getall();
	}
	
	
	@GetMapping("/crit_1")
	public List<User> getUsersCriteria_1() {
		return c1s.getAllByCriteria1("Emma");
	}
	
	@GetMapping("/crit_2")
	public List<User> getUsersCriteria_2() {
		return c1s.getAllByCriteria2("Bela", 23);
	}
	
	@GetMapping("/crit_3")
	public List<User> getUsersCriteria_3() {
		return c2s.findUserByAgeAndHeight(169, 23);
	}
	
	@GetMapping("/crit_4")
	public List<User> getUsersCriteria_4() {
		return c2s.findUserByAgeAndHeightV2(169, 23);
	}
	
	@GetMapping("/crit_5")
	public List<User> getUsersCriteria_5() {
		
		UserQ uq = new UserQ();
		uq.user = new User();
		
		uq.user.setEmail("e");
		uq.emailType = StringType.LIKE;
		
		uq.user.setHeight(170);
		uq.heightType = NumberType.GREATER;
		
		return c3s.findUser(uq);
	}
}

























