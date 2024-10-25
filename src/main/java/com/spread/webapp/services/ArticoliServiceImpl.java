package com.spread.webapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.spread.webapp.dtos.ArticoliDto;
import com.spread.webapp.entities.Articoli;
import com.spread.webapp.entities.FamAssort;
import com.spread.webapp.entities.Iva;
import com.spread.webapp.repository.ArticoliRepository;
import com.spread.webapp.repository.CategoriaRepository;
import com.spread.webapp.repository.IvaRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService
{
	
	//@Autowired
	private ArticoliRepository articoliRepository;
	private PrezziService prezziService;
	private IvaRepository ivaRepository;
	private CategoriaRepository categoriaRepository;
	
	private ModelMapper modelMapper;
	
	public ArticoliServiceImpl(
			ArticoliRepository articoliRepository,
			PrezziService prezziService,
			IvaRepository ivaRepository,
			CategoriaRepository categoriaRepository,
			ModelMapper modelMapper)
	{
		this.modelMapper = modelMapper;
		this.articoliRepository = articoliRepository;
		this.ivaRepository = ivaRepository;
		this.categoriaRepository = categoriaRepository;
		this.prezziService = prezziService;
		
	}
	
	@Override
	public void InsArticolo(ArticoliDto articoloDto) 
	{
		articoliRepository.save(ConvertToArticoli(articoloDto));	
	}

	private Articoli ConvertToArticoli(ArticoliDto articoloDto) 
	{
		articoloDto.setDescrizione(articoloDto.getDescrizione().toUpperCase());
		Articoli articolo = modelMapper.map(articoloDto, Articoli.class);
		
		return articolo;
		
	}
	
	@Override
	public List<ArticoliDto> SelAll() 
	{
		return  ConvertToDto(articoliRepository.findAll());
	}

	@Override
	public ArticoliDto SelByCodArt(String codart) 
	{
		return ConvertToDto(articoliRepository.findByCodArt(codart));
	}

	@Override
	public List<ArticoliDto> SelByDescrizione(String filter, int page, int numrec) 
	{
		filter = "%".concat(filter.toUpperCase().concat("%"));
		
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		
		return ConvertToDto(articoliRepository.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoliDto SelByBarcode(String barcode) 
	{
		return ConvertToDto(articoliRepository.selByEan(barcode));
	}
	
	@Override
	public List<ArticoliDto> SearchArticoli(String filter, int page, int numrec) 
	{
		ArticoliDto articolo = SelByCodArt(filter);

	    if (articolo == null) 
	    {
	        articolo = SelByBarcode(filter);
	    }

	    List<ArticoliDto> articoli = new ArrayList<>();
	    
	    if (articolo != null) 
	    {
	        articoli.add(articolo);
	    } 
	    else 
	    {
	        articoli = SelByDescrizione(filter, page, numrec);
	    }

	    return articoli;
	}
	
	@Override
	public int NumRecords(String filter) 
	{
		filter = "%".concat(filter.toUpperCase().concat("%"));
		
		return articoliRepository.countRecords(filter);
	}
	
	private ArticoliDto ConvertToDto(Articoli articoli)
	{
		ArticoliDto articoliDto = null;
		
		if (articoli != null)
		{
			articoliDto =  modelMapper.map(articoli, ArticoliDto.class);
			articoliDto.setPrezzo(prezziService.SelPrezzoArt(articoliDto.getCodart(), "1"));
		}
		
		return articoliDto;
	}
	
	@Override
	public List<Iva> SelAllIva() 
	{
		return ivaRepository.findAll();
	}

	@Override
	public List<FamAssort> SelAllCat() 
	{
		 
		return categoriaRepository.findAll();
	}

	
	private List<ArticoliDto> ConvertToDto(List<Articoli> articoli)
	{		
		List<ArticoliDto> articoliDto = articoli
		        .stream()
		        .map(source -> modelMapper.map(source, ArticoliDto.class))
		        .collect(Collectors.toList());
		
		articoliDto.forEach(e -> 
		e.setPrezzo(prezziService.SelPrezzoArt(e.getCodart(),"1")));
		
		return articoliDto;
	}

	@Override
	public void DelArticolo(String codart) {
		
		articoliRepository.delete(articoliRepository.findByCodArt(codart));
		
	}

	

}


