package br.com.julianograciano.pageobjects;

import org.openqa.selenium.WebDriver;

import br.com.julianograciano.evidence.Evidence;

/**
 * 
 * Page Object da tela View Issues
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class ViewIssuesPage extends PageObjectBase {

	public ViewIssuesPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}

}
