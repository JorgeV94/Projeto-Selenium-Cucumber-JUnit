package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class BrasilPage {
	private WebDriver driver;
	
	private By campopesquisa = By.id("orb-search-q");
	private By botaoPesq = By.id("orb-search-button");
	private By respostaPesqu = By.className("ws-search-results__message");

	public BrasilPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void preencherPesquisa(String texto) {
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))// verifica em x segundos se a condicao de fim de espera foi atingida
				.ignoring(NoSuchElementException.class); // espera que dura 5 secundos ignorando a execessao
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(campopesquisa));
		driver.findElement(campopesquisa).sendKeys(texto);
	} 
	
	public void clicarBotaoPesquisa() {
		driver.findElement(botaoPesq).click();
	}
	public String obter_resultadoPesquisa() {
		return driver.findElement(respostaPesqu).getText();
	}

	public String obterNomePagina() {
		return driver.getTitle();
	}
	

}
