package com.primeup.control.util;

import java.util.Comparator;

import com.primeup.dominio.Projeto;

public class OrdenaProjetoRecente implements Comparator<Projeto>{

	@Override
	public int compare(final Projeto lhs, Projeto rhs) {
		if(lhs.getDataTermino() == null)
			return -1;
		if(rhs.getDataTermino() == null)
			return 1;
		
		return -lhs.getDataTermino().compareTo(rhs.getDataTermino());
	}
	
}
