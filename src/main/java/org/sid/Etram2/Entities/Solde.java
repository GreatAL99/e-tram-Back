package org.sid.Etram2.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Solde {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_solde;
	private double somme;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	@JsonIgnoreProperties("soldes")
	@ManyToOne
	private Voyageur voyageur;
	@JsonIgnoreProperties("soldes")
	@ManyToOne
	private ResGuichet resGuichet;
}
