package com.query;

import javax.persistence.criteria.Root;

import com.entity.User;



public class UserQ {

	@Deprecated
	public Root<User> root;
	@Deprecated
	public User user;
	
	public StringType nameType;
	public StringType emailType;
	public StringType passwordType;
	
	public NumberType heightType;
	public NumberType weightType;
	public NumberType ageType;

	public StringType currentStringType;	
	public NumberType currentNumberType;
}
