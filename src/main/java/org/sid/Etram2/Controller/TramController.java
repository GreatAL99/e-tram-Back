package org.sid.Etram2.Controller;

import java.util.List;

import org.sid.Etram2.Entities.Ticket;
import org.sid.Etram2.Entities.Tram;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.TicketRepository;
import org.sid.Etram2.dao.TramRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class TramController {
	@Autowired
	private TramRepository tramRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private VoyageurRepository voyageurRepository;
	
	@RequestMapping(value = "/listTrams",method = RequestMethod.GET)
	public List<Tram> listTrams()
	{
		return tramRepository.getAll();
	}
	@GetMapping(value = "/listTrams/{id_tram}")
	public Tram listTrams(@PathVariable(name = "id_tram") String id_tram)
	{
		return tramRepository.findById(id_tram).get();
	}
	@DeleteMapping(value = "/listTrams/{id_tram}")
	public void deleteTram(@PathVariable(name = "id_tram") String id)
	{
		tramRepository.deleteById(id);
	}
	@PostMapping(value = "/listTrams")
	public Tram addStation(@RequestBody Tram t)
	{
		 return tramRepository.save(t);
	}
	
	@PutMapping(value = "/listTrams/{id_tram}")
	public Tram update(@PathVariable(name="id_tram") String id_tram ,@RequestBody Tram tram)
	{
		tram.setUsername(id_tram);
		return tramRepository.save(tram);
	}
	@GetMapping(value = "/valider/{cin}&{code_val}")
	public void valider(@PathVariable(name = "cin") String cin,@PathVariable(name = "code_val") int code_val)
	{
		Voyageur v = voyageurRepository.findByUsername(cin);
		Ticket ticket = new Ticket();
		int valide = 0;
		if(v == null ) throw new RuntimeException("CIN n'existe pas !");
		else {
			for(Ticket t : v.getTickets()) {
				if(t.getCode_val() == code_val && t.isEtat_val()==false) {
					valide = 1;
					ticket = t;
				}
		}
			if(valide == 1) {
				ticket.setEtat_val(true);
				ticketRepository.save(ticket);
			}
			else {
				throw new RuntimeException("Code n'existe pas !");
			}
		}
	}
}


