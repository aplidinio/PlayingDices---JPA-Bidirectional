package com.ITAcademy.dices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ITAcademy.dices.DAO.IGamesDAO;
import com.ITAcademy.dices.domains.Games;
import com.ITAcademy.dices.exceptions.PlayerNotFoundException;
import com.ITAcademy.dices.repositories.IGamesRepository;
import com.ITAcademy.dices.repositories.IPlayerRepository;
import com.ITAcademy.dices.tools.Gamble;

@Service
public class GamesService implements IGamesDAO <Games> {
	
	@Autowired
	private final IGamesRepository gamesRepository;
	private final IPlayerRepository playerRepository;
	private List <Games> games = new ArrayList <Games>();

	public GamesService(IGamesRepository gamesRepository, IPlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
		this.gamesRepository = gamesRepository;
	}
	
	// Get all games
	@Override
	public List <Games> getAllGames() {
		return gamesRepository.findAll();
	}
	
	// Get all games from single player
	@Override
	public List <Games> getPlayerGames(Integer playerIdPlayer) {
		try {
			return gamesRepository.findByPlayerIdPlayer(playerIdPlayer);
		} catch (EmptyResultDataAccessException e) {
			throw new PlayerNotFoundException(Integer.toString(playerIdPlayer));
			}		
	}
	
	// Make game
	@Override
	public Games gambleDices(Integer playerIdPlayer, int numberDices) {
		try {
			Gamble gamble = new Gamble();
			Games newGame = new Games();
			if (numberDices == 2)
				newGame = new Games(gamble.throwDices(numberDices), gamble.winOrFailTwoDices(), playerIdPlayer); 
			else if (numberDices == 6)
				newGame = new Games(gamble.throwDices(numberDices), gamble.winOrFailSixDices(), playerIdPlayer); 
			gamesRepository.save(newGame);
			double success = calculatePlayerSuccess(playerIdPlayer);
			setPlayerSuccess(playerIdPlayer, success);
			return newGame;
		} catch (EmptyResultDataAccessException e) {
			throw new PlayerNotFoundException(Integer.toString(playerIdPlayer));
			}
	}

	// Delete all games from single player
	@Override
	public void deleteGames(Integer playerIdPlayer) {
		try {
			games = getPlayerGames(playerIdPlayer);
			for (Games e : games) {
				gamesRepository.deleteById(e.getIdGame());
			}
			setPlayerSuccess(playerIdPlayer, -1);
		} catch (EmptyResultDataAccessException e) {
			throw new PlayerNotFoundException(Integer.toString(playerIdPlayer));
			}			
	}

	// Calculate player Success
	@Override
	public double calculatePlayerSuccess(Integer playerIdPlayer) {
		int success = 0;
		double rateSuccess = 0;
		games = getPlayerGames(playerIdPlayer);
		for (Games e : games) {
			if (e.getResult())
				success++;
		}
		if (games.size() != 0)
			rateSuccess = (success*100/games.size());
		else 
			rateSuccess = -1;
		return rateSuccess;
		
	}
	// Save player Ratesuccess
	@Override
	public void setPlayerSuccess (Integer playerIdPlayer, double success) {
		playerRepository.findById(playerIdPlayer)
			.map(player -> {
				player.setRateSuccess(success);
				return playerRepository.save(player);
			})
			.orElseThrow(() -> {
			    return new PlayerNotFoundException(Integer.toString(playerIdPlayer));
		 }); 
		
	}

}
