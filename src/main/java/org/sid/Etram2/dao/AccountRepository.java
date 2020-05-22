package org.sid.Etram2.dao;



import org.sid.Etram2.Entities.Account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {

}
