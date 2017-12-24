package br.com.julianograciano.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.julianograciano.evidence.Evidence;

/**
 * Page Object da tela de Login
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class LoginPage extends PageObjectBase {

	@FindBy(name = "username")
	private WebElement caixaTextoBotaoUsername;

	@FindBy(name = "password")
	private WebElement caixaTextoBotaoPassword;

	@FindBy(css = "input.button[type='submit']")
	private WebElement botaoLogin;

	public LoginPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	public LoginPage visita(String url) {
		action.navigateTo(url);
		return this;
	}

	public MyViewPage autentica(String username, String password) {
		preencherCaixaTextoUsername(username);
		preencherCaixaTextoPassword(password);
		return acionarBotaoLogin();
	}

	private LoginPage preencherCaixaTextoUsername(String username) {
		ExpectedCondition<?> condition = ExpectedConditions.visibilityOf(caixaTextoBotaoUsername);
		action.waitingForConditions(condition).sendkeys(caixaTextoBotaoUsername, username);
		return this;
	}

	private LoginPage preencherCaixaTextoPassword(String username) {
		ExpectedCondition<?> condition = ExpectedConditions.visibilityOf(caixaTextoBotaoPassword);
		action.waitingForConditions(condition).sendkeys(caixaTextoBotaoPassword, username);
		return this;
	}

	private MyViewPage acionarBotaoLogin() {
		evidence.addEvidence("Usuário e Senha");
		ExpectedCondition<?> condition = ExpectedConditions.elementToBeClickable(botaoLogin);
		action.waitingForConditions(condition).click(botaoLogin);
		return new MyViewPage(driver, evidence, timeoutInSeconds);
	}

	@Override
	public MenuPage menu() {
		return null;
	}

}
