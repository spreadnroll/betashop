package com.spread.webapp.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
 
import org.springframework.stereotype.Repository;

import com.spread.webapp.entities.Iva;

@Repository
public interface IvaRepository extends JpaRepository<Iva, Integer>
{
	/*
	@Query(value = "SELECT i FROM iva i")
	List<Iva> selIva();	
	*/
}
