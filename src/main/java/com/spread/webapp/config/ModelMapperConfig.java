package com.spread.webapp.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spread.webapp.dtos.ArticoliDto;
import com.spread.webapp.dtos.BarcodeDto;
import com.spread.webapp.entities.Articoli;
import com.spread.webapp.entities.Barcode;

@Configuration
public class ModelMapperConfig 
{
    @Bean
    ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(articoliMapping);
        modelMapper.addMappings(articoliDtoMapping);

        modelMapper.addMappings(new PropertyMap<Barcode, BarcodeDto>()
        {
            @Override
            protected void configure()
            {
                map().setIdTipoArt(source.getIdTipoArt());
            }
        });

        modelMapper.addConverter(articoliConverter);

        return modelMapper;
    }
	
	PropertyMap<Articoli, ArticoliDto> articoliMapping = new PropertyMap<Articoli,ArticoliDto>() 
	{
	      protected void configure() 
	      {
	         map().setData(source.getDataCreaz());
	         map().setStatus(source.getIdStatoArt());
	         map().setIdCat(source.getFamAssort().getId());
	        
	      }
	};
	
	PropertyMap<ArticoliDto, Articoli> articoliDtoMapping = new PropertyMap<ArticoliDto,Articoli>() 
	{
	      protected void configure() 
	      {
	    	 map().setCodArt(source.getCodart()); 
	         map().setDataCreaz(source.getData());
	         map().getFamAssort().setId(source.getIdCat());
	         map().setPesoNetto(source.getPeso()); 
	         map().getIva().setIdIva(source.getIdIva()); 
	        
	      }
	};
	
	Converter<String, String> articoliConverter = new Converter<String, String>() 
	{
		  @Override
		  public String convert(MappingContext<String, String> context) 
		  {
			  return context.getSource() == null ? "" : context.getSource().trim();
		  }
	};
	
}
