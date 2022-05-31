package com.gatheringdecks.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gatheringdecks.cards.model.*;

public interface Cards extends JpaRepository<Card, Long> {

}
