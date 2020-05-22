package org.sid.Etram2.dao;


import java.util.List;


import org.sid.Etram2.Entities.Tram;
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
public interface TramRepository extends JpaRepository<Tram, String>{
	@RestResource(path = "/chercherTrame")
	public List<Tram> findByNomContains(@Param("nom")String nom);
	@RestResource(path = "/chercherTram")
	public Page<Tram> findByNomContains(@Param("nom")String nom,Pageable pageable);
	@Query("select t from Tram t")
	public List<Tram> getAll();
	public Tram findByUsername(String username);
	@Query("SELECT t FROM Tram t where t.username = :username and t.password = :password")
	public Tram getTram(@Param("username")String username,@Param("password")String password);
}
