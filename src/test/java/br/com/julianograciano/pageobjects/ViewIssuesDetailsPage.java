package br.com.julianograciano.pageobjects;

import java.io.File;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.julianograciano.evidence.Evidence;

/**
 * 
 * Page Object da tela View Issues Details
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class ViewIssuesDetailsPage extends PageObjectBase {

	@FindBy(css = "td:nth-child(3) > form[action='bug_change_status_page.php'] > input.button")
	private WebElement botaoChangeStatusTo;

	@FindBy(name = "new_status")
	private WebElement listaNewStatus;

	@FindBy(name = "resolution")
	private WebElement listaResolution;

	@FindBy(name = "handler_id")
	private WebElement listaAssignedTo;

	@FindBy(name = "bugnote_text")
	private WebElement caixaTextAddNote;

	@FindBy(name = "private")
	private WebElement caixaSelecaoPrivate;

	@FindBy(css = "form[action='bug_update.php'] input[type='submit']")
	private WebElement botaoConfirmIssue;

	@FindBy(css = "td[bgcolor]")
	private WebElement textoStatus;

	@FindBy(id = "ufile[]")
	private WebElement caixaTextoUploadFile;

	@FindBy(css = "#upload_form_open input.button")
	private WebElement botaoUploadFile;

	public ViewIssuesDetailsPage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	public ViewIssuesDetailsPage selecionarListaNewStatus(String newStatus) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaNewStatus, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaNewStatus, newStatus);
		return this;
	}

	public ViewIssuesDetailsPage acionarBotaoChangeStatusTo() {
		evidence.addEvidence("Alteração de Status");
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(botaoChangeStatusTo);
		action.waitingForConditions(conditions).click(botaoChangeStatusTo);
		return new ViewIssuesDetailsPage(driver, evidence, timeoutInSeconds);
	}

	public ViewIssuesDetailsPage selecionarListResolution(String resolution) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaResolution, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaResolution, resolution);
		return this;
	}

	public ViewIssuesDetailsPage selecionarListaAssignedTo(String assignedTo) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaAssignedTo, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaAssignedTo, assignedTo);
		return this;
	}

	public ViewIssuesDetailsPage preencherCaixaTextoAddNote(String note) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextAddNote);
		action.waitingForConditions(conditions).sendkeys(caixaTextAddNote, note);
		return this;
	}

	public ViewIssuesDetailsPage acionarBotaoConfirmIssue() {
		evidence.addEvidence("Confirmar Alteração");
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(botaoConfirmIssue);
		action.waitingForConditions(conditions).click(botaoConfirmIssue);
		evidence.addEvidence("Status Alterado");
		return new ViewIssuesDetailsPage(driver, evidence, timeoutInSeconds);
	}

	public boolean statusAtualizado(String newStatus) {
		String statusAtualizado = action.getText(textoStatus);
		return statusAtualizado.equals(newStatus);
	}

	public ViewIssuesDetailsPage preencherCaixaTextoUploadFile(String uploadFile) {
		File file = new File(uploadFile);
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoUploadFile);
		action.waitingForConditions(conditions).sendkeys(caixaTextoUploadFile, file.getAbsolutePath());
		return this;
	}

	public OperationSuccessfulPage acionarBotaoUploadFile() {
		evidence.addEvidence("Envio de Arquivo");
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(botaoUploadFile);
		action.waitingForConditions(conditions).click(botaoUploadFile);
		return new OperationSuccessfulPage(driver, evidence, timeoutInSeconds);
	}

	public boolean arquivoAnexado(String uploadFile) {
		int beginIndex = uploadFile.lastIndexOf(File.separator);
		int endIndex = uploadFile.length();
		String fileName = uploadFile.substring(beginIndex, endIndex);

		fileName = fileName.replace(File.separator, "");

		String xpathExpression = "// *[@id='attachments']/following::a[text()='" + fileName + "']";
		By locator = By.xpath(xpathExpression);

		WebElement element = action.getWeElement(driver, locator);

		return element != null;
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}

	public static String title(String bugId, String summary) {
		StringBuilder title = new StringBuilder();
		int total = 7 - bugId.length();
		title.append(String.join("", Collections.nCopies(total, "0")));
		title.append(bugId);
		title.append(": ");
		title.append(summary);
		title.append(" - MantisBT");
		return title.toString();
	}
}
