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

import com.entity.Post;

@Service
public class PostCriteriaService {

	EntityManager em;

	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<Post> findPosts() 
	{
        CriteriaBuilder cb 		= em.getCriteriaBuilder();
        CriteriaQuery<Post> cq 	= cb.createQuery(Post.class); 
        Root<Post> root			= cq.from(Post.class);
        
        List<Predicate> predicates = new ArrayList<>();             

        
        
        cq.where(predicates.toArray(new Predicate[0]));
 
        TypedQuery<Post> query = em.createQuery(cq);
        return query.getResultList();
    }
	
	public List<Post> findPostsJoinUser() {
		return null;		
	}
	
	
}

















