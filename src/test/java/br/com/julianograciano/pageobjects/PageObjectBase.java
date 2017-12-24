package br.com.julianograciano.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.julianograciano.actions.Action;
import br.com.julianograciano.evidence.Evidence;

/**
 * Classe base para PageObjects
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public abstract class PageObjectBase {
	protected WebDriver driver;
	protected Evidence evidence;
	protected int timeoutInSeconds;
	protected Action action;

	/**
	 * 
	 * Construtor padrão da classe PageObjectBase
	 * 
	 * @param driver
	 * @param evidence
	 * @param timeoutInSeconds
	 */
	public PageObjectBase(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		this.driver = driver;
		this.evidence = evidence;
		this.timeoutInSeconds = timeoutInSeconds;
		this.action = new Action(driver, timeoutInSeconds);
	}

	/**
	 * Inicia elementos em uma Page Object
	 * 
	 */
	protected void initElements() {
		PageFactory.initElements(this.driver, this);
	}

	/**
	 * @return título da página
	 */
	public String getTitle() {
		return action.getTitle();
	}

	/**
	 * Acesso ao menu
	 * 
	 * @return 
	 */
	public abstract MenuPage menu();
}
