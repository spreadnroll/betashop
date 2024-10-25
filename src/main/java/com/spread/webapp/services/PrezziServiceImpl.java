package com.spread.webapp.services;

import org.springframework.stereotype.Service;

import com.spread.webapp.entities.DettListini;
import com.spread.webapp.repository.PrezziRepository;
 
@Service
public class PrezziServiceImpl implements PrezziService
{
	//@Autowired
	private PrezziRepository prezziRepository;
	
	public PrezziServiceImpl(PrezziRepository prezziRepository)
	{
		this.prezziRepository = prezziRepository;
	}
	
	@Override
	public double SelPrezzoArt(String CodArt, String IdList) 
	{
		DettListini dettListini = prezziRepository.SelByCodArtAndList(CodArt, IdList);
		
		if (dettListini != null)
		{
			return dettListini.getPrezzo();
		}
		else
		{
			return 0;
		}
	}

}
