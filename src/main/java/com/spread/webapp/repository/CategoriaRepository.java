package com.spread.webapp.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spread.webapp.entities.FamAssort;
 
@Repository
public interface CategoriaRepository extends JpaRepository<FamAssort, Integer>
{
	/*
	//@Query(value = "SELECT id, descrizione FROM famassort", nativeQuery = true)
	@Query("SELECT f FROM FamAssort f")
	List<FamAssort> selCat();	
	*/
}