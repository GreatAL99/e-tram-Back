package org.sid.Etram2.Entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Entity
public class Employee extends Utilisateur{

	
	public Employee(String username,String nom,String prenom,String adresse,String email,String password,String tel,String role) {
		super(username,nom,prenom,adresse,email,password,tel,role);
		

	}
	
}
