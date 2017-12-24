package br.com.julianograciano.test;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

import br.com.julianograciano.utils.ConfigTestUtils;

/**
 * Classe base com parâmetro passados via linha de comando. 
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestBase {

	protected int timeoutInSeconds;
	protected String url;
	protected String username;
	protected String password;

	/**
	 * Inicializa os parâmetros de teste.
	 * 
	 * @param context
	 */
	@BeforeTest
	public void beforeTest(ITestContext context) {
		this.timeoutInSeconds = ConfigTestUtils.getTimeOutInSeconds();
		this.url = ConfigTestUtils.getUrlBase();
		this.username = ConfigTestUtils.getUsername();
		this.password = ConfigTestUtils.getPassword();

		Map<String, String> parameters = context.getCurrentXmlTest().getAllParameters();
		parameters.put("timeoutInSeconds", String.valueOf(timeoutInSeconds));
		parameters.put("url", url);
		parameters.put("username", username);
		parameters.put("password", password);

		context.getCurrentXmlTest().setParameters(parameters);
	}
}
