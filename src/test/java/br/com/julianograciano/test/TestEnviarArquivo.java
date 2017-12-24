package br.com.julianograciano.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.pageobjects.LoginPage;
import br.com.julianograciano.pageobjects.MyViewPage;
import br.com.julianograciano.pageobjects.OperationSuccessfulPage;
import br.com.julianograciano.pageobjects.ViewIssuesDetailsPage;
import br.com.julianograciano.testdata.TestData;
import br.com.julianograciano.testng.DataDriven;

/**
 * Teste de alteração de status de issue existente.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestEnviarArquivo extends TestBase {

	@Test(dataProviderClass = DataDriven.class, dataProvider = "testDataExcel")
	public void enviarArquivo(TestData massaDados, ITestContext context) {
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

		OperationSuccessfulPage operationSuccessfulPage = viewIssuesDetailsPage
				.preencherCaixaTextoUploadFile(massaDados.get("Upload File")).acionarBotaoUploadFile();

		// Tela Operation Successful Page
		Assert.assertTrue(operationSuccessfulPage.mensagemSucesso(), "Erro no envio de arquivo.");

		// Tela View Issues Details Page
		viewIssuesDetailsPage = operationSuccessfulPage.acionarLinkProceed();

		Assert.assertTrue(viewIssuesDetailsPage.arquivoAnexado(massaDados.get("Upload File")),
				"Não foi exibido o nome do arquivo na tela de detalhes.");
	}
}
