package com.extrawest.simplechargingstationtertyshniy.repository;

import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, Long> {
}

