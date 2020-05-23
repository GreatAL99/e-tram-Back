package org.sid.Etram2.Controller;

import java.util.ArrayList;

import java.util.List;


import java.util.concurrent.ThreadLocalRandom;

import org.sid.Etram2.Entities.Horaire;
import org.sid.Etram2.Entities.Ticket;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.HoraireRepository;
import org.sid.Etram2.dao.TicketRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TicketController {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private VoyageurRepository voyageurRepository;
	@Autowired
	private HoraireRepository horaireRepository;
	
	@RequestMapping(value = "/chercherT",method = RequestMethod.GET)
	public Page<Ticket> chercher(@RequestParam(name="date",defaultValue = "")String date,
								 @RequestParam(name="cin",defaultValue = "")String cin,
								 @RequestParam(name="page",defaultValue = "0")int page,
								 @RequestParam(name="size",defaultValue = "5")int size)
	{
	
		Pageable paging =  PageRequest.of(page,size);
		return ticketRepository.chercher(date + "%","%"+cin+"%",paging);
	} 
	@RequestMapping(value = "/listT",method = RequestMethod.GET)
	public List<Ticket> listT()
	{
		return ticketRepository.getAll();
	}
	@RequestMapping(value = "/listTickets",method = RequestMethod.GET)
	public Page<Ticket> listTickets(@RequestParam(name="page",defaultValue = "0")int page,
			  						  @RequestParam(name="size",defaultValue = "5")int size)
	{
		Pageable paging =  PageRequest.of(page,size);
		return ticketRepository.getAll(paging);
	}
	
	@RequestMapping(value = "/listH",method = RequestMethod.GET)
	public List<Horaire> listH()
	{
		return horaireRepository.getAll2();
	}
	
	@GetMapping(value = "/listTickets/{id_ticket}")
	public Ticket listTickets(@PathVariable(name = "id_ticket") Long id_ticket)
	{
		return ticketRepository.findById(id_ticket).get();
	}
	
	@RequestMapping(value = "/deleteTickets",method = RequestMethod.DELETE)
	public void deleteTickets()
	{
		for(Ticket t : listT() ) {
			if(t.isEtat_val()==true) {
				ticketRepository.delete(t);
			}
		}
	}
	
	@GetMapping(value = "/prix")
	public double getPrix()
	{
		Ticket t = new Ticket();
		t= ticketRepository.find();
		return t.getPrix();
	}
	
	@PatchMapping(value = "/listT")
	public void updatePrix(double prix)
	{
		for(Ticket t : listT() ) {
		     t.setPrix(prix);
		     ticketRepository.save(t);
		}
		
	} 
	
	@GetMapping(value="/NbTicketNV")
	public int getNombreTickNV() {
		int i=0;
		for(Ticket t : listT() ) {
		   if(t.isEtat_val()==false) {
			   i = i+1;
		   }  
		}
		return i;
	}
	@GetMapping(value="/NbTicketV")
	public int getNombreTickV() {
		int i=0;
		for(Ticket t : listT() ) {
		   if(t.isEtat_val()==true) {
			   i = i+1;
		   }  
		}
		return i;
	}
	@GetMapping(value="/random")
	public int random() {
		int rand;
		boolean exist;
		do {
			rand = ThreadLocalRandom.current().nextInt(100000,999999);
			exist = false;
			for (Ticket t : listT()) {
				if (t.getCode_val()==rand)
					exist = true;
			}
		}
		while (!exist);
		return rand;
	}
	@GetMapping(value="/som/{cin}")
	public double getS(@PathVariable(name = "cin")String cin) {
		double somme=0;
		for(Ticket t : listT() ) {
		   if(t.getVoyageur().getUsername().equals(cin)) {
			   somme = somme + t.getPrix();
		   }  
		}
		return somme;
	}
	
	@GetMapping(value="/tickets/{cin}")
	public List<Ticket> listTickets(@PathVariable(name = "cin")String cin){
		List<Ticket> list = new ArrayList<>();
		for(Ticket t : listT()) {
			if(t.getVoyageur().getUsername().equals(cin)) {
				list.add(t);
			}
		}
		return list;
	}

	@RequestMapping(value = "/listTicketsVNV",method = RequestMethod.GET)
	public Page<Ticket> listTicketsV( @RequestParam(name="etat_val")boolean etat_val,
									  @RequestParam(name="username")String username,
									  @RequestParam(name="page",defaultValue = "0")int page,
			  						  @RequestParam(name="size",defaultValue = "5")int size)
	{
		Pageable paging =  PageRequest.of(page,size);
		return ticketRepository.getAllVNV(etat_val,username,paging);
	}
	
	@GetMapping(value = "/acheter/{cin}&{nbTickets}")
	public void acheter(@PathVariable(name = "cin") String cin,@PathVariable(name = "nbTickets") int nbTickets) {
		Voyageur v = voyageurRepository.findByUsername(cin);
		double prix  = getPrix();
		if(v.getTramSolde() < prix*nbTickets) {
				throw new RuntimeException("Solde insuffisant");
			}
		else {
			v.setTramSolde(v.getTramSolde() - prix*nbTickets);
			voyageurRepository.save(v);
			for(int i=0;i<nbTickets;i++) {
				ticketRepository.save(new Ticket(null,false,random(),prix,null,v));
				}
			}
		}
	
	@GetMapping(value="/nbtickets/{cin}")
	public int nbTicketsByCin(@PathVariable(name = "cin")String cin){
		int i = 0;
		for(Ticket t : listT()) {
			if(t.getVoyageur().getUsername().equals(cin)) {
				i = i+1;
			}
		}
		return i;
	}
	@GetMapping(value="/NbTicketNvCin/{username}")
	public int getNombreTickNV(@PathVariable(name = "username")String username) {
		int i=0;
		for(Ticket t : listT() ) {
		   if(t.isEtat_val()==false && t.getVoyageur().getUsername().equals(username)) {
			   i = i+1;
		   }  
		}
		return i;
	}
	@GetMapping(value="/NbTicketVCin/{username}")
	public int getNombreTickV(@PathVariable(name = "username")String username) {
		int i=0;
		for(Ticket t : listT() ) {
		   if(t.isEtat_val()==true && t.getVoyageur().getUsername().equals(username)) {
			   i = i+1;
		   }  
		}
		return i;
	}
	@GetMapping(value="/NbTicketTotals/{username}")
	public int getNombreTickTotals(@PathVariable(name = "username")String username) {
		int i=0;
		for(Ticket t : listT() ) {
		   if(t.getVoyageur().getUsername().equals(username)) {
			   i = i+1;
		   }  
		}
		return i;
	}
	
}
