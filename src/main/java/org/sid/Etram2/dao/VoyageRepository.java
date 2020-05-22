package org.sid.Etram2.dao;


import org.sid.Etram2.Entities.Voyage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface VoyageRepository extends JpaRepository<Voyage, Long>{
	@Query("select v from Voyage v where v.ligneTram LIKE :ligne")
	public Page<Voyage> chercherVoyage(@Param("ligne")String ligne,Pageable pageable);
	@RestResource(path = "/byLigne")
	public Page<Voyage> findByLigneTramContains(@Param("ligne")String ligne,Pageable pageable);
	@Query("select v from Voyage v")
	public Page<Voyage> getAll(Pageable pageable);
	
	
}
