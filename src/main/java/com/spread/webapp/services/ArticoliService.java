package com.spread.webapp.services;

import java.util.List;

import com.spread.webapp.dtos.ArticoliDto;
import com.spread.webapp.entities.FamAssort;
import com.spread.webapp.entities.Iva;
 
 
public interface ArticoliService 
{
	public List<ArticoliDto> SelAll();
	
	public ArticoliDto SelByCodArt(String codart);
	
	public List<ArticoliDto> SelByDescrizione(String filter, int page, int numrec);
	
	public ArticoliDto SelByBarcode(String barcode);
	
	public int NumRecords(String filter);
	
	public List<ArticoliDto> SearchArticoli(String filter, int page, int numrec);
	
	public List<Iva> SelAllIva();
	
	public List<FamAssort> SelAllCat();
	
	public void InsArticolo(ArticoliDto articolo);
	
	public void DelArticolo(String codart);
	
}