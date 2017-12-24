package br.com.julianograciano.testng;

import java.io.File;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import br.com.julianograciano.evidence.Evidence;
import br.com.julianograciano.evidence.EvidencePng;
import br.com.julianograciano.utils.WebDriverUtils;

/**
 * 
 * Implementa as interfaces de listeners do TestNG.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestNGListener extends AbstractTestNGListener {
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		if (method.getTestMethod().isTest()) {
			String browser = context.getCurrentXmlTest().getParameter("browser");
			WebDriverUtils.startWebDriver(browser);

			File dir = new File(context.getOutputDirectory() + File.separator + context.getName());
			Evidence evidence = new EvidencePng(WebDriverUtils.getWebDriver(), dir);
			context.setAttribute("evidence", evidence);
			context.setAttribute("driver", WebDriverUtils.getWebDriver());
		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		if (method.getTestMethod().isTest()) {
			Evidence evidence = (Evidence) context.getAttribute("evidence");
			if (!testResult.isSuccess()) {
				evidence.addEvidence("Erro");
			}

			WebDriverUtils.stopWebDriver();

			linkImageToReport(evidence.evidenceList());
		}
	}

	/**
	 * 
	 * Inserir imagens geradas durante a execução no relatório do TestNG.
	 * 
	 * @param files
	 */
	public static void linkImageToReport(List<File> files) {
		for (File file : files) {
			String fileName = file.toURI().toString();
			String tag = "<img src=\"file://" + fileName + "\" alt=\"\">";
			Reporter.log(tag);
		}
	}

}
