package com.spread.webapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spread.webapp.dtos.ArticoliDto;
import com.spread.webapp.dtos.CategoriaDto;
import com.spread.webapp.dtos.PagingData;
import com.spread.webapp.entities.FamAssort;
import com.spread.webapp.entities.Iva;
import com.spread.webapp.services.ArticoliService;
import com.spread.webapp.utils.Paging;

import jakarta.validation.Valid;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/articoli")
public class ArticoliController 
{
	//@Autowired
	private ArticoliService articoliService;
	private Paging paging;
	
	public ArticoliController(
			ArticoliService articoliService, 
			Paging paging)
	{
		this.articoliService = articoliService;
		this.paging = paging;
	}
	
	List<PagingData> pages = new ArrayList<PagingData>();
	
	@ModelAttribute("famAssort")
	public List<FamAssort> getFamAssort()
	{
		return articoliService.SelAllCat();
	}
	
	@ModelAttribute("iva")
	public List<Iva> getIva()
	{ 
		return articoliService.SelAllIva();
	}
	
	@GetMapping(value = "/modifica/{CodArt}")
	public String UpdArticoli(
			ModelMap model, 
			@PathVariable("CodArt") String codart,
			@ModelAttribute("famAssort") List<FamAssort> famAssort,
			@ModelAttribute("iva") List<Iva> iva)
	{
		ArticoliDto articolo =  articoliService.SelByCodArt(codart);
		
		model.addAttribute("title", "Modifica Articolo");
		model.addAttribute("famAssort",famAssort);
		model.addAttribute("iva",iva);
		model.addAttribute("isModifica", true);
		model.addAttribute("datiart", articolo);
		
		return "gestart";
	}
	
	@PostMapping(value = "/modifica/{CodArt}")
	public String GestUpdArticoli(
			@PathVariable("CodArt") String codart, 
			@Valid @ModelAttribute("datiart") ArticoliDto articolo,
			BindingResult result)
	{
		if (result.hasErrors())
			return "gestart";
		
		articoliService.InsArticolo(articolo); 

		return "redirect:/articoli/search?filtro=" + codart;
	}
	
	@GetMapping(value = "/aggiungi")
	public String InsArticoli(ModelMap model,
			@ModelAttribute("famAssort") List<FamAssort> famAssort,
			@ModelAttribute("iva") List<Iva> iva)
	{
		model.addAttribute("datiart", new ArticoliDto());
		model.addAttribute("title", "Creazione Articolo"); 
		model.addAttribute("iva", iva);
		model.addAttribute("famAssort", famAssort);
		 
		return "gestart";
	}
	
	@PostMapping(value="/aggiungi")
	public String GestInsArticoli(
			@Valid @ModelAttribute("datiart") ArticoliDto articolo,
			BindingResult result)
	{
		
		//log.info(String.format("Articolo Inserito: %s - %s", articolo.getCodart(), articolo.getDescrizione()));
		
		if (result.hasErrors())
			return "gestart";
		
		
		articolo.setData(new Date());
		
		articoliService.InsArticolo(articolo);
		
		return "redirect:/articoli/search?filtro=" + articolo.getCodart() ;
		
	}
	
	@GetMapping()
	public String GetGestArt()
	{
		return "articoli";
	}
	
	@GetMapping(value="/cerca/descrizione/{filter}")
	public String GetArticoli(
			@PathVariable("filter") String filter,
			@MatrixVariable(name="page",defaultValue = "0") String page,
			@MatrixVariable(name="record",defaultValue = "10") String record, 
			ModelMap model)
	{
		int pageNum = Integer.parseInt(page); //Numero della pagina
		int recForPage = Integer.parseInt(record); //Record per pagina
		
		List<ArticoliDto> articoli = articoliService.SelByDescrizione(filter,pageNum,recForPage);
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
		
	}
	
	@GetMapping(value = "/search")
	public String searchItem(
	        @RequestParam(name="filtro") String filtro,
	        @RequestParam(name="selected", required = false, defaultValue = "10") String selected,
	        ModelMap model) 
	{
	    log.info(String.format("Ricerca articoli con filtro %s ", filtro));

	    int pageNum = 0;
	    int recForPage;

	    try {
	        recForPage = Integer.parseInt(selected);
	    } catch (NumberFormatException e) {
	        recForPage = 10; // Valore predefinito nel caso di errore di parsing
	    }

	    List<ArticoliDto> articoli = articoliService.SearchArticoli(filtro, pageNum, recForPage);
	    
	    int numArt = articoliService.NumRecords(filtro);
	    
	    boolean notFound = articoli.isEmpty();

	    log.info(String.format("Trovati %s articoli", numArt));

	    pages = paging.setPages(pageNum, numArt);

	    model.addAttribute("articoli", articoli);
	    model.addAttribute("PageNum", pageNum);
	    model.addAttribute("RecPage", recForPage);
	    model.addAttribute("filtro", filtro);
	    model.addAttribute("Pages", pages);
	    model.addAttribute("notFound", notFound);

	    return "articoli";
	}
	
	
	
	// articoli/cerca/parametri;paging=0,10;exfilter=1,15?filtro=Barilla&selected=20
	@GetMapping(value="/cerca/{parametri}")
	public String GetArticoliWithPar(
			@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri, 
			@RequestParam(name="filtro") String filtro,
			@RequestParam(name="selected", required = false, defaultValue = "10")  String selected,
			ModelMap model)
	{
		int numArt = 0;
		int pageNum = 0;
		int recForPage = 10;
		int diffPage = 0;
		
		//PARAMETRI PAGING
		List<String> ParamPaging = parametri.get("paging");
		if (ParamPaging != null)
		{
			try
			{
				pageNum = Integer.parseInt(ParamPaging.get(0)); //Numero della pagina
				diffPage = Integer.parseInt(ParamPaging.get(1));
				recForPage = Integer.parseInt(selected); //Record per pagina
				
				if (pageNum >= 1)
					pageNum += diffPage;
				else
					pageNum = 1;
				
			} 
			catch (NumberFormatException ex)
			{
				diffPage = 0;
				pageNum = 0;
				recForPage = 10;
			}
			
			log.info(String.format("pagina: %s, records %s", pageNum, recForPage));
		}
		
		/*
		//PARAMETRI FILTRI AGGIUNTIVI
		List<String> ExFilter = parametri.get("exfilter");
		if (ExFilter != null)
		{
			try
			{
				log.info(String.format("status: %s", ExFilter.get(0)));
				log.info(String.format("categoria: %s", ExFilter.get(1)));
			}
			catch (Exception ex)
			{
				
			}
		}
		*/
		
		log.info("Cerco tutti gli articoli con descrizione " + filtro);
		
		List<ArticoliDto> articoli = articoliService.SearchArticoli(filtro,pageNum,recForPage);
		
		numArt = articoliService.NumRecords(filtro);
		
		log.info(String.format("Trovati %s articoli", numArt));
		
		pages = paging.setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("PageNum", pageNum);
		model.addAttribute("RecPage", recForPage);
		model.addAttribute("filtro", filtro);
		model.addAttribute("Pages", pages);
 
		return "articoli";
	}
	
	@GetMapping(value = "/elimina/{CodArt}")
	public String DelArticolo(@PathVariable("CodArt") String codart, ModelMap model)
	{
		try
		{
			if (codart.length() > 0)
			{
				articoliService.DelArticolo(codart);
			}
		} 
		catch (Exception ex)
		{
			throw new RuntimeException("Errore eliminazione articolo", ex);
		}

		return "redirect:/articoli/search?filtro=" + codart;
	}
	
	
}

