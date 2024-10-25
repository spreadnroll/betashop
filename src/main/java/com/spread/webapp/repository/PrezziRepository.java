package com.spread.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spread.webapp.entities.DettListini;

@Repository
public interface PrezziRepository extends JpaRepository<DettListini, Integer>
{
	//JPQL
	@Query(value = "SELECT b FROM Listini a JOIN a.dettListini b WHERE b.codArt = :codart AND a.id = :idlist")
	DettListini SelByCodArtAndList(@Param("codart") String CodArt, @Param("idlist") String Listino);
}
