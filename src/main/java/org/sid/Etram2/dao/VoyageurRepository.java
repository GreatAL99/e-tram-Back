package org.sid.Etram2.dao;

import java.util.List;

import org.sid.Etram2.Entities.Voyageur;
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
public interface VoyageurRepository extends JpaRepository<Voyageur, String>{
	@Query("SELECT v FROM Voyageur v where v.username = :username and v.password = :password")
	public Voyageur getVoyageur(@Param("username")String username,@Param("password")String password);
	public Voyageur findByUsername(String username);
	@RestResource(path = "/byCin")
	public List<Voyageur> findByUsernameContains(@Param("mc")String username);
	@RestResource(path = "/byCinPage")
	public Page<Voyageur> findByUsernameContains(@Param("mc")String username,Pageable pageable);
	@Query("SELECT username FROM Voyageur v WHERE v.username = :username")
	public String getCin(@Param("username")String username);
	@Query("select v from Voyageur v")
	public List<Voyageur> getAll();
	@Query(value="SELECT * FROM Voyageur ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Voyageur find();

}
