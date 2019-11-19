package com.ITAcademy.dices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ITAcademy.dices.domains.Games;

@Repository
public interface IGamesRepository extends JpaRepository <Games, Integer> {
	
	public List <Games> findByPlayerIdPlayer (Integer playerIdPlayer);
	
}
