package com.extrawest.simplechargingstationtertyshniy.repository;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}

