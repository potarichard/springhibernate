package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_Post")
public class Post {
	
	@Id
	@GeneratedValue
	Integer id;
	
	String content;
	String date;
	
	
	public Post() {	}
	
	public Post(Integer id, String content, String date) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
