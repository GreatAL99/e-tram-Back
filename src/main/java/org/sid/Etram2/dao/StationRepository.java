package org.sid.Etram2.dao;

import java.util.List;


import org.sid.Etram2.Entities.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface StationRepository extends JpaRepository<Station, Long>{
	@RestResource(path = "/byName")
	public List<Station> findByNameContains(@Param("mc")String name);
	@RestResource(path = "/byNamePage")
	public Page<Station> findByNameContains(@Param("mc")String name,Pageable pageable);
	@Query("SELECT name FROM Station s WHERE s.id = :id")
	public String getName(@Param("id")Long id);
	@Query("select s from Station s")
	public List<Station> getAll();
}
