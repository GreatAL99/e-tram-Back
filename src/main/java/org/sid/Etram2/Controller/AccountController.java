package org.sid.Etram2.Controller;

import org.sid.Etram2.Entities.Account;
import org.sid.Etram2.Entities.Admin;
import org.sid.Etram2.Entities.ResGuichet;
import org.sid.Etram2.Entities.Tram;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.AdminRepository;
import org.sid.Etram2.dao.ResGuichetRepository;
import org.sid.Etram2.dao.TramRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AccountController {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private VoyageurRepository voyageurRepository;
	@Autowired
	private ResGuichetRepository resGuichetRepository;
	@Autowired
	private TramRepository tramRepository;

	
	@GetMapping(value="/login/{cin}&{password}")
	public Account getUsernamePassword(@PathVariable(name = "cin") String cin,@PathVariable(name = "password") String password) {
		Account a1 =  new Voyageur();
		Account a2 =  new Admin();
		Account a3 =  new ResGuichet();
		Account a4 =  new Tram();
		
		Voyageur voyageur = voyageurRepository.findByUsername(cin);
		
		if(voyageur == null) {
			Admin admin = adminRepository.findByUsername(cin);
			if(admin == null) {
				ResGuichet res = resGuichetRepository.findByUsername(cin);
				if(res == null) {
					Tram tram = tramRepository.findByUsername(cin);
					if(tram == null ) {
						throw new RuntimeException("Identifiant n'existe pas !");
					}
					else {
						if(!tram.getPassword().equals(password)) {
							throw new RuntimeException("Mot de passe ne correspond pas");
						}
						else {
							a4 = tramRepository.getTram(cin, password);
							return a4;
						}
					}
				}
				else {
					if(!res.getPassword().equals(password)) {
						throw new RuntimeException("Mot de passe ne correspond pas");
					}
					else {
						a3 = resGuichetRepository.getResGuichet(cin, password);
						return a3;
					}
				}
			}
			else {
				if(!admin.getPassword().equals(password)) {
					throw new RuntimeException("Mot de passe ne correspond pas");
				}
				else {
					a2 = adminRepository.getAdmin(cin, password);
					return a2;
				}
			}
		}
		else {
			if(!voyageur.getPassword().equals(password)) {
				throw new RuntimeException("Mot de passe ne correspond pas");
			}
			else {
				a1 = voyageurRepository.getVoyageur(cin, password);
				return a1;
			}
		}
	}

}
