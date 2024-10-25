package com.spread.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spread.webapp.entities.Articoli;

public interface ArticoliRepository extends JpaRepository<Articoli, String> 
{
	//Query Method
	Articoli findByCodArt(String codArt);
	
	//Query Method
	List<Articoli> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	//Query Method
	List<Articoli> findByCodStatOrderByDescrizione(String codStat);
	
	//JPQL
	@Query(value="SELECT a FROM Articoli a JOIN a.barcode b WHERE b.barcode IN (:ean)")
	Articoli selByEan(@Param("ean") String ean);
	
	//SQL
	@Query(value = "SELECT COUNT(*) FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	int countRecords(@Param("desArt") String descrizione);	
	
}

