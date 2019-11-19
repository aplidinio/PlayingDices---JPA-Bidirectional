package com.ITAcademy.dices.domains;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {
    
	private String name;
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Id
    private Integer idPlayer;  
	private Date dateRegister;
	@OneToMany (targetEntity = Games.class, mappedBy = "player", cascade = CascadeType.ALL)
	private Set <Games> games = new HashSet <Games>(); //set is also hibernate
	private double rateSuccess;
	
	public Player() {
		
	}
	
	public Player (Integer idPlayer) {
		this.idPlayer = idPlayer;
	}
		
	public Player(String name, Date dateRegister, int rateSuccess) {
		
		this.name = name;
		this.dateRegister = Date.valueOf(LocalDate.now());
		this.rateSuccess = rateSuccess;
	}

	public double getRateSuccess() {
		return rateSuccess;
	}

	public void setRateSuccess(double rateSuccess) {
		this.rateSuccess = rateSuccess;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", idPlayer=" + idPlayer + ", dateRegister=" + dateRegister + ", rateSuccess="
				+ rateSuccess + "]";
	}
	
}
