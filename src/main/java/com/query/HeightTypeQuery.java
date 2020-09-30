package com.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
 
public class HeightTypeQuery implements UserQuery {

	@Override
	public Predicate check(UserQ uq, String value, CriteriaBuilder cb) 
	{		
		NumberType heighttype = uq.heightType;
		Integer height = Integer.valueOf(value);
		
		switch (heighttype) 
		{
			case EQUAL:
				return cb.equal(uq.root.get("height"), height);
			case GREATER:
				return cb.greaterThan(uq.root.get("height"), height);
			case GREATEREQUAL:
				return cb.greaterThanOrEqualTo(uq.root.get("height"), height);
			case LESSER:
				return cb.lessThan(uq.root.get("height"), height);
			case LESSEREQUAL:
				return cb.lessThanOrEqualTo(uq.root.get("height"), height);		
		}
		
		return null;
	}

}
