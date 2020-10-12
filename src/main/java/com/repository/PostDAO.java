package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Post;

public interface PostDAO extends JpaRepository<Post, Integer>{

}
