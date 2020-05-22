package org.sid.Etram2.Entities;

import java.util.Collection;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class ResGuichet extends Employee{
	private double SommeVtotale;
	@JsonIgnoreProperties("resGuichet")
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "resGuichet" )
	private Collection<Solde> soldes;
	
	public ResGuichet(String username,String nom,String prenom,String adresse,String email,String password,String tel,String role,
			double sommeVtotale,Collection<Solde> soldes) {
		super(username,nom,prenom,adresse,email,password,tel,role);
		this.SommeVtotale = sommeVtotale;
		this.soldes = soldes;
     }
	

}
