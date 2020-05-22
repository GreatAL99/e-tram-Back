package org.sid.Etram2.Controller;

import java.util.Date;
import java.util.List;

import org.sid.Etram2.Entities.Solde;
import org.sid.Etram2.dao.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SoldeController {
	@Autowired
	private SoldeRepository soldeRepository;
	
	@RequestMapping(value = "/chercherS",method = RequestMethod.GET)
	public Page<Solde> chercher( @RequestParam(name="cin",defaultValue = "")String cin,
								 @RequestParam(name="page",defaultValue = "0")int page,
								 @RequestParam(name="size",defaultValue = "5")int size)
	{
	
		Pageable paging =  PageRequest.of(page,size);
		return soldeRepository.chercher("%"+cin+"%",paging);
	} 
	@RequestMapping(value = "/listS",method = RequestMethod.GET)
	public List<Solde> listS()
	{
		return soldeRepository.getAll();
	}
	@RequestMapping(value = "/listSoldes",method = RequestMethod.GET)
	public Page<Solde> listSoldes(@RequestParam(name="page",defaultValue = "0")int page,
			  						  @RequestParam(name="size",defaultValue = "5")int size)
	{
		Pageable paging =  PageRequest.of(page,size);
		return soldeRepository.getAll2(paging);
	}
	
	@GetMapping(value = "/listSoldes/{id_solde}")
	public Solde listSoldes(@PathVariable(name = "id_solde") Long id_solde)
	{
		return soldeRepository.findById(id_solde).get();
	}
	
	@GetMapping(value = "/sommeVt")
	public double getSommeVt(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,String cin)
	{
		double somme = 0;
		for(Solde s : listS() ) {
			if(s.getDate().compareTo(date) == 0 && s.getResGuichet().getUsername().equals(cin))
				somme = somme + s.getSomme();
		}
		return somme;
	}
}
