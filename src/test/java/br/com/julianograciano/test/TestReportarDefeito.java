package br.com.julianograciano.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.pageobjects.LoginPage;
import br.com.julianograciano.pageobjects.MyViewPage;
import br.com.julianograciano.pageobjects.OperationSuccessfulPage;
import br.com.julianograciano.pageobjects.ReportIssuePage;
import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testng.DataDriven;

/**
 * Teste de criação de um issue.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestReportarDefeito extends TestBase {

	@Test(dataProviderClass = DataDriven.class, dataProvider = "testDataExcel")
	public void reportarDefeito(TestData massaDados, ITestContext context) {
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		Evidence evidence = (Evidence) context.getAttribute("evidence");

		// Tela login
		LoginPage loginPage = new LoginPage(driver, evidence, timeoutInSeconds);
		loginPage.visita(url);
		Assert.assertEquals(loginPage.getTitle(), "MantisBT");
		MyViewPage myViewPage = loginPage.autentica(username, password);

		// Tela My View
		Assert.assertEquals(myViewPage.getTitle(), "My View - MantisBT");
		ReportIssuePage reportIssuePage = myViewPage.menu().menuReportIssue();

		// Tela Report Issue
		Assert.assertEquals(reportIssuePage.getTitle(), "Report Issue - MantisBT");
		reportIssuePage.selecionarListaCategory(massaDados.get("Category"))
				.selecionarListaReproducibility(massaDados.get("Reproducibility"))
				.selecionarListaSeverity(massaDados.get("Severity")).selecionarListaPriority(massaDados.get("Priority"))
				.selecionarListaSelectProfile(massaDados.get("Select Profile"));

		reportIssuePage.preencherCaixaTextoPlatform(massaDados.get("Platform"))
				.preencherCaixaTextoOS(massaDados.get("OS")).preencherCaixaTextoOSVersion(massaDados.get("OS Version"));

		reportIssuePage.selecionarListaAssignTo(massaDados.get("Assign To"))
				.preencherCaixaTextoSummary(massaDados.get("Summary"))
				.preencherCaixaTextoDescription(massaDados.get("Description"))
				.preencherCaixaTextoStepsToReproduce(massaDados.get("Steps To Reproduce"))
				.preencherCaixaTextoAdditionalInformation(massaDados.get("Additional Information"));

		reportIssuePage.preencherCaixaTextoUploadFile(massaDados.get("Upload File"))
				.caixaSelecaoViewStatus(massaDados.get("View Status"));

		OperationSuccessfulPage operationSuccessfulPage = reportIssuePage.botaoSubmitReport();

		Assert.assertTrue(operationSuccessfulPage.mensagemSucesso(), "Erro ao reportar o defeito.");
		massaDados.set("Issue", operationSuccessfulPage.idIssue());
		operationSuccessfulPage.aguardarAtualizacao();
	}
}
