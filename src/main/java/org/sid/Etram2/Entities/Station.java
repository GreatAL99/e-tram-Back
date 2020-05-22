package org.sid.Etram2.Entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Station {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String adresse;
	@JsonIgnoreProperties("station")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "station" )
	private Collection<Horaire> horaires;
}