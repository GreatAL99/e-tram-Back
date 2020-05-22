package org.sid.Etram2.Controller;

import java.util.Date;

import java.util.List;


import org.sid.Etram2.Entities.ResGuichet;
import org.sid.Etram2.Entities.Solde;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.ResGuichetRepository;
import org.sid.Etram2.dao.SoldeRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ResGuichetController {
	@Autowired
	private ResGuichetRepository resGuichetRepository;
	@Autowired
	private VoyageurRepository voyageurRepository;
	@Autowired
	private SoldeRepository soldeRepository;
	
	@RequestMapping(value = "/listResponsables",method = RequestMethod.GET)
	public List<ResGuichet> listResponsablesG()
	{
		return resGuichetRepository.getAll();
	}
	@GetMapping(value = "/listResponsables/{cin}")
	public ResGuichet listResponsables(@PathVariable(name = "cin") String cin)
	{
		return resGuichetRepository.findById(cin).get();
	}
	@DeleteMapping(value = "/listResponsables/{cin}")
	public void deleteresGuichet(@PathVariable(name = "cin") String cin)
	{
		resGuichetRepository.deleteById(cin);
	}
	@GetMapping(value = "/alimenter/{cin}&{somme}&{cinR}")
	public void alimenterCompte(@PathVariable(name = "cin") String cin,@PathVariable(name = "somme") double somme,@PathVariable(name = "cinR") String cinR)
	{
		Voyageur voyageur = voyageurRepository.findByUsername(cin);
		ResGuichet responsable = resGuichetRepository.findByUsername(cinR);
		if(voyageur == null) throw new RuntimeException("CIN n'existe pas !");
		else {
			responsable.setSommeVtotale(responsable.getSommeVtotale() + somme);
			resGuichetRepository.save(responsable);
			voyageur.setTramSolde(voyageur.getTramSolde() + somme);
			voyageurRepository.save(voyageur);
			soldeRepository.save(new Solde(null,somme,new Date(),voyageur,responsable));
		}
	}
	@PutMapping(value = "/updateR/{cin}")
	public ResGuichet updateR(@PathVariable(name="cin") String cin, @RequestBody ResGuichet r)
	{
		r.setUsername(cin);
		return resGuichetRepository.save(r);
	}
	@GetMapping(value = "/resGuichet/{cin}")
	public ResGuichet getResBycin(@PathVariable(name="cin") String cin)
	{
		return resGuichetRepository.findByUsername(cin);
	}

	

}
