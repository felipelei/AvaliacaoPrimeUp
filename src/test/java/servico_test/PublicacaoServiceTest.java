package servico_test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.primeup.dominio.Publicacao;
import com.primeup.init.AppConfig;
import com.primeup.service.PublicacaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class PublicacaoServiceTest {

	@Autowired
	PublicacaoService ps;
	
	@Test
	public void testarListaDePublicacaoVazia() {
		List<Publicacao> pubs =  ps.obterTodos();
		
		assertEquals(8, pubs.size());
		
		assertEquals(2006, pubs.get(0).getAno() , 0.0001);
        assertEquals(2007, pubs.get(1).getAno(), 0.0001); 
	}
	
	
}
