package org.sid.Etram2.Entities;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity @NoArgsConstructor
public class Admin extends Employee{
	
	public Admin(String username,String nom,String prenom,String adresse,String email,String password,String tel,String role) {
		super(username,nom,prenom,adresse,email,password,tel,role);

     }
}
