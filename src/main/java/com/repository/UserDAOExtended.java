package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDAOExtended extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

}
