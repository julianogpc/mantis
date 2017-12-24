package br.com.julianograciano.pageobjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.julianograciano.evidence.Evidence;

/**
 * Page Object da tela Report Issue.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class ReportIssuePage extends PageObjectBase {

	@FindBy(name = "category_id")
	private WebElement listaCategory;

	@FindBy(name = "reproducibility")
	private WebElement listaReproducibility;

	@FindBy(name = "severity")
	private WebElement listaSeverity;

	@FindBy(name = "priority")
	private WebElement listaPriority;

	@FindBy(name = "profile_id")
	private WebElement listaSelectProfile;

	@FindBy(id = "platform")
	private WebElement caixaTextoPlatform;

	@FindBy(id = "os")
	private WebElement caixaTextoOS;

	@FindBy(id = "os_build")
	private WebElement caixaTextoOSVersion;

	@FindBy(name = "handler_id")
	private WebElement listaAssignTo;

	@FindBy(name = "summary")
	private WebElement caixaTextoSummary;

	@FindBy(name = "description")
	private WebElement caixaTextoDescription;

	@FindBy(name = "steps_to_reproduce")
	private WebElement caixaTextoStepsToReproduce;

	@FindBy(name = "additional_info")
	private WebElement caixaTextoAdditionalInformation;

	@FindBy(id = "ufile[]")
	private WebElement caixaTextoUploadFile;

	@FindAll({ @FindBy(xpath = "//input[@name='view_state']/..") })
	private List<WebElement> caixaSelecaoViewStatus;

	@FindBy(id = "report_stay")
	private WebElement caixaSelecaoReportStay;

	@FindBy(css = "input.button[type='submit']")
	private WebElement botaoSubmitReport;

	public ReportIssuePage(WebDriver driver, Evidence evidence, int timeoutInSeconds) {
		super(driver, evidence, timeoutInSeconds);
		this.initElements();
	}

	public ReportIssuePage selecionarListaCategory(String category) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaCategory, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaCategory, category);
		return this;
	}

	public ReportIssuePage selecionarListaReproducibility(String reproducibility) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaReproducibility, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaReproducibility, reproducibility);
		return this;
	}

	public ReportIssuePage selecionarListaSeverity(String severity) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaSeverity, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaSeverity, severity);
		return this;
	}

	public ReportIssuePage selecionarListaPriority(String priority) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaPriority, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaPriority, priority);
		return this;
	}

	public ReportIssuePage selecionarListaSelectProfile(String profile) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaSelectProfile, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaSelectProfile, profile);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoPlatform(String platform) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoPlatform);
		action.waitingForConditions(conditions).sendkeys(caixaTextoPlatform, platform);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoOS(String os) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoOS);
		action.waitingForConditions(conditions).sendkeys(caixaTextoOS, os);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoOSVersion(String osVersion) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoOSVersion);
		action.waitingForConditions(conditions).sendkeys(caixaTextoOSVersion, osVersion);
		return this;
	}

	public ReportIssuePage selecionarListaAssignTo(String assignTo) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementSelectionStateToBe(listaAssignTo, false);
		action.waitingForConditions(conditions).selectByVisibleText(listaAssignTo, assignTo);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoSummary(String summary) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoSummary);
		action.waitingForConditions(conditions).sendkeys(caixaTextoSummary, summary);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoDescription(String description) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoDescription);
		action.waitingForConditions(conditions).sendkeys(caixaTextoDescription, description);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoStepsToReproduce(String stepsToReproduce) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoStepsToReproduce);
		action.waitingForConditions(conditions).sendkeys(caixaTextoStepsToReproduce, stepsToReproduce);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoAdditionalInformation(String additionalInformation) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoAdditionalInformation);
		action.waitingForConditions(conditions).sendkeys(caixaTextoAdditionalInformation, additionalInformation);
		return this;
	}

	public ReportIssuePage preencherCaixaTextoUploadFile(String uploadFile) {
		if (uploadFile.equals(""))
			return this;
		File file = new File(uploadFile);
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOf(caixaTextoUploadFile);
		action.waitingForConditions(conditions).sendkeys(caixaTextoUploadFile, file.getAbsolutePath());
		return this;
	}

	public ReportIssuePage caixaSelecaoViewStatus(String viewStatus) {
		ExpectedCondition<?> conditions = ExpectedConditions.visibilityOfAllElements(caixaSelecaoViewStatus);
		WebElement opcao = action.waitingForConditions(conditions).getElementWithText(caixaSelecaoViewStatus,
				viewStatus);
		conditions = ExpectedConditions.elementToBeClickable(opcao);
		action.waitingForConditions(conditions).click(opcao);
		return this;
	}

	public ReportIssuePage caixaSelecaoReportStay(boolean reportStay) {
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(caixaSelecaoReportStay);

		boolean click = (!caixaSelecaoReportStay.isSelected() && reportStay)
				|| (caixaSelecaoReportStay.isSelected() && !reportStay);
		if (click) {
			action.waitingForConditions(conditions).click(caixaSelecaoReportStay);
		}
		return this;
	}

	public OperationSuccessfulPage botaoSubmitReport() {
		evidence.addEvidence("Report Issue");
		ExpectedCondition<?> conditions = ExpectedConditions.elementToBeClickable(botaoSubmitReport);
		action.waitingForConditions(conditions).click(botaoSubmitReport);
		return new OperationSuccessfulPage(driver, evidence, timeoutInSeconds);
	}

	@Override
	public MenuPage menu() {
		return new MenuPage(driver, evidence, timeoutInSeconds);
	}
}
