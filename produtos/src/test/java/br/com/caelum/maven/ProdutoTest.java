package br.com.caelum.maven;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProdutoTest {

	@Test
	public void deveSetarAsVariaveis(){
		Produto produto = new Produto("bala", 15.30);
		assertEquals(15.30, produto.getPreco(), 0.0);
		assertEquals("bala", produto.getNome());
	}
}
