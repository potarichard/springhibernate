package com.service;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserDAO;

// usuall stuff just to see if works

@Service
public class Criteria_0_Service {
	
	UserDAO userdao;
	
	@Autowired
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	@PostConstruct
	public void init() {
		Stream.of(new User("Emma", "emma@email.com", "emmapwd", "emmafood", "white", 170, 60, 20),
				new User("Bela", "bela@email.com", "belapwd", "belafood", "white", 175, 80, 24),
				new User("Joso", "joso@email.com", "josopwd", "josofood", "yellow", 168, 82, 22),
				new User("Eve", "eve@email.com", "evepwd", "evefood", "darkyellowishorange", 192, 101, 29))
		.forEach(u -> userdao.save(u));
	}
	
	public List<User> getall() {
		return userdao.findAll();
	}
}
