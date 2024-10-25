package com.spread.webapp.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticoliDto 
{
	@Size(min = 5, max = 20, message = "Il codice articolo deve essere compreso fra 5 e 20 caratteri")
	@NotNull(message = "Non è possibile inserire un valore null nel codice articolo")
	private String codart;
	
	@Size(min = 6, max = 80, message = "La descrizione dell'articolo deve essere compresa fra 6 e 80 caratteri")
	private String descrizione;	
	
	@NotBlank(message = "Inserire l'unità di misura dell'articolo")
	private String um;
	
	private String codstat;
	
	@Max(value = 99, message = "Il valore massimo dei pezzi per cartone è 99")
	@Min(value = 1, message = "Il valore minimo dei pezzi per cartone è 1")
	@NotNull(message = "E' necessario inserire un valore dei pezzi per cartone")
	private int pzcart;
	
	@Max(value = 999, message = "Il valore massimo del peso è 999")
	@DecimalMin(value = "0.01", message = "Il valore minimo del peso è 0.01")
	@NotNull(message = "E' necessario inserire il valore del peso dell'articolo")
	private double peso;
	
	private String status;
	private Date data;
	private double prezzo = 0;
	
	@NotNull(message = "E'necessario selezionare l'iva dell'articolo")
	private int idIva = 0;
	
	@NotNull(message = "E'necessario selezionare la categoria dell'articolo")
	private int idCat = 0;
	
	@NotNull(message = "E' necessario selezionare lo stato dell'articolo")
	@NotBlank(message = "E' necessario selezionare lo stato dell'articolo")
	private String idStatoArt;
	
	private Set<BarcodeDto> barcode = new HashSet<>();
	private IngredientiDto ingredienti;
	private CategoriaDto famAssort;
	private IvaDto iva;
}