package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.entity.MarvelCharacter;

// more here : https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa
public interface MarvelCharacterRepository extends JpaRepository<MarvelCharacter, String>, JpaSpecificationExecutor<MarvelCharacter> {

	// it is just here to see it later
//	@Query(
//		    value = "select mc from MarvelCharacter mc left join fetch mc.team where mc.firstName like ?1",
//		    countQuery = "select count(mc) from MarvelCharacter where mc.firstName like ?1")
//		Page<MarvelCharacter> findByFirstNameFetchingTeam(String firstName, Pageable page);
}
