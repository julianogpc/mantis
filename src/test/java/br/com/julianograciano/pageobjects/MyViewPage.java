package br.com.julianograciano.pageobjects;

import org.openqa.selenium.WebDriver;

import br.com.julianograciano.evidence.Evidence;

/**
 * Page object da tela My View.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class MyViewPage extends PageObjectBase {

	public MyViewPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}

}
