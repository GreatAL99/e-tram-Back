package org.sid.Etram2.Entities;


import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voyage {
	@Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_voyage;
	private String ligneTram;
	@JsonIgnoreProperties("voyage")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "voyage")
	private Collection<Horaire> horaires;
	@JsonIgnoreProperties("voyages")
	@ManyToOne 
	private Tram tram;

}
