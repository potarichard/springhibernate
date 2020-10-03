package com.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public interface UserQuery {

	public Predicate check(UserQ uq, String field, String value, CriteriaBuilder cb);
}
