package br.com.julianograciano.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.julianograciano.evidence.Evidence;

/**
 * Acesso ao Menu do Mantis
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class MenuPage extends PageObjectBase {

	@FindAll({ @FindBy(css = ".menu > a") })
	private List<WebElement> listaMenus;

	@FindBy(name = "bug_id")
	private WebElement caixaTextoBugId;

	@FindBy(css = "form[action='/jump_to_bug.php']> input.button-small[type='submit']")
	private WebElement botaoJump;

	public MenuPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	public ReportIssuePage menuReportIssue() {
		evidence.addEvidence("My View");
		WebElement menu = localizarMenu("Report Issue");
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(menu);
		action.waitingForConditions(conditions).click(menu);
		return new ReportIssuePage(driver, evidence, timeoutInSeconds);
	}

	public MenuPage preencherCaixaTextoBugId(String bugId) {
		ExpectedCondition<?> condition = ExpectedConditions.visibilityOf(caixaTextoBugId);
		action.waitingForConditions(condition).sendkeys(caixaTextoBugId, bugId);
		return this;
	}

	public ViewIssuesDetailsPage acionarBotaoJump() {
		evidence.addEvidence("Jump to Bug Id");
		ExpectedCondition<?> condition = ExpectedConditions.elementToBeClickable(botaoJump);
		action.waitingForConditions(condition).click(botaoJump);
		return new ViewIssuesDetailsPage(driver, evidence, timeoutInSeconds);
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}

	private WebElement localizarMenu(final String menu) {
		return listaMenus.stream().filter(m -> {
			ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(m);
			return action.waitingForConditions(conditions).getText(m).equals(menu);

		}).findFirst().orElse(null);
	}
}
