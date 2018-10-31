package com.walmart.bootcamp.ticketsystem.repository;

import com.walmart.bootcamp.ticketsystem.model.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface ShowsRepository extends JpaRepository<Shows, String> {

}
