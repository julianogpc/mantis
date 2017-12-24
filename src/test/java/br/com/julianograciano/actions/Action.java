package br.com.julianograciano.actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

/**
 * Ações do WebDriver.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class Action {
	public static int pooling = 1;
	private WebDriver driver;
	private int timeoutInSeconds;

	public Action(WebDriver driver, int timeoutInSeconds) {
		this.driver = driver;
		this.timeoutInSeconds = timeoutInSeconds;
	}

	/**
	 * Espera até que a condição seja verdadeira.
	 */
	public Action waitingForConditions(final ExpectedCondition<?> conditions) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		wait.pollingEvery(pooling, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.until(conditions);
		return this;
	}

	/**
	 * Clicar em um elemento no navegador.
	 */
	public Action click(WebElement element) {
		element.click();
		return this;
	}

	/**
	 * Enviar texto para um elemento no navegador.
	 */
	public Action sendkeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
		return this;
	}

	/**
	 * Acessar URL.
	 */
	public Action navigateTo(String url) {
		driver.get(url);
		return this;
	}

	/**
	 * Retorna o título da página.
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Retorna o texto de um elemento.
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * Seleciona um element baseado no texto visível.
	 */
	public Action selectByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		return this;
	}

	/**
	 * Seleciona um elemento baseado no texto visível.
	 */
	public WebElement getElementWithText(List<WebElement> elements, String value) {
		return elements.stream().filter(e -> {
			return e.getText().equals(value);
		}).findFirst().orElse(null);
	}

	/**
	 * Localiza um elemento.
	 * 
	 * @param driver
	 * @param locator
	 * @return webElement
	 */
	public WebElement getWeElement(WebDriver driver, By locator) {
		try {
			WebElement element = driver.findElement(locator);
			return element;
		} catch (NoSuchElementException e) {
			return null;
		}
	}

}
