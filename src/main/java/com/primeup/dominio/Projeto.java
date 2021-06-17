package com.primeup.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.apache.log4j.Logger;
import org.hibernate.annotations.OrderBy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.util.StringUtils;

@Entity
public class Projeto {

	private static final Logger logger = Logger.getLogger(Projeto.class);
	
	public enum EnumStatus {
		EM_ELABORACAO,
		EM_ANDAMENTO,
		CONCLUIDO;
		
		public EnumStatus getProximo() {
			if(this == EM_ELABORACAO) {
				return EM_ANDAMENTO;
			}
			else if(this == EM_ANDAMENTO) {
				return CONCLUIDO;
			}
			
			return null;
		}
	};
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String titulo;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataTermino;

	private String agenciaFinanciadora;

	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal valorFinanciado;

	private String objetivo;

	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;
	
	@OneToMany(mappedBy="projeto", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@OrderBy(clause = "ano DESC")
	private List<Publicacao> publicacaoList;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="projeto_colaborador", joinColumns=
	{@JoinColumn(name="projeto_id")}, inverseJoinColumns=
	{@JoinColumn(name="colaborador_id")})
	private List<Colaborador> colaboradorList;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getAgenciaFinanciadora() {
		return agenciaFinanciadora;
	}

	public void setAgenciaFinanciadora(String agenciaFinanciadora) {
		this.agenciaFinanciadora = agenciaFinanciadora;
	}

	public BigDecimal getValorFinanciado() {
		return valorFinanciado;
	}

	public void setValorFinanciado(BigDecimal valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		if(this.status != null && this.status != status)
		{
			if(status == EnumStatus.EM_ANDAMENTO) 
			{
				boolean possuiProfessor = 
							this.colaboradorList != null && 
							this.colaboradorList.stream()
												.filter(c -> c instanceof Professor)
												.findAny()
												.isPresent();
				
				if(!possuiProfessor)
					throw new IllegalStateException("Um projeto não pode entrar Em andamento sem um professor!");
			}
			if(status == EnumStatus.EM_ELABORACAO) 
			{
				// se nao possuir as informações básicas, não pode ficar em elaboracao
				if(this.getDataInicio() == null 
						|| this.getDataTermino() == null
						|| StringUtils.isEmpty(this.getObjetivo())
						|| StringUtils.isEmpty(this.getDescricao())
						|| StringUtils.isEmpty(this.getTitulo())
						|| StringUtils.isEmpty(this.getAgenciaFinanciadora())
						|| (this.getValorFinanciado() == null || this.getValorFinanciado().doubleValue() == 0)
				)
				{
					throw new IllegalStateException("Um projeto não pode estar Em elaboração sem suas informações básicas preenchidas!");
				}
			} 
			else if(status == EnumStatus.CONCLUIDO) 
			{
				// verificar se possui publicacao
				if(this.getPublicacaoList().isEmpty())
				{
					throw new IllegalStateException("Um projeto não pode ser concluído sem publicações!");
				}
			}
		}
		
		this.status = status;
	}

	public List<Publicacao> getPublicacaoList() {
		return publicacaoList;
	}

	public void setPublicacaoList(List<Publicacao> publicacaoList) {
		this.publicacaoList = publicacaoList;
	}

	public List<Colaborador> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<Colaborador> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}
	
	public boolean isEmAndamento() {
		return this.status == EnumStatus.EM_ANDAMENTO;
	}
	
	public boolean isEmElaboracao() {
		return this.status == EnumStatus.EM_ELABORACAO;
	}
	
	public boolean isConcluido() {
		return this.status == EnumStatus.CONCLUIDO;
	}
	
	public void addPublicacao(Publicacao p) {
		if(p == null) {
			throw new IllegalArgumentException("Não foi enviada publicação!");
		}
		
		if(!this.isEmAndamento()) {
			throw new IllegalStateException("Não pode ser adicionado publicações para um projeto que não está Em andamento.");
		}
		
		if(this.publicacaoList == null) {
			this.publicacaoList = new ArrayList<>();
		}
		
		if(publicacaoList.contains(p)) {
			throw new IllegalArgumentException("Esta publicação já está relacionado com o projeto!");
		}
		
		this.publicacaoList.add(p);
	}
	
	public void addColaborador(Colaborador c) {
		if(c == null) {
			throw new IllegalArgumentException("Não foi enviado o colaborador!");
		}
		
		if(!this.isEmElaboracao()) {
			throw new IllegalStateException("Não pode ser adicionado Colaborador para um projeto que não está Em elaboração.");
		}
		
		if(this.colaboradorList == null) {
			this.colaboradorList = new ArrayList<>();
		}
		
		if(colaboradorList.contains(c)) {
			throw new IllegalArgumentException("Este colaborador já está relacionado com a publicação!");
		}
		
		this.colaboradorList.add(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", titulo=" + titulo + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", agenciaFinanciadora=" + agenciaFinanciadora + ", valorFinanciado=" + valorFinanciado
				+ ", objetivo=" + objetivo + ", descricao=" + descricao + ", status=" + status + "]";
	}

}
