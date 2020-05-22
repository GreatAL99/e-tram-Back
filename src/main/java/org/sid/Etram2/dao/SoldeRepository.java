package org.sid.Etram2.dao;

import java.util.List;

import org.sid.Etram2.Entities.Solde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface SoldeRepository extends JpaRepository<Solde, Long> {
	@Query("SELECT s FROM Solde s inner join s.voyageur v WHERE v.username LIKE:username")
	public Page<Solde> chercher(@Param("username")String username,Pageable pageable);
	@Query("select s from Solde s")
	public Page<Solde> getAll2(Pageable pageable);
	@Query("select s from Solde s")
	public List<Solde> getAll();
	@Query(value="SELECT * FROM Solde ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Solde find();

}
