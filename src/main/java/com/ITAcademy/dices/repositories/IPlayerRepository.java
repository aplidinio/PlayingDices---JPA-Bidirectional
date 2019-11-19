package com.ITAcademy.dices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ITAcademy.dices.domains.Player;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer>{

}

