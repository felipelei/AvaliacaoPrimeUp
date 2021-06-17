/**
 * 
 */
package dominio_test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.primeup.dominio.Aluno;
import com.primeup.dominio.AlunoGraduacao;
import com.primeup.infra.AlunoDao;
import com.primeup.init.AppConfig;
import com.primeup.service.ColaboradorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AlunoTest extends Aluno{

	@Autowired
	ColaboradorService cs;

	@Test
	public void testSimulandoBuscapPorId() {
		
		Calendar auxCalendar = Calendar.getInstance();
		
		Aluno joao = new AlunoGraduacao();
		joao.setNome("João");
		joao.setEmail("joao@email.br");
		auxCalendar.set(2005, 2, 1);
		joao.setDataIngresso(auxCalendar.getTime());
		cs.incluir(joao);
		
		long retornoId = 13l;
		
		AlunoDao daoMockado = mock(AlunoDao.class);
        when(daoMockado.obterPorId(1l)).thenReturn(joao); //mock simulando um retorno do objeto AlunoDao
        
        
        assertEquals(joao.getNome(), "João");
        assertEquals(joao.getId(), retornoId, 0.0001); // O id é 13 e não 1, como o mock está simulando!!!
	}
}
