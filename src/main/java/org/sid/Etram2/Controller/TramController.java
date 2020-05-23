package org.sid.Etram2.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.sid.Etram2.Entities.Horaire;
import org.sid.Etram2.Entities.Ticket;
import org.sid.Etram2.Entities.Tram;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.HoraireRepository;
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
	@Autowired
	private HoraireRepository horaireRepository;
	
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
	
	//RE-DO

	public int comparer(String h1, String hn) {
		return Integer.parseInt(hn.substring(0, 2)) * 60 + Integer.parseInt(hn.substring(3, 5))
				- (Integer.parseInt(h1.substring(3, 5)) + 60 * Integer.parseInt(h1.substring(0, 2)));
	}
	
	@GetMapping(value = "/valider/{code}&{idtram}")
	public void valider(@PathVariable(name = "code") int code, @PathVariable(name = "idtram") String idtram) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateN = dtf.format(now).substring(0, 10);
		String heureN = dtf.format(now).substring(11, 16);
		int trouve = 0;
		int min = 100000;
		Long idmin = null;

		for (Ticket t : ticketRepository.findAll()) {
			if (t.getCode_val() == code) {
				trouve = 1;
				t.setEtat_val(true);
				t.setCode_val(0);
				for (Horaire h : horaireRepository.findAll()) {
					if (h.getDate().equals(dateN) && (((h.getVoyage()).getTram()).getUsername()).equals(idtram)
							&& h.getHeure().compareTo(heureN) <= 0 && comparer(h.getHeure(), heureN) < min) {
						idmin = h.getId_horaire();
						min = comparer(h.getHeure(), heureN);
					}
				}
				if (idmin != null) {
					t.setHoraire(horaireRepository.getOne(idmin));
					ticketRepository.save(t);
					//return "Ticket Validé";
					throw new RuntimeException("Ticket Validé");
				}
				else {
					throw new RuntimeException("Aucun horaire n'est disponible!");
				}
			}
		}
		//return "Code erroné! Veuillez vérifier votre code SVP.";
		if (trouve==0)
			throw new RuntimeException("Code erroné! Veuillez vérifier votre code SVP.");
	
	}
}
