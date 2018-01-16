package br.com.julianograciano.utils;

import org.openqa.selenium.WebDriver;

import br.com.julianograciano.driver.DriverFactory;
import br.com.julianograciano.driver.DriverManager;
import br.com.julianograciano.driver.DriverProvider;

/**
 * 
 * Controle do Selenium WebDriver de forma segura.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class WebDriverUtils {
	/**
	 * 
	 * Inicializa uma instância do WebDriver para a thread atual.
	 * 
	 * @param browser
	 */
	public static final void startWebDriver(String browser) {
		DriverManager.setDriverProvider(DriverFactory.createInstance());
		DriverManager.getDriverProvider().setWebDriver(DriverFactory.createInstance(browser));
		if (!browser.equals("chrome-headless"))
			DriverManager.getDriverProvider().getWebDriver().manage().window().maximize();
		DriverManager.getDriverProvider().getWebDriver().manage().deleteAllCookies();
	}

	/**
	 * 
	 * Instância do WebDriver para a thread atual.
	 * 
	 * @return driver
	 */
	public static final WebDriver getWebDriver() {
		return DriverManager.getDriverProvider().getWebDriver();
	}

	/**
	 * Verifica se a instância foi inicializada.
	 * 
	 * @return running
	 */
	public static final boolean isRunning() {
		return DriverManager.getDriverProvider() != null;
	}

	/**
	 * Finaliza a instância do WebDriver para a thread atual.
	 */
	public static final void stopWebDriver() {
		DriverProvider driverProvider = DriverManager.getDriverProvider();

		if (driverProvider != null) {
			WebDriver driver = DriverManager.getDriverProvider().getWebDriver();

			if (driver != null)
				driver.close();
		}

	}
}
