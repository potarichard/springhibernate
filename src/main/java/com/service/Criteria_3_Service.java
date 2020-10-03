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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.query.NumberQuery;
import com.query.NumberType;
import com.query.StringQuery;
import com.query.StringType;
import com.query.UserQ;

// if this wasnot on the classpath, then EntityManager em couldnot be autowired
//@Repository
//public interface UserDAO extends JpaRepository<User, Integer>{}

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

        Map<String, String> fileds 		= fieldsAndValues(userquery);
        Map<String, Enum<?>> fieldsType = fieldsTypeAndValues(userquery);
        
        predicates = makeQueries(fileds, fieldsType, cb, userquery);
        
        cq.where(predicates.toArray(new Predicate[0]));
 
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }
	
	
	
	
	
	
	private List<Predicate> makeQueries(Map<String, String> fileds, Map<String, Enum<?>> fieldsType, CriteriaBuilder cb, UserQ userquery) 
	{
		List<Predicate> predicates = new ArrayList<>();
		
		fileds.entrySet().forEach(entry ->
		{
			String field = entry.getKey();
			String field_value = entry.getValue().substring(0, entry.getValue().indexOf("+"));
//			String field_type = entry.getValue().substring(entry.getValue().indexOf("+"));
			
			Optional<Entry<String, Enum<?>>> matchedType = fieldsType.entrySet().stream()
																.filter(e -> e.getKey().startsWith(field))		// thats why it is important to match field+Type
																.findFirst();
			
			if(matchedType.isPresent()) 
			{
				Entry<String, Enum<?>> type = matchedType.get();								
				
				//ezt attenni factoryba, vagy kitalalni valami jo polymorphizmust
				
				if(type.getValue() instanceof StringType)
				{
					userquery.currentStringType = (StringType) type.getValue();
					predicates.add(new StringQuery().check(userquery, field, field_value, cb));
				}
				
				else if(type.getValue() instanceof NumberType)		// azon belul lehet Integer, Float, Double ?
				{
					userquery.currentNumberType = (NumberType) type.getValue();
					predicates.add(new NumberQuery().check(userquery, field, field_value, cb));
				}
				
//				else if(type.getValue() instanceof BoolType) ilyen is kene
			}
			
		});
		
		return predicates;		
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
					if(value != null && !field.isAnnotationPresent(Deprecated.class) )		// irni kene sajat annotaciot
						map.put(field.getName(), (Enum<?>) value);
				} 				
				
				catch (IllegalArgumentException e) 	{e.printStackTrace();} 
				catch (IllegalAccessException e) 	{e.printStackTrace();}	
		}
		
		return map;
	}
	
}









