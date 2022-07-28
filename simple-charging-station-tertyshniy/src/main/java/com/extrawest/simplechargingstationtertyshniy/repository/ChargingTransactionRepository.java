package com.extrawest.simplechargingstationtertyshniy.repository;

import com.extrawest.simplechargingstationtertyshniy.model.ChargingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingTransactionRepository extends JpaRepository<ChargingTransaction, Long> {
}

