package com.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class StringQuery implements UserQuery {

	@Override
	public Predicate check(UserQ uq, String field, String value, CriteriaBuilder cb) {
		
		StringType nametype = uq.currentStringType;
		
		switch (nametype) 
		{
			case EQUAL:
				return cb.equal(uq.root.get(field), value);
			case LIKE:
				return cb.like(uq.root.get(field), "%" + value + "%");
		}
		
		return null;
	}



}
