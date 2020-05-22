package org.sid.Etram2.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
@Table(name = "horaire")
public class Horaire {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_horaire;
	private String date;
	private String  heure;
	@JsonIgnoreProperties("horaires")
	@ManyToOne 
	private Station station;
	@JsonIgnoreProperties("horaires")
	@ManyToOne
	private Voyage voyage;
	@JsonIgnoreProperties("horaire")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "horaire" )
	private Collection<Ticket> tickets;
}
	

