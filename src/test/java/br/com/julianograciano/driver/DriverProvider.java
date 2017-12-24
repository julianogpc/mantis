package br.com.julianograciano.driver;

import org.openqa.selenium.WebDriver;

/**
 * WebDriver para execução da automação.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class DriverProvider {

	private WebDriver webDriver;

	public WebDriver getWebDriver() {
		return webDriver;
	}


	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

}
