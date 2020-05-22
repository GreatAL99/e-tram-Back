package org.sid.Etram2.Controller;


import org.sid.Etram2.Entities.Voyage;
import org.sid.Etram2.dao.VoyageRepository;
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
public class VoyageController {
	@Autowired
	private VoyageRepository voyageRepository;
	
	@RequestMapping(value = "/chercherVoyage",method = RequestMethod.GET)
	public Page<Voyage> chercherVoyage(@RequestParam(name="ligne",defaultValue = "")String ligne,
								  @RequestParam(name="page",defaultValue = "0")int page,
								  @RequestParam(name="size",defaultValue = "5")int size)
	{
	
		Pageable paging =  PageRequest.of(page,size);
		return voyageRepository.chercherVoyage("%" + ligne +"%",paging);
	} 
	
	@RequestMapping(value = "/listVoyages",method = RequestMethod.GET)
	public Page<Voyage> listVoyages(@RequestParam(name="page",defaultValue = "0")int page,
			  						  @RequestParam(name="size",defaultValue = "5")int size)
	{
		Pageable paging =  PageRequest.of(page,size);
		return voyageRepository.getAll(paging);
	}
	
	@GetMapping(value = "/listVoyages/{id_voyage}")
	public Voyage listVoyages(@PathVariable(name = "id_voyage") Long id_voyage)
	{
		return voyageRepository.findById(id_voyage).get();
	}
	@RequestMapping(value = "/listVoyages/{id_voyage}",method = RequestMethod.DELETE)
	public void deleteVoyage(@PathVariable(name = "id_voyage") Long id_voyage)
	{
		voyageRepository.deleteById(id_voyage);
	}
	
	@PostMapping(value = "/listVoyages")
	public Voyage addVoyagee(@RequestBody Voyage v)
	{
		 return voyageRepository.save(v);
	}
	
	@PutMapping(value = "/listVoyages/{id}")
	public Voyage update(@PathVariable(name="id") Long id ,@RequestBody Voyage voyage) {
		voyage.setId_voyage(id); 
		return voyageRepository.save(voyage);
	}
}
