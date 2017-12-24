package br.com.julianograciano.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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

	public final static WebDriver createInstance(MutableCapabilities capabilities, String browser) {
		WebDriver driver = null;

		if (browser.equals("firefox")) {
			driver = new FirefoxDriver((FirefoxOptions) capabilities);
		} else if (browser.equals("chrome") || browser.equals("chrome-headless")) {
			driver = new ChromeDriver((ChromeOptions) capabilities);
		}

		return driver;
	}

}
