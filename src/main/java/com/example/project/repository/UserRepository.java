package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
