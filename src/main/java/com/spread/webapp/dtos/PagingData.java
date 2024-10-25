package com.spread.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingData 
{
	private int PageNum;
	private boolean IsSelected;
}
