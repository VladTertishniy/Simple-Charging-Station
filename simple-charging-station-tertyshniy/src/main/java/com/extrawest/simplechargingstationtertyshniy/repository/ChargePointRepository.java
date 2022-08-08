package com.extrawest.simplechargingstationtertyshniy.repository;

import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {
    Optional<ChargePoint> findByUserAndId(User user, Long id);
}

