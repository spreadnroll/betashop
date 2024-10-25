package com.spread.webapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spread.webapp.dtos.PagingData;

@Component
public class Paging 
{
	public List<PagingData> setPages(int page, long numRecords) 
	{
		List<PagingData> pages = new ArrayList<PagingData>();
		
	    int recForPage = 10;
	    int min = 1;
	    int max = 5;

	    page = (page == 0) ? 1 : page;

	    if (pages != null)
	        pages.clear();

	    int group = (int) Math.ceil((double) page / 5);

	    max = group * 5;
	    min = Math.max(max - 4, 1);

	    int maxPages = (int) Math.ceil((double) numRecords / recForPage);

	    for (int i = min; i <= Math.min(max, maxPages); i++) 
	    {
	        boolean isSelected = (i == page);
	        pages.add(new PagingData(i, isSelected));
	    }
	    
	    return pages;
	}
}

