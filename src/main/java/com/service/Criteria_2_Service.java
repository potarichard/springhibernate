package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;

@Service
public class Criteria_2_Service {

	EntityManager em;

	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<User> findUserByAgeAndHeight(Integer height, Integer age) 
	{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
 
        Root<User> user = cq.from(User.class);
        Predicate heightPredicate 	= cb.greaterThan(user.get("height"), height);
        Predicate agePredicate 		= cb.greaterThan(user.get("age"), age);
        Predicate colorPredicate 	= cb.like(user.get("color"), "%" + "yellow" + "%");
        
        cq.where(heightPredicate, agePredicate, colorPredicate);
 
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
	
	// NEXT is to: write a queryobject, on frontend we can customize it every way, than
	// the object is processed here and it combines all predicetes every possible way (selectors i mean)
	public List<User> findUserByAgeAndHeightV2(Integer height, Integer age)
	{
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	 
	    Root<User> user = cq.from(User.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (height != null) {
	        predicates.add(cb.greaterThan(user.get("height"), height));
	    }
	    if (age != null) {
	        predicates.add(cb.greaterThan(user.get("age"), age));
	    }
	    
//	    cq.where(predicates.toArray(new Predicate[predicates.size()]));		// both works
	    cq.where(predicates.toArray(new Predicate[0]));						// both works
	    
//	    Predicate[] preds = predicates.toArray(new Predicate[0]);
//	    Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);	    
//	    cq.where(preds);
	 
	    return em.createQuery(cq).getResultList();
	}
	
}






