package org.sid.Etram2.dao;





import java.util.List;


import org.sid.Etram2.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, String>{
	@Query("SELECT a FROM Admin a where a.username = :username and a.password = :password")
	public Admin getAdmin(@Param("username")String username,@Param("password")String password);
	public Admin findByUsername(String username);
	@Query("select a from Admin a")
	public List<Admin> getAll();
}
