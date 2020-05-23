package org.sid.Etram2.Controller;

import java.util.ArrayList;



import java.util.Collection;
import java.util.List;


import org.sid.Etram2.Entities.Ticket;
import org.sid.Etram2.Entities.Voy;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.Entities.Voyageur1;
import org.sid.Etram2.dao.TicketRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class VoyageurController {
	@Autowired
	private VoyageurRepository voyageurRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@RequestMapping(value = "/listVoyageurs",method = RequestMethod.GET)
	public List<Voyageur> listVoyageurs()
	{
		return voyageurRepository.getAll();
	}
	@GetMapping(value = "/listVoyageurs/{cin}")
	public Voyageur getVoyageur(@PathVariable(name = "cin") String cin)
	{
		return voyageurRepository.findById(cin).get();
	}
	@GetMapping(value = "/NbTickets")
	public int NbTickets()
	{
		Voyageur v = new Voyageur();
		v= voyageurRepository.find();
		return v.getNbTickets();
	}
	@DeleteMapping(value = "/listVoyageurs/{cin}")
	public void deleteVoyageur(@PathVariable(name = "cin") String cin)
	{
			voyageurRepository.deleteById(cin);
	}
	@GetMapping(value = "/verefier")
	public List<Voyageur> verefierAvantSupprimer()
	{
		List<Voyageur> list = new ArrayList<Voyageur>();
		List<Ticket> tickets = ticketRepository.getAll();
		boolean bool  =false;
		for(Ticket ticket : tickets) {
			if(Boolean.compare(ticket.isEtat_val(),bool)==0 ) {
				list.add(ticket.getVoyageur());
			}
		}

		return list;
	}
	@GetMapping(value = "/verefier/{cin}")
	public int verefierAvantSupprimer(@PathVariable(name = "cin") String cin)
	{
		Voyageur v = voyageurRepository.getOne(cin);
		Collection<Ticket> tickets = v.getTickets();
		boolean bool  =false;
		int supprimer=1;
		for(Ticket ticket : tickets) {
			if(Boolean.compare(ticket.isEtat_val(),bool)==0) {
				supprimer=0;
				break;
			}
		}

		if(supprimer==0) {
			return 1;
		}
		else
			return 0;
	}
	@GetMapping(value = "/assurer/{cin}")
	public List<Voyageur> assurer(@PathVariable(name = "cin") String cin)
	{
		List<Voyageur> list1 = voyageurRepository.getAll();
		List<Voyageur> list2 = verefierAvantSupprimer();
		List<Voyageur> list3 = new ArrayList<Voyageur>();
		for(Voyageur v1 : list1) {
				if(!list2.contains(v1) && v1.getUsername().equals(cin)) {
					list3.add(v1);
				}
			}
	
	return list3;
	}
	@PatchMapping(value = "/listVoyageurs")
	public void updateNbTickets(int nb)
	{
		for(Voyageur v : listVoyageurs() ) {
		     v.setNbTickets(nb);
		     voyageurRepository.save(v);
		}
		
	} 
	@GetMapping(value = "/solde/{cin}&{tramSolde}")
	public void updateSoldeTram(@PathVariable(name = "cin") String cin,@PathVariable(name = "tramSolde") double tramSolde)
	{
		for(Voyageur v : listVoyageurs() ) {
		     if(v.getUsername().equals(cin)) {
		    	 v.setTramSolde(v.getTramSolde() + tramSolde);
		    	 voyageurRepository.save(v);
		    
		     }
	     }
	
	} 
	@GetMapping(value = "/voyageur/{cin}")
	public Voyageur getVoyageurBycin(@PathVariable(name="cin") String cin)
	{
		return voyageurRepository.findByUsername(cin);
	}
	@PutMapping(value = "/updateV/{cin}")
	public Voyageur updateV(@PathVariable(name="cin") String cin, @RequestBody Voyageur v)
	{
		v.setUsername(cin);
		return voyageurRepository.save(v);
	}
	
	@PostMapping(value = "/lista")
	public Voyageur1 addVoyageur(@RequestBody Voyageur1 v)
	{
		String username  = v.getUsername();
		String nom = v.getNom();
		String prenom = v.getPrenom();
		String password = v.getPassword();
		String repassword = v.getRepassword();
		String adresse = v.getAdresse();
		String email = v.getEmail();
		String tel = v.getTel();

		Voyageur voyageur = voyageurRepository.findByUsername(username);
		
		if(voyageur != null ) throw new RuntimeException("Ce CIN est déja exsisté !");
		else {
		if (!password.equals(repassword)) throw new RuntimeException("Le mot de passe doit etre la meme !");
		else
			voyageurRepository.save(new Voyageur(username,nom,prenom,adresse,tel,password,email,"voyageur",0,11,10,null,null));
		return v;
		}
	}
	@PostMapping(value="/changeMdp")
	public Voyageur changePassword(@RequestBody Voy v){
		System.out.println(v.getUsername());
		Voyageur voyageur = voyageurRepository.findByUsername(v.getUsername());
		if(voyageur == null ) throw new RuntimeException("Ce CIN n'existe pas !");
		else {
			if(!voyageur.getPassword().equals(v.getPassword())) throw new RuntimeException("Password ne correspond pas  !");
			else {
				if(!v.getNewPassword().equals(v.getReNewPassword())) throw new RuntimeException("Password doit etre le meme!");
				else {
					voyageur.setPassword(v.getNewPassword());
					voyageurRepository.save(voyageur);
					return voyageur;
				}
			}
			
		}
	}
	
	
}


