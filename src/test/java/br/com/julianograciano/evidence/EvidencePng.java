package br.com.julianograciano.evidence;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import br.com.julianograciano.utils.DateUtils;

/**
 * Salvar evidências em fomato PNG.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class EvidencePng implements Evidence {

	private WebDriver driver;
	private File dir;
	private List<File> listEvidences;

	/**
	 * Salvar evidências em fomato PNG.
	 * 
	 * @param driver
	 * @param diretorio
	 * 
	 */
	public EvidencePng(WebDriver driver, File dir) {
		this.driver = driver;
		this.dir = dir;
		this.listEvidences = new ArrayList<File>();
	}

	@Override
	public void addEvidence(String title) {
		Date date = new Date();
		String fileName = DateUtils.format(date, "yyyyMMdd hhmmss.SSS.") + Thread.currentThread().getId();
		title += " - " + DateUtils.format(date, "dd/MM/yyyy hh:mm:ss");

		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).withTitle(title).withName(fileName)
				.save(dir.getAbsolutePath());

		listEvidences.add(new File(dir.getAbsolutePath() + File.separator + fileName + ".png"));
	}

	@Override
	public List<File> evidenceList() {
		return listEvidences;
	}

	@Override
	public String getDirectoryOutput() {
		return dir.getAbsolutePath();
	}
}
