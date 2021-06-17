package servico_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.primeup.service.AlunoService;

public class FuncoesMatematicasTest {

	@Test
	public void somarTest() {
		AlunoService alunoService = new AlunoService();
		
		final int resultadoSoma = 8; 
		
		assertEquals(resultadoSoma, alunoService.somarParcelas(5, 3));
		
	}

}
