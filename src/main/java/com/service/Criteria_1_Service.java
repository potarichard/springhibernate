package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.Specification;
import com.repository.UserDAOExtended;

// using spec custom interface in the dao!

@Service
public class Criteria_1_Service {

	@Autowired
	UserDAOExtended userdaoext;
	
	 
	Specification<User> colorIsLike(String color) {
	    return (user, cq, cb) -> cb.like(user.get("color"), "%" + color + "%");
	}
	
	
	public List<User> getAllByCriteria1(String name) {
		return userdaoext.findAll((user, cq, cb) -> cb.equal(user.get("name"), name));
	}
	
	public List<User> getAllByCriteria2(String name, Integer age) {
		return userdaoext.findAll((user, cq, cb) -> 
		{
			// not nesceserry, as the user! got all
			{
				CriteriaQuery<User> criteria = cb.createQuery(User.class);			
				Root<User> root = criteria.from(User.class);			
				Set<Attribute<? super User, ?>> attributes = root.getModel().getAttributes();			
				attributes.forEach(atr -> System.out.println(atr.getName()));
			}
			
			
			return cb.greaterThan(user.get("age"), age);
		});
	}
	
	 
//	public List<User> getAllByCriteria3(String name, Integer age) {
//		return userdaoext.findAll(where(colorIsLike(name)).and);
//	}
}
 





















