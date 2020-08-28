package pages;

import org.openqa.selenium.WebDriver;

public class PesquisaPage {
	private WebDriver driver;
	
	public PesquisaPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterNomePaginaPesquisa() {
		return driver.getTitle();
	}
	
	
}
