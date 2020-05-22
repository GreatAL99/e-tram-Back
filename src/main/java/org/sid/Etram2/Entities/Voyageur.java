package org.sid.Etram2.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data @NoArgsConstructor
@Entity
public class Voyageur extends Utilisateur {
	private double tramSolde;
	private int carteB;
	private int nbTickets;
	@JsonIgnoreProperties("voyageur")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "voyageur" )
	private Collection<Solde> soldes;
	@JsonIgnoreProperties("voyageur")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "voyageur" )
	private Collection<Ticket> tickets;
	public Voyageur(String username,String nom,String prenom,String adresse,String tel,String password,String email,String role,
			double tramSolde, int carteB, int nbTickets, Collection<Solde> soldes, Collection<Ticket> tickets) {
		super(username,nom,prenom,adresse,tel,password,email,role);
		this.tramSolde = tramSolde;
		this.carteB = carteB;
		this.nbTickets = nbTickets;
		this.soldes = soldes;
		this.tickets = tickets;
	}
	

}
