package br.com.julianograciano.driver;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * Configurações do WebDriver para cada tipo de navegador.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class Capabilities {

	/**
	 * 
	 * Desativar logs.
	 * 
	 */
	static {
		Logger[] pin = new Logger[] { Logger.getLogger("com.gargoylesoftware.htmlunit"),
				Logger.getLogger("org.apache.commons.httpclient"),
				Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake") };

		for (java.util.logging.Logger l : pin) {
			l.setLevel(Level.OFF);
		}
	}

	/**
	 * Retorna as configurações do navegador informado.
	 * 
	 * @param browser
	 * @return capabilities
	 */
	public static MutableCapabilities getBrowserCapabilities(String browser) {
		MutableCapabilities capabilities = null;
		if (browser.equals("chrome")) {
			capabilities = Capabilities.getChromeCapabilities();
		} else if (browser.equals("firefox")) {
			capabilities = Capabilities.getFirefoxCapabilities();
		}
		return capabilities;
	}

	/**
	 * Configurações do navegador Google Chrome.
	 * 
	 * @return chrome options
	 */
	public static ChromeOptions getChromeCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("test-type");
		return options;
	}

	/**
	 * Configurações do navegador Google Chrome em modo headless
	 * 
	 * @return chrome options
	 */
	public static ChromeOptions getChromeHeadlessCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("test-type=ui");
		options.addArguments("disable-gpu");
		options.addArguments("disable-gpu-sandbox");
		options.addArguments("no-sandbox");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("window-size=1280x1024");
		return options;
	}
	
	/**
	 * Configurações do navegador Mozilla Firefox.
	 * 
	 * @return capabilities
	 */
	public static FirefoxOptions getFirefoxCapabilities() {
		FirefoxOptions options = new FirefoxOptions();
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("default");
		options.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		options.setCapability("marionette", true);
		options.setCapability("acceptSslCerts", true);

		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logs.txt");
		return options;
	}

}
