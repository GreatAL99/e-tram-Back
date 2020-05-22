package org.sid.Etram2.Controller;


import org.sid.Etram2.Entities.Admin;

import org.sid.Etram2.dao.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;


	@PutMapping(value = "/admin/{cin}")
	public Admin update(@PathVariable(name="cin") String cin, @RequestBody Admin admin)
	{
		admin.setUsername(cin);
		return adminRepository.save(admin);
	}
	@GetMapping(value = "/admin2/{cin}")
	public Admin getAdminBycin(@PathVariable(name="cin") String cin)
	{
		return adminRepository.findByUsername(cin);
	}

}
