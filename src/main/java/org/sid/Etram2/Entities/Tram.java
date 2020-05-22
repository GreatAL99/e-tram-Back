package org.sid.Etram2.Entities;



import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class Tram extends Account {
	private String nom;
	private String type;
	private String etat_fonc;
	@OneToMany(mappedBy = "tram")
	private Collection<Voyage> voyages;
	public Tram(String username, String password,String role,String nom, String type, String etat, Collection<Voyage> voyages) {
		super(username,password,role);
		this.type=type;
		this.etat_fonc=etat;
		this.voyages=voyages;
	}
}
