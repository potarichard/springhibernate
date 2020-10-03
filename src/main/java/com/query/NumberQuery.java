package com.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class NumberQuery implements UserQuery {

	@Override
	public Predicate check(UserQ uq, String field, String value, CriteriaBuilder cb) {
		
		NumberType numbertype = uq.currentNumberType;		
		Integer convertedValue = Integer.valueOf(value);
		
		switch (numbertype) 
		{
			case EQUAL:
				return cb.equal(uq.root.get(field), convertedValue);
			case GREATER:
				return cb.greaterThan(uq.root.get(field), convertedValue);
			case GREATEREQUAL:
				return cb.greaterThanOrEqualTo(uq.root.get(field), convertedValue);
			case LESSER:
				return cb.lessThan(uq.root.get(field), convertedValue);
			case LESSEREQUAL:
				return cb.lessThanOrEqualTo(uq.root.get(field), convertedValue);	
				
//			case BETWEEN: ilyen nem lesz egyelore
//				return 	
		}
		
		return null;
	}



}
