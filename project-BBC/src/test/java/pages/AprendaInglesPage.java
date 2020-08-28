package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AprendaInglesPage {
	private WebDriver driver;
	
	private By campopesquisaA = By.id("orb-search-q");
	private By botaoPesqA = By.id("orb-search-button");
	private By respostaPesquA = By.className("ws-search-results__message");


	
	public AprendaInglesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void preencherPesquisaAprendaIng(String texto) {
		driver.findElement(campopesquisaA).sendKeys(texto);
	}

	public void clicarBotaoPesquisaAprendaIng() {
		driver.findElement(botaoPesqA).click();
	}
	
	public String obter_resultadoPesquisaAprendaIng() {
		return driver.findElement(respostaPesquA).getText();
	}
	
	
	
}
