package br.com.julianograciano.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * Criação da instância do WebDriver.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class DriverFactory {
	public final static DriverProvider createInstance() {
		DriverProvider driver = new DriverProvider();
		return driver;
	}
	
	public final static WebDriver createInstance(String browser) {
		WebDriver driver = null;

		if (browser.equals("firefox")) {
			driver = new FirefoxDriver(Capabilities.getFirefoxCapabilities());
		} else if (browser.equals("chrome")) {
			driver = new ChromeDriver(Capabilities.getChromeCapabilities());
		}else if (browser.equals("chrome-headless")) {
			driver = new ChromeDriver(Capabilities.getChromeHeadlessCapabilities());
		}

		return driver;
	}

}
