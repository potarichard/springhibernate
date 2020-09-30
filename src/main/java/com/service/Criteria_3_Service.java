package com.service;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.query.EmailQuery;
import com.query.HeightTypeQuery;
import com.query.NameQuery;
import com.query.NumberType;
import com.query.StringType;
import com.query.UserQ;

@Service
public class Criteria_3_Service {

	EntityManager em;

	@Autowired
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<User> findUser(UserQ userquery) 
	{
        CriteriaBuilder cb 		= em.getCriteriaBuilder();
        CriteriaQuery<User> cq 	= cb.createQuery(User.class); 
        userquery.root 			= cq.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        // need reflection userQ fields and values, and use factory for each NameQuery or EmialQuery ....
        // if frontend sets name forexmpl, than need to set nameType if equal or just like it
        // others as well, 
        // but now its just hard coded
        
        // igazabol jo ez a hardcoded igy, nem kell a reflection, csak 3 tipus legyen queryre egy string egy integer es egy double, 
        // sot meg egy boolean, es check all fields, es add the predicate

        Map<String, String> fileds 		= fieldsAndValues(userquery);
        Map<String, Enum<?>> fieldsType = fieldsTypeAndValues(userquery);
        
        doSomething(fileds, fieldsType);
        
        // instead of this, loop on class's fields, check the corresponding userfield to it for the value
        {
            if(userquery.nameType != null) {
            	predicates.add(new NameQuery().check(userquery, userquery.user.getName(), cb));
            }
            if(userquery.emailType != null) {
            	predicates.add(new EmailQuery().check(userquery, userquery.user.getEmail(), cb));
            }
            if(userquery.heightType != null) {
            	predicates.add(new HeightTypeQuery().check(userquery, String.valueOf(userquery.user.getHeight()), cb));
            }
        }

        
        cq.where(predicates.toArray(new Predicate[0]));
 
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
	
	private void doSomething(Map<String, String> fileds, Map<String, Enum<?>> fieldsType) 
	{
		
		fileds.entrySet().forEach(entry ->
		{
			String field = entry.getKey();
			String field_type = entry.getValue().substring(entry.getValue().indexOf("+"));
			
			Optional<Entry<String, Enum<?>>> matchedType = fieldsType.entrySet().stream()
																.filter(e -> e.getKey().startsWith(field))		// thats why it is important to match field+Type
																.findFirst();
			
			if(matchedType.isPresent()) 
			{
				Entry<String, Enum<?>> type = matchedType.get();
								
				
				if(type.getValue() instanceof StringType)
				{
					System.out.println("send info to factory: field is:" + field + ", type: " + field_type);
				}
				
				else if(type.getValue() instanceof NumberType)
				{
					System.out.println("send info to factory: field is:" + field + ", type: " + field_type);
				}
			}
			
		});
		
		System.out.println();		
	}

	// and also get fields .name so can loop, make a factory for string,int, double, and bool type
	// combine with the type
	private Map<String, String> fieldsAndValues(UserQ userquery) 
	{
		User user = userquery.user;
		Field[] allFields = user.getClass().getDeclaredFields();	
		Map<String, String> map = new HashMap<String, String>();
		
		for(Field field : allFields) 
		{				
				try 
				{
					field.setAccessible(true);
					Object value = field.get(user);
					if(value != null)
						map.put(field.getName(), String.valueOf(value) + "+" + field.getType());
				} 				
				
				catch (IllegalArgumentException e) 	{e.printStackTrace();} 
				catch (IllegalAccessException e) 	{e.printStackTrace();}	
		}
		
		return map;
	}
	
	private Map<String, Enum<?>> fieldsTypeAndValues(UserQ userquery) 
	{
		Field[] allFields = userquery.getClass().getDeclaredFields();	
		Map<String, Enum<?>> map = new HashMap<String, Enum<?>>();
		
		for(Field field : allFields) 
		{				
				try 
				{
					field.setAccessible(true);
					Object value = field.get(userquery);
					if(value != null && !field.isAnnotationPresent(Deprecated.class) )
						map.put(field.getName(), (Enum<?>) value);
				} 				
				
				catch (IllegalArgumentException e) 	{e.printStackTrace();} 
				catch (IllegalAccessException e) 	{e.printStackTrace();}	
		}
		
		return map;
	}
	
}









