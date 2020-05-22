package org.sid.Etram2.dao;

import java.util.List;


import org.sid.Etram2.Entities.ResGuichet;
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
public interface ResGuichetRepository extends JpaRepository<ResGuichet, String>{
	@RestResource(path = "/byCinRPage")
	public Page<ResGuichet> findByUsernameContains(@Param("mc")String username,Pageable pageable);
	@Query("select r from ResGuichet r")
	public List<ResGuichet> getAll();
	public ResGuichet findByUsername(String username);
	@Query("SELECT r FROM ResGuichet r where r.username = :username and r.password = :password")
	public ResGuichet getResGuichet(@Param("username")String username,@Param("password")String password);

}
