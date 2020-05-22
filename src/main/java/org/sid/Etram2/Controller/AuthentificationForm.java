package org.sid.Etram2.Controller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthentificationForm {
	private String cin;
	private String password;
}
