package br.com.julianograciano.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.pageobjects.LoginPage;
import br.com.julianograciano.pageobjects.MyViewPage;
import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testng.DataDriven;

/**
 *Teste de login com sucesso.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestLogin extends TestBase {

	@Test(dataProviderClass = DataDriven.class, dataProvider = "testDataExcel")
	public void loginComSucesso(TestData massaDados, ITestContext context) {
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		Evidence evidence = (Evidence) context.getAttribute("evidence");

		LoginPage loginPage = new LoginPage(driver, evidence, timeoutInSeconds);
		loginPage.visita(url);
		Assert.assertEquals(loginPage.getTitle(), "MantisBT");

		MyViewPage myViewPage = loginPage.autentica(username, password);
		Assert.assertEquals(myViewPage.getTitle(), "My View - MantisBT");
		evidence.addEvidence("Usuário Autenticado");

	}
}
