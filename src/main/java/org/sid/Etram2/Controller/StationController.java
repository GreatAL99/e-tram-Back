package org.sid.Etram2.Controller;

import org.sid.Etram2.dao.StationRepository;

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

import java.util.List;

import org.sid.Etram2.Entities.*;

@CrossOrigin(origins = "*")
@RestController
public class StationController {
	@Autowired
	private StationRepository stationRepository;
	@RequestMapping(value = "/listStations",method = RequestMethod.GET)
	public List<Station> listStations()
	{
		return stationRepository.getAll();
	}
	@GetMapping(value = "/listStations/{id}")
	public Station listStations(@PathVariable(name = "id") Long id)
	{
		return stationRepository.findById(id).get();
	}
	@DeleteMapping(value = "/listStations/{id}")
	public void deleteStation(@PathVariable(name = "id") Long id)
	{
		stationRepository.deleteById(id);
	}
	@PostMapping(value = "/listStations")
	public Station addStation(@RequestBody Station s)
	{
		 return stationRepository.save(s);
	}
	
	@PutMapping(value = "/listStations/{id}")
	public Station update(@PathVariable(name="id") Long id ,@RequestBody Station station)
	{
		station.setId(id); 
		return stationRepository.save(station);
	}
}
