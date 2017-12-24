package br.com.julianograciano.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.pageobjects.LoginPage;
import br.com.julianograciano.pageobjects.MyViewPage;
import br.com.julianograciano.pageobjects.ViewIssuesDetailsPage;
import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testng.DataDriven;

/**
 * Teste de alteração de status de issue existente.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestAlterarStatus extends TestBase {

	@Test(dataProviderClass = DataDriven.class, dataProvider = "testDataExcel")
	public void alterarStatus(TestData massaDados, ITestContext context) {
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		Evidence evidence = (Evidence) context.getAttribute("evidence");

		// Tela login
		LoginPage loginPage = new LoginPage(driver, evidence, timeoutInSeconds);
		loginPage.visita(url);
		Assert.assertEquals(loginPage.getTitle(), "MantisBT");
		MyViewPage myViewPage = loginPage.autentica(username, password);

		// Tela My View
		Assert.assertEquals(myViewPage.getTitle(), "My View - MantisBT");
		ViewIssuesDetailsPage viewIssuesDetailsPage = myViewPage.menu()
				.preencherCaixaTextoBugId(massaDados.get("Bug ID")).acionarBotaoJump();

		// Tela View Issues Details Page
		Assert.assertEquals(viewIssuesDetailsPage.getTitle(),
				ViewIssuesDetailsPage.title(massaDados.get("Bug ID"), massaDados.get("Summary")));
		viewIssuesDetailsPage.selecionarListaNewStatus(massaDados.get("Change Status To")).acionarBotaoChangeStatusTo();

		if (massaDados.get("Change Status To").equals("closed")
				|| massaDados.get("Change Status To").equals("resolved")) {
			viewIssuesDetailsPage.selecionarListResolution(massaDados.get("Resolution"));
		}

		viewIssuesDetailsPage.selecionarListaAssignedTo(massaDados.get("Assigned To"))
				.preencherCaixaTextoAddNote(massaDados.get("Add Note")).acionarBotaoConfirmIssue();

		Assert.assertTrue(viewIssuesDetailsPage.statusAtualizado(massaDados.get("Change Status To")),
				"Status do bug não foi atualizado.");
	}
}
