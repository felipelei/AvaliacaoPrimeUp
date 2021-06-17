package com.primeup.control.util;

import java.util.Comparator;

import com.primeup.dominio.Publicacao;

public class OrdenaPublicacaoRecente implements Comparator<Publicacao>{

	@Override
	public int compare(final Publicacao lhs, Publicacao rhs) {
		return rhs.getAno() - lhs.getAno();
	}
	
}
