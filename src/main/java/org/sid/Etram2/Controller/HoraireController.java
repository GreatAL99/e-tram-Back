package org.sid.Etram2.Controller;


import org.sid.Etram2.Entities.Horaire;

import org.sid.Etram2.dao.HoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class HoraireController {
	@Autowired
	private HoraireRepository horaireRepository;
	
	@RequestMapping(value = "/chercher",method = RequestMethod.GET)
	public Page<Horaire> chercher(@RequestParam(name="date",defaultValue = "")String date,
								  @RequestParam(name="page",defaultValue = "0")int page,
								  @RequestParam(name="size",defaultValue = "5")int size)
	{
	
		Pageable paging =  PageRequest.of(page,size);
		return horaireRepository.chercher(date + "%",paging);
	} 
	@RequestMapping(value="/chercherH",method=RequestMethod.GET)
	public Page<Horaire> chercherH(@RequestParam(name="ligne",defaultValue="")String ligne,
		                       	  @RequestParam(name="station",defaultValue="")String station,
			                      @RequestParam(name="date",defaultValue="")String date,
			                      @RequestParam(name="page",defaultValue="0")int page,
			                      @RequestParam(name="size",defaultValue="5")int size)
	{
	    Pageable paging=PageRequest.of(page,size);
	    return horaireRepository.chercherH("%" +ligne+"%","%"+station+"%","%"+date+"%",paging);
	}
	
	@RequestMapping(value="/chercherSt",method=RequestMethod.GET)
	public Page<Horaire> chercherS(
			                      @RequestParam(name="station",defaultValue="")String station,
			                      @RequestParam(name="page",defaultValue="0")int page,
			                      @RequestParam(name="size",defaultValue="5")int size)
	{
	    Pageable paging=PageRequest.of(page,size);
	    return horaireRepository.chercherS(station,paging);
	}
	
	@RequestMapping(value="/chercherL",method=RequestMethod.GET)
	public Page<Horaire> chercherL(
			                      @RequestParam(name="ligne",defaultValue="")String ligne,
			                      @RequestParam(name="page",defaultValue="0")int page,
			                      @RequestParam(name="size",defaultValue="5")int size)
	{
	    Pageable paging=PageRequest.of(page,size);
	    return horaireRepository.chercherL(ligne,paging);
	}
	
	@RequestMapping(value = "/listHoraires",method = RequestMethod.GET)
	public Page<Horaire> listHoraires(@RequestParam(name="page",defaultValue = "0")int page,
			  						  @RequestParam(name="size",defaultValue = "5")int size)
	{
		Pageable paging =  PageRequest.of(page,size);
		return horaireRepository.getAll(paging);
	}
	
	@GetMapping(value = "/listHoraire/{id_horaire}")
	public Horaire listHoraires(@PathVariable(name = "id_horaire") Long id_horaire)
	{
		return horaireRepository.findById(id_horaire).get();
	}
	@RequestMapping(value = "/listHoraire/{id_horaire}",method = RequestMethod.DELETE)
	public void deleteHoraire(@PathVariable(name = "id_horaire") Long id_horaire)
	{
		horaireRepository.deleteById(id_horaire);
	}
	
	@PostMapping(value = "/listHoraires")
	public Horaire addHoraire(@RequestBody Horaire h)
	{
		 return horaireRepository.save(h);
	}
	
	@PutMapping(value = "/listHoraires/{id}")
	public Horaire update(@PathVariable(name="id") Long id ,@RequestBody Horaire horaire) {
		horaire.setId_horaire(id); 
		return horaireRepository.save(horaire);
	}

	
}
