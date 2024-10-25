package com.spread.webapp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ARTICOLI")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Articoli 
{
	@Id
	@Column(name = "codart")
	private String codArt;
	
	@Column(name = "descrizione")
	private String descrizione;	
	
	@Column(name = "um")
	private String um;
	
	@Column(name = "codstat")
	private String codStat;
	
	@Column(name = "pzcart")
	private Integer pzCart;
	
	@Column(name = "pesonetto")
	private double pesoNetto;
	
	@Column(name = "idstatoart")
	private String idStatoArt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datacreazione")
	//@NotNull(message = "{NotNull.Articoli.dataCreaz.Validation}")
	private Date dataCreaz;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	private Set<Barcode> barcode = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	private Ingredienti ingredienti;
	
	@ManyToOne
	@JoinColumn(name = "idiva",  referencedColumnName = "idiva")
	private Iva iva;
	
	@ManyToOne
	@JoinColumn(name = "idfamass", referencedColumnName = "id")
	private FamAssort famAssort;
	
}

