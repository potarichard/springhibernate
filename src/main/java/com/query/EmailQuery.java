package com.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

// eleg egy fajta StringTypeQuery es a field nevet atadni stringben

public class EmailQuery implements UserQuery {

	@Override
	public Predicate check(UserQ uq, String name, CriteriaBuilder cb) 
	{		
		StringType emailtype = uq.emailType;
		
		switch (emailtype) 
		{
			case EQUAL:
				return cb.equal(uq.root.get("email"), name);
			case LIKE:
				return cb.like(uq.root.get("email"), "%" + name + "%");
		}
		
		return null;
	}

}