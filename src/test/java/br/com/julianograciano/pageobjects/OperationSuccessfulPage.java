package br.com.julianograciano.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.utils.RegexUtils;

/**
 * 
 * Page Object da tela Operation Successful
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class OperationSuccessfulPage extends PageObjectBase {

	@FindBy(css = "body > div:nth-child(5)")
	private WebElement textoMensagemSucesso;

	@FindBy(css = "span.bracket-link > a")
	private WebElement linkProceed;

	public OperationSuccessfulPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	public boolean mensagemSucesso() {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(textoMensagemSucesso);
		String mensagemSucesso = action.waitingForConditions(conditions).getText(textoMensagemSucesso);
		evidence.addEvidence("Operation Successful");
		return mensagemSucesso.startsWith("Operation successful.");
	}

	public String idIssue() {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(textoMensagemSucesso);
		String mensagemSucesso = action.waitingForConditions(conditions).getText(textoMensagemSucesso);
		String idIssue = RegexUtils.getFirstGroupOcorrence(mensagemSucesso, "\\[ [A-Za-z ]+(\\d+) \\]");
		return idIssue;
	}

	public ViewIssuesPage aguardarAtualizacao() {
		ExpectedCondition<?> condition1 = ExpectedConditions.not(ExpectedConditions.titleIs("MantisBT"));
		ExpectedCondition<?> condition2 = ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("buglist"));
		ExpectedCondition<?> conditions = ExpectedConditions.and(condition1, condition2);
		action.waitingForConditions(conditions);
		evidence.addEvidence("View Issue Page");
		return new ViewIssuesPage(driver, evidence, timeoutInSeconds);
	}

	public ViewIssuesDetailsPage acionarLinkProceed() {
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(linkProceed);
		action.waitingForConditions(conditions).click(linkProceed);
		return new ViewIssuesDetailsPage(driver, evidence, timeoutInSeconds);
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}
}
