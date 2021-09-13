package com.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.beans.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer>{

	@Query("FROM Accounts WHERE username = ?1 and password = ?2")
	List<Accounts> findByUsernameAndPassword(String username, String password);

}
