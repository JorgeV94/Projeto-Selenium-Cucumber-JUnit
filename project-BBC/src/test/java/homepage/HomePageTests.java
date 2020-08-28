package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.AprendaInglesPage;
import pages.BrasilPage;
import pages.FacebookBBCPage;
import pages.PesquisaPage;
import util.Funcoes;

public class HomePageTests extends BaseTests {
	
	@Test
	public void testValidarPaginaInicial() {
		carregarPaginaInicial();
		assertThat(homePage.obterMensagemProdutoAdicionado(),is("Mais lidas"));
	}
	
	String respostaEsperadaDesmatamento = "Busca para \"desmatamento\" produziu";
	BrasilPage brasilpage;
	String fraseDesm;
	@Test
	public void testValidarPesquisa_Desmatamento() {
		//muda de pagina
		brasilpage = homePage.novaPaginaBrasil();	
		//preencher pesquisa
		brasilpage.preencherPesquisa("desmatamento");
		//clicar no botao lupa para pesquisar
		brasilpage.clicarBotaoPesquisa();
		//Verificar se a frase corresponde a pesquisa
		String fraseDesm = brasilpage.obter_resultadoPesquisa();
		fraseDesm = Funcoes.removeTexto(fraseDesm);
		assertThat(fraseDesm,is(respostaEsperadaDesmatamento));	
	}
	
	String validaFace = "BBC News Brasil";
	FacebookBBCPage facebbc;
	@Test
	public void testValidarFacebookBBC_VerificacaoCorreta(){
		//muda de pagina
		facebbc = homePage.novaPaginaFacebookBBC();
		//Verificar se a pagina contém BBC News Brasil
		String fraseFBBC = facebbc.obter_resultadoFraseBBC();
		assertThat(fraseFBBC,is(validaFace));
	}
	
	String respostaEspRobo = "Busca para \"O robô com sensores nos dedos que separa lixo reciclavel\" produziu 1 resultados";
	AprendaInglesPage aprendaIngles;
	@Test
	public void testValidarPesquisa_LixoReciclavel() {
		//muda de pagina
		aprendaIngles = homePage.novaPaginaAprendaIngles();
		//preencher pesquisa
		aprendaIngles.preencherPesquisaAprendaIng("O robô com sensores nos dedos que separa lixo reciclavel");
		//clicar no botao lupa para pesquisar
		aprendaIngles.clicarBotaoPesquisaAprendaIng();
		//Verificar se a frase corresponde a pesquisa
		String fraseLixoReci = aprendaIngles.obter_resultadoPesquisaAprendaIng();
		assertThat(fraseLixoReci,is(respostaEspRobo));	
	}
	PesquisaPage pesqPage;
	@Test
	public void testPesquisas_Abertura_das_Noticias(String titulo,String tituloLink, String newTitle) {
		aprendaIngles = homePage.novaPaginaAprendaIngles();
		aprendaIngles.preencherPesquisaAprendaIng(titulo);
		aprendaIngles.clicarBotaoPesquisaAprendaIng();
		aprendaIngles.obter_resultadoPesquisaAprendaIng();
		pesqPage = homePage.novaPaginaPesquisa();
		String tituloPagina = pesqPage.obterNomePaginaPesquisa();
		assertThat(tituloPagina,is(newTitle));
		

		
		
	}
	
	
	

}
