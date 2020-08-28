package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePage {
	private WebDriver driver;
	
	private By fraseValida = By.id("Most-Read"); 
	
	private By newPageB = By.cssSelector("#root > header > nav > div > div.ScrollableWrapper-t4argr-0.UDeIc > div > ul > li:nth-child(2) > a");	
	private By newPageFace = By.cssSelector("#root > main > div > section:nth-child(17) > ul > li:nth-child(1) > div > div.TextGridItem-sc-1lkbq5i-0.jbyRcL > div > a");

	private By newPageIngl = By.cssSelector("#root > header > nav > div > div.ScrollableWrapper-t4argr-0.UDeIc > div > ul > li:nth-child(8) > a");
	
	private By newPageLinkNoticia = By.cssSelector("#page > div > div > div.column--primary > div.ws-search-components > div.hard-news-unit.hard-news-unit--regular.faux-block-link > div > h3 > a\n");

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void carregarPaginaInicial() {
		driver.get("https://www.bbc.com/portuguese");
		
	}
	
	public String obterMensagemProdutoAdicionado() {
		return driver.findElement(fraseValida).getText();

	}
	
	public BrasilPage novaPaginaBrasil() {
		driver.findElement(newPageB).click();
		return new BrasilPage(driver);
	}
	
	public FacebookBBCPage novaPaginaFacebookBBC() {
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1))// verifica em x segundos se a condicao de fim de espera foi atingida
				.ignoring(NoSuchElementException.class); // espera que dura 5 secundos ignorando a execessao
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(newPageFace));
		driver.findElement(newPageFace).click();
		return new FacebookBBCPage(driver);
	}
	
	public AprendaInglesPage novaPaginaAprendaIngles() {
		driver.findElement(newPageIngl).click();
		return new AprendaInglesPage(driver);
	}

	public String obterNomePagina() {
		return driver.getTitle();
	}
	
	public PesquisaPage novaPaginaPesquisa() {
		driver.findElement(newPageLinkNoticia).click();
		return new PesquisaPage(driver);
	}
	


}
