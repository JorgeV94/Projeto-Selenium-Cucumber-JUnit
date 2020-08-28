package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import java.io.File;
//import java.io.IOException;
//import com.google.common.io.Files;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.pt.Dado;
//import io.cucumber.java.pt.Então;
//import io.cucumber.java.pt.Quando;
//import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import pages.AprendaInglesPage;
import pages.BrasilPage;
import pages.FacebookBBCPage;
import pages.HomePage;
import pages.PesquisaPage;
import util.Funcoes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NavegandoSiteFeatures {

	private static WebDriver driver;
	private HomePage homepage = new HomePage(driver);

	@Before
	public static void inicializar() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/jorgevieira/iterasys-workspace/drivers/Chrome/84/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// qtd de tempo que o driver espera por um
																		// elemento quando ele nao esta presente
	}

	BrasilPage brasilpage;

	@Dado("usuario acesse a página principal")
	public void usuario_acesse_a_página_principal() {
		homepage.carregarPaginaInicial();
		assertThat(homepage.obterNomePagina(),
				is("Notícias, vídeos, análise e contexto em português - BBC News Brasil"));
	}

	@Dado("clica na aba Brazil")
	public void clica_na_aba_brazil() {
		brasilpage = homepage.novaPaginaBrasil();
	}

	@Quando("digita desmatamento no campo pesquisa")
	public void digita_desmatamento_no_campo_pesquisa() {
		brasilpage.preencherPesquisa("desmatamento");
		brasilpage.clicarBotaoPesquisa();
	}

	String respostaEsperadaDesmatamento = "Busca para \"desmatamento\" produziu";
	String fraseDesm;

	@Então("deve ver que a mensagem contém: Busca para desmatamento produziu")
	public void deve_ver_que_a_mensagem_contém_busca_para_desmatamento_produziu() {
		String fraseDesm = brasilpage.obter_resultadoPesquisa();
		fraseDesm = Funcoes.removeTexto(fraseDesm);
		assertThat(fraseDesm, is(respostaEsperadaDesmatamento));
	}

	FacebookBBCPage facebbc;
	String validaFace = "BBC News Brasil";

	@Quando("clico em Facebook na pagina da BBC")
	public void clico_em_facebook_na_pagina_da_bbc() {
		facebbc = homepage.novaPaginaFacebookBBC();
	}

	@Então("devo ver que a nova página contém BBC News Brasil.")
	public void devo_ver_que_a_nova_página_contém_bbc_news_brasil() {
		String fraseFBBC = facebbc.obter_resultadoFraseBBC();
		assertThat(fraseFBBC, is(validaFace));
	}

	String respostaEspRobo = "Busca para \"O robô com sensores nos dedos que separa lixo reciclavel\" produziu 1 resultados";
	AprendaInglesPage aprendaIngles;

	@Dado("clico na aba Aprenda Inglês")
	public void clico_na_aba_aprenda_inglês() {
		aprendaIngles = homepage.novaPaginaAprendaIngles();

	}

	@Quando("digito no campo pesquisa O robô com sensores nos dedos que separa lixo reciclavel")
	public void digito_no_campo_pesquisa_o_robô_com_sensores_nos_dedos_que_separa_lixo_reciclavel() {
		aprendaIngles.preencherPesquisaAprendaIng("O robô com sensores nos dedos que separa lixo reciclavel");
		aprendaIngles.clicarBotaoPesquisaAprendaIng();
	}

	@Então("deve ver que a mensagem contém Busca para pesquisa acima produziu {int} resultados.")
	public void deve_ver_que_a_mensagem_contém_busca_para_pesquisa_acima_produziu_resultados(Integer int1) {
		String fraseLixoReci = aprendaIngles.obter_resultadoPesquisaAprendaIng();
		assertThat(fraseLixoReci, is(respostaEspRobo));
	}

	PesquisaPage pesqPage;

	@Dado("pesquiso no campo de pesquisa {string}")
	public void pesquiso_no_campo_de_pesquisa(String string) {
		aprendaIngles.preencherPesquisaAprendaIng(string);
		aprendaIngles.clicarBotaoPesquisaAprendaIng();
		aprendaIngles.obter_resultadoPesquisaAprendaIng();
	}

	@Quando("clico no link")
	public void clico_no_link() {
		pesqPage = homepage.novaPaginaPesquisa();
	}

	@Então("uma nova página abrirá e devo ver o título {string}")
	public void uma_nova_página_abrirá_e_devo_ver_o_título(String string) {
		String tituloPagina = pesqPage.obterNomePaginaPesquisa();
		assertThat(tituloPagina, is(string));
	}
	
	@After(order = 1)
	public void capturarTela(Scenario scenario) {
		var camera = (TakesScreenshot) driver;
		File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
		System.out.println(scenario.getId());
		
		String scenarioId = scenario.getId().substring(scenario.getId().lastIndexOf(".feature:")+9);
		
		String nomeArquivo = "resources/Screenshots/"+scenario.getName()+"_"+scenarioId+"_"+scenario.getStatus()+".png";
		System.out.println(nomeArquivo);
		try {
			Files.move(capturaDeTela, new File(nomeArquivo));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}


	@After(order = 0)
	public static void finalizar() {
		driver.quit();
	}

}
