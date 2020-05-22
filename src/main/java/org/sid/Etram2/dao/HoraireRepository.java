package org.sid.Etram2.dao;


import java.util.List;


import org.sid.Etram2.Entities.Horaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface HoraireRepository extends JpaRepository<Horaire, Long>{
	@Query("select h from Horaire h where h.date LIKE :date")
	public Page<Horaire> chercher(@Param("date")String date,Pageable pageable);
	@Query("select h from Horaire h inner join h.station s where   s.name LIKE :station")
	public Page<Horaire> chercherS(@Param("station")String station,Pageable pageable);
	
	@Query("select h from Horaire h inner join h.voyage v where   v.ligneTram LIKE :ligne")
	public Page<Horaire> chercherL(@Param("ligne")String ligne,Pageable pageable);
	
	@Query("select h from Horaire h inner join h.voyage v inner join h.station s where  v.ligneTram LIKE :ligne and s.name LIKE :station and h.date LIKE :date")
	public Page<Horaire> chercherH(@Param("ligne")String ligne,@Param("station")String station,@Param("date")String date,Pageable pageable);
	@Query("select h from Horaire h")
	public Page<Horaire> getAll(Pageable pageable);
	@Query("select h from Horaire h")
	public List<Horaire> getAll2();
}
