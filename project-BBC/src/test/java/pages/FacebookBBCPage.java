package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FacebookBBCPage {
	private WebDriver driver;
	
	private By validarFace = By.cssSelector("#seo_h1_tag > a");

	public FacebookBBCPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obter_resultadoFraseBBC() {
		return driver.findElement(validarFace).getText();
	}
	
}
