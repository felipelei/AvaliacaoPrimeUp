package dominio_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.primeup.dominio.Aluno;
import com.primeup.dominio.AlunoGraduacao;
import com.primeup.dominio.Colaborador;
import com.primeup.dominio.Projeto;
import com.primeup.init.AppConfig;
import com.primeup.service.ColaboradorService;
import com.primeup.service.PublicacaoService;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ColaboradorTest{

	@Autowired
	ColaboradorService cs;
	
	@Autowired
	PublicacaoService ps;
	
	private static Aluno aluno10;
	private final String NOME_ALUNO10 = "Aluno 10";
	
	@Before
	public void CadastroColaborador() {
		Calendar auxCalendar = Calendar.getInstance();
		
		aluno10 = new AlunoGraduacao();
		aluno10.setNome(NOME_ALUNO10);
		aluno10.setEmail("aluno10@aluno.com.br");
		auxCalendar.set(2000, 1, 20);
		aluno10.setDataIngresso(auxCalendar.getTime());
		cs.incluir(aluno10);
	}

	@Test
	public void testObterPorId() {
		Colaborador colaborador = cs.obterPorId(aluno10.getId());
		
		assertEquals(colaborador.getNome(), NOME_ALUNO10);
	}
	
	@Test(expected=RuntimeException.class)
	public void testNaodevepermitirProjetoVazio() {
		Aluno aluno = new AlunoGraduacao();
		List<Projeto> projetos = new ArrayList<Projeto>(); 
		aluno.setProjetoList(projetos);
		aluno.addProjeto(null);
		
		cs.incluir(aluno);
		
		assertEquals(aluno.getNome(), "Aluno");
	}
	
	@Test
	public void testUtilizandoVerify() {
		
		Calendar auxCalendar = Calendar.getInstance();
		
		Aluno maria = new AlunoGraduacao();
		maria.setNome("Maria");
		maria.setId(2l);
		maria.setEmail("maria@email.br");
		auxCalendar.set(2015, 5, 5);
		maria.setDataIngresso(auxCalendar.getTime());
		
        ColaboradorService csMockado = mock(ColaboradorService.class);
        Colaborador colaborador = csMockado.atualizarObterPorIdParaVisualizacao(maria);
        
		verify(csMockado, times(1)).atualizarObterPorIdParaVisualizacao(maria);
	}
}

