package org.sid.Etram2.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Voyageur1 {
	private String username;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String tel;
	private String password;
	private String repassword;
}
