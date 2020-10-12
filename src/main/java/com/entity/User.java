package com.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="TB_User")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)			// works if askd by id
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)	// what does it mean?
public class User {

	@Id
	@GeneratedValue
	Integer id;
	
	String name;
	String email;
	String password;
	String favorite_food;
	String color;
	Integer height;
	Integer weight;
	Integer age;
	
	
	
	public User(String name, String email, String password, String favorite_food, String color, Integer height,
			Integer weight, Integer age) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.favorite_food = favorite_food;
		this.color = color;
		this.height = height;
		this.weight = weight;
		this.age = age;
	}
	
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFavorite_food() {
		return favorite_food;
	}
	public void setFavorite_food(String favorite_food) {
		this.favorite_food = favorite_food;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
