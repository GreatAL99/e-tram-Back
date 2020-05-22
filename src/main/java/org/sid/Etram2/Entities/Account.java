package org.sid.Etram2.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Entity
public class Account {
	
	@Id   
	@Column(name = "username", unique = true, length = 7)
	private String username;
	private String password;
	private String role;
	
	
	
}
