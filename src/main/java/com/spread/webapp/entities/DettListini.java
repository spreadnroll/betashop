package com.spread.webapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DettListini 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dettlistini_generator")
	@SequenceGenerator(name = "dettlistini_generator", sequenceName = "dettlistini_seq", allocationSize = 1)
	private int id;
	
	private String codArt;
	
	private double prezzo; 
	
	@ManyToOne
	@JoinColumn(name = "idlist", referencedColumnName = "id")
	private Listini listino;
}
