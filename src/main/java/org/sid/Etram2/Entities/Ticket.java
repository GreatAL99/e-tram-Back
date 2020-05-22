package org.sid.Etram2.Entities;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
@Entity
public class Ticket {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ticket;
	private boolean etat_val;
	private int code_val;
	private double prix;
	@JsonIgnoreProperties("tickets")
	@ManyToOne
	private Horaire horaire;
	@JsonIgnoreProperties("tickets")
	@ManyToOne
	private Voyageur voyageur;
	

}
