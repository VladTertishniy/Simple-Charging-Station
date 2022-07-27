package com.extrawest.simplechargingstationtertyshniy.repository;

import com.extrawest.simplechargingstationtertyshniy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
