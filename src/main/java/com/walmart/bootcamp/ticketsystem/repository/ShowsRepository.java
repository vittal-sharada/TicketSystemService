package com.walmart.bootcamp.ticketsystem.repository;

import com.walmart.bootcamp.ticketsystem.model.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowsRepository extends JpaRepository<Shows, Long> {

        Shows findByShowName(String showName);


}
