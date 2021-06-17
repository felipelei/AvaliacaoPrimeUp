package com.primeup.init;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.primeup.dominio.Aluno;
import com.primeup.dominio.AlunoGraduacao;
import com.primeup.dominio.AlunoPosGraduacao;
import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Orientacao;
import com.primeup.dominio.Professor;
import com.primeup.dominio.Projeto;
import com.primeup.dominio.Publicacao;
import com.primeup.dominio.AlunoPosGraduacao.EnumRegime;
import com.primeup.dominio.Projeto.EnumStatus;
import com.primeup.service.ColaboradorService;
import com.primeup.service.ProducaoAcademicaService;
import com.primeup.service.ProjetoService;

@Component
public class InitMockDB implements InitializingBean {

	private static final Logger logger = Logger.getLogger(InitMockDB.class);

	@Autowired
	private ColaboradorService colService;

	@Autowired
	private ProjetoService projService;
	
	@Autowired
	private ProducaoAcademicaService prodService;
	
	public void afterPropertiesSet() throws Exception {
		logger.info("Loading InitMockDB");

		// aux data ingresso
		Calendar auxCalendar = Calendar.getInstance();
		
		// alunos de graduação
		Aluno maria = new AlunoGraduacao();
		maria.setNome("Maria");
		maria.setEmail("maria@email.br");
		auxCalendar.set(2006, 2, 1);
		maria.setDataIngresso(auxCalendar.getTime());
		colService.incluir(maria);
		
		Aluno joao = new AlunoGraduacao();
		joao.setNome("João");
		joao.setEmail("joao@email.br");
		auxCalendar.set(2005, 2, 1);
		joao.setDataIngresso(auxCalendar.getTime());
		colService.incluir(joao);
		
		Aluno mario = new AlunoGraduacao();
		mario.setNome("Mário");
		mario.setEmail("mario@email.br");
		auxCalendar.set(2007, 2, 1);
		mario.setDataIngresso(auxCalendar.getTime());
		colService.incluir(mario);
		
		// alunos de mestrado
		AlunoPosGraduacao soraia = new AlunoPosGraduacao();
		soraia.setNome("Soraia");
		soraia.setEmail("soraia@email.br");
		auxCalendar.set(2006, 2, 1);
		soraia.setDataIngresso(auxCalendar.getTime());
		soraia.setRegime(EnumRegime.PARCIAL);
		colService.incluir(soraia);
		
		AlunoPosGraduacao rafael = new AlunoPosGraduacao();
		rafael.setNome("Rafael");
		rafael.setEmail("rafael@email.br");
		auxCalendar.set(2007, 5, 1);
		rafael.setDataIngresso(auxCalendar.getTime());
		rafael.setRegime(EnumRegime.PARCIAL);
		colService.incluir(rafael);
		
		AlunoPosGraduacao marta = new AlunoPosGraduacao();
		marta.setNome("Marta");
		marta.setEmail("marta@email.br");
		auxCalendar.set(2007, 5, 1);
		marta.setDataIngresso(auxCalendar.getTime());
		marta.setRegime(EnumRegime.INTEGRAL);
		colService.incluir(marta);
		
		AlunoPosGraduacao daniel = new AlunoPosGraduacao();
		daniel.setNome("Daniel");
		daniel.setEmail("daniel@email.br");
		auxCalendar.set(2006, 2, 1);
		daniel.setDataIngresso(auxCalendar.getTime());
		daniel.setRegime(EnumRegime.INTEGRAL);
		colService.incluir(daniel);
		
		// alunos de doutorado
		AlunoPosGraduacao michael = new AlunoPosGraduacao();
		michael.setNome("Michael");
		michael.setEmail("michael@email.br");
		auxCalendar.set(2005, 2, 1);
		michael.setDataIngresso(auxCalendar.getTime());
		michael.setRegime(EnumRegime.INTEGRAL);
		michael.setDoutorado(true);
		colService.incluir(michael);
		
		AlunoPosGraduacao bia = new AlunoPosGraduacao();
		bia.setNome("Bia");
		bia.setEmail("bia@email.br");
		auxCalendar.set(2004, 5, 1);
		bia.setDataIngresso(auxCalendar.getTime());
		bia.setRegime(EnumRegime.INTEGRAL);
		bia.setDoutorado(true);
		colService.incluir(bia);
		
		// professores
		Professor carlos = new Professor();
		carlos.setNome("Prof. Carlos");
		carlos.setEmail("carlos@email.br");
		colService.incluir(carlos);
		
		Professor arnaldo = new Professor();
		arnaldo.setNome("Prof. Arnaldo");
		arnaldo.setEmail("arnaldo@email.br");
		colService.incluir(arnaldo);
		
		Professor paulo = new Professor();
		paulo.setNome("Prof. Paulo");
		paulo.setEmail("paulo@email.br");
		colService.incluir(paulo);
		
		// projeto
		Projeto esma = new Projeto();
		auxCalendar.set(2003, 1, 2);
		esma.setTitulo("Engenharia de Software para Sistemas Multi-Agentes (ESMA)");
		esma.setDataInicio(auxCalendar.getTime());
		auxCalendar.set(2008, 1, 2);
		esma.setDataTermino(auxCalendar.getTime());
		esma.setAgenciaFinanciadora("FPCL");
		esma.setValorFinanciado(new BigDecimal(300000));
		esma.setObjetivo("O objetivo geral deste projeto é desenvolver os fundamentos e as tecnologias da ESSMA.");
		esma.setDescricao("Pesquisar, aplicar e avaliar técnicas de desenvolvimento de software para sistemas multi-agentes.");
		
		List<Colaborador> participantes = Arrays.asList(carlos, arnaldo, joao, mario, rafael, marta, daniel, michael, bia);
		esma.setColaboradorList((participantes));
		esma.setStatus(EnumStatus.EM_ANDAMENTO);
		projService.incluir(esma);
		
		Projeto esoa = new Projeto();
		auxCalendar.set(2005, 1, 2);
		esoa.setTitulo("Engenharia de Software Orientada a Aspectos (ESOA)");
		esoa.setDataInicio(auxCalendar.getTime());
		auxCalendar.set(2006, 11, 2);
		esoa.setDataTermino(auxCalendar.getTime());
		esoa.setAgenciaFinanciadora("FPCL");
		esoa.setValorFinanciado(new BigDecimal(190000));
		esoa.setObjetivo("O objetivo geral deste projeto é desenvolver os fundamentos e as tecnologias da ESOA.");
		esoa.setDescricao("Pesquisar, aplicar e avaliar técnicas de desenvolvimento de software orientado à aspectos.");
		participantes = Arrays.asList(arnaldo, carlos, maria, joao, soraia, daniel, michael);
		esoa.setColaboradorList((participantes));
		esoa.setStatus(EnumStatus.CONCLUIDO);
		projService.incluir(esoa);
		
		Projeto qos = new Projeto();
		auxCalendar.set(2006, 4, 2);
		qos.setDataInicio(auxCalendar.getTime());
		qos.setTitulo("Qualidade de Software");
		auxCalendar.set(2007, 9, 2);
		qos.setDataTermino(auxCalendar.getTime());
		qos.setAgenciaFinanciadora("FPCL");
		qos.setValorFinanciado(new BigDecimal(100000));
		qos.setObjetivo("O objetivo deste projeto é desenvolver os fundamentos e as tecnologias para desenvolvimento de software com qualidade.");
		qos.setDescricao("Pesquisar, aplicar e avaliar técnicas para qualidade em desenvolvimento de software.");
		participantes = Arrays.asList(paulo, arnaldo, maria, mario, rafael, marta, daniel, bia);
		qos.setColaboradorList((participantes));
		qos.setStatus(EnumStatus.EM_ELABORACAO);
		projService.incluir(qos);
		
		// publicacoes
		Publicacao abordagem = new Publicacao();
		abordagem.setTitulo("Abordagem Quantitativa para Desenvolvimento de Software Orientado a Aspectos");
		abordagem.setConferencia("SBQS");
		abordagem.setAno(2006);
		List<Colaborador> autores = Arrays.asList(soraia, michael, carlos);
		abordagem.setColaboradorList(autores);
		abordagem.setProjeto(esoa);
		prodService.incluir(abordagem);
		
		Publicacao refactoring = new Publicacao();
		refactoring.setTitulo("Refactoring Product Lines");
		refactoring.setConferencia("GPCE");
		refactoring.setAno(2007);
		autores = Arrays.asList(maria, bia, arnaldo);
		refactoring.setColaboradorList(autores);
		prodService.incluir(refactoring);
		
		Publicacao tratamento = new Publicacao();
		tratamento.setTitulo("Tratamento de Exceções Sensível ao Contexto");
		tratamento.setConferencia("SBES");
		tratamento.setAno(2006);
		autores = Arrays.asList(marta, paulo);
		tratamento.setColaboradorList(autores);
		prodService.incluir(tratamento);
		
		Publicacao integrating = new Publicacao();
		integrating.setTitulo("Integrating MAS in a component-based groupware environment");
		integrating.setConferencia("AOSE");
		integrating.setAno(2006);
		autores = Arrays.asList(marta, daniel, paulo);
		integrating.setColaboradorList(autores);
		integrating.setProjeto(esma);
		prodService.incluir(integrating);
		
		Publicacao reputation = new Publicacao();
		reputation.setTitulo("Reputation Model Based on Testimonies");
		reputation.setConferencia("AAMAS");
		reputation.setAno(2006);
		autores = Arrays.asList(maria, bia, carlos);
		reputation.setColaboradorList(autores);
		reputation.setProjeto(esma);
		prodService.incluir(reputation);
		
		Publicacao extensions = new Publicacao();
		extensions.setTitulo("Extensions on Interaction Laws in Open Multi-Agent Systems");
		extensions.setConferencia("SEAS");
		extensions.setAno(2005);
		autores = Arrays.asList(michael);
		extensions.setColaboradorList(autores);
		prodService.incluir(extensions);
		
		Publicacao aspect = new Publicacao();
		aspect.setTitulo("Aspect-oriented Patterns");
		aspect.setConferencia("FLOP");
		aspect.setAno(2006);
		autores = Arrays.asList(soraia, carlos);
		aspect.setColaboradorList(autores);
		aspect.setProjeto(esoa);
		prodService.incluir(aspect);
		
		Publicacao classifying = new Publicacao();
		classifying.setTitulo("Classifying and Describing Agent Contracts and Norms");
		classifying.setConferencia("AAMAS");
		classifying.setAno(2005);
		autores = Arrays.asList(joao, daniel);
		classifying.setColaboradorList(autores);
		classifying.setProjeto(esma);
		prodService.incluir(classifying);
		
		// orientacoes
		Orientacao tcc = new Orientacao();
		tcc.setAluno(maria);
		tcc.setProfessor(carlos);
		tcc.setTitulo("Usabilidade no Portal do Banco do Brasil");
		prodService.incluir(tcc);
		
		Orientacao diss1 = new Orientacao();
		diss1.setAluno(soraia);
		diss1.setProfessor(carlos);
		diss1.setTitulo("Framework para o Cálculo de Reputação de Agentes");
		prodService.incluir(diss1);
		
		Orientacao diss2 = new Orientacao();
		diss2.setAluno(rafael);
		diss2.setProfessor(paulo);
		diss2.setTitulo("Arquitetura para Catálogos de Objetos baseado em Ontologias");
		prodService.incluir(diss2);
		
		Orientacao diss3 = new Orientacao();
		diss3.setAluno(daniel);
		diss3.setProfessor(paulo);
		diss3.setTitulo("Framework para Smart Cards");
		prodService.incluir(diss3);
		
		Orientacao diss4 = new Orientacao();
		diss4.setAluno(bia);
		diss4.setProfessor(carlos);
		diss4.setTitulo("Linguagem de Modelagem para Sistemas baseados em Agentes");
		prodService.incluir(diss4);
	}
	

}
