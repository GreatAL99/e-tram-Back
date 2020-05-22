package org.sid.Etram2.Entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Utilisateur extends Account {
	
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String tel;
	
	public Utilisateur(String username,String nom,String prenom,String adresse,String email,String password,String tel,String role) {
		super(username,password,role);
		this.nom=nom;
		this.prenom=prenom;
		this.adresse=adresse;
		this.email=email;
		this.tel=tel;
	}
	
	
}
