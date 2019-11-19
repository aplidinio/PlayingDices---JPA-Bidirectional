package com.ITAcademy.dices;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ITAcademy.dices.domains.Games;
import com.ITAcademy.dices.domains.Player;
import com.ITAcademy.dices.repositories.IGamesRepository;
import com.ITAcademy.dices.repositories.IPlayerRepository;

@Configuration
public class LoadDatabase {
	
	@Bean
	  CommandLineRunner initDatabase(IPlayerRepository playerRepository, IGamesRepository gamesRepository) {
	    return args -> {
	      System.out.println("Preloading Data to memory Database");
	      Player newPlayer = new Player("Poker De Ases", Date.valueOf(LocalDate.now()), 100);
	      ArrayList <Integer> dices = new ArrayList <Integer>();
	      dices.add(2); 
	      dices.add(5); 
	      Games newGame = new Games(dices, true, 1);
	      List <Games> newGameList = new ArrayList <Games>();
	      newGameList.add(newGame);
	          
	          
	      playerRepository.save(newPlayer);
	      gamesRepository.save(newGame);
		
	      System.out.println("Data loaded");
	      
	    };
	  }
 
}
