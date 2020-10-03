//package com.query;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.Predicate;
//
//public class NameQuery implements UserQuery {
//
//	@Override
//	public Predicate check(UserQ uq, String name, CriteriaBuilder cb) 
//	{		
//		StringType nametype = uq.nameType;
//		
//		switch (nametype) 
//		{
//			case EQUAL:
//				return cb.equal(uq.root.get("name"), name);
//			case LIKE:
//				return cb.like(uq.root.get("name"), "%" + name + "%");
//		}
//		
//		return null;
//	}
//
//}
