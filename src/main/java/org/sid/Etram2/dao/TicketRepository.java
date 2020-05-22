package org.sid.Etram2.dao;

import java.util.List;



import org.sid.Etram2.Entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("SELECT t FROM Ticket t inner join t.horaire h inner join t.voyageur v WHERE h.date LIKE :date and v.username LIKE:username")
	public Page<Ticket> chercher(@Param("date")String date,@Param("username")String username,Pageable pageable);
	@Query("select t from Ticket t")
	public Page<Ticket> getAll(Pageable pageable);
	@Query("select t from Ticket t")
	public List<Ticket> getAll();
	@Query(value="SELECT * FROM Ticket ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Ticket find();


}
