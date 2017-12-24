package br.com.julianograciano.evidence;

import java.io.File;
import java.util.List;

/**
 * Interface para gerar evidências.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public interface Evidence {
	/**
	 * Salva um evidência de execução do teste.
	 */
	public void addEvidence(String title);

	/**
	 * 
	 * Diretório de saída das evidências
	 * 
	 * @return
	 */
	public String getDirectoryOutput();

	/**
	 * Lista de evidências salvas.
	 * 
	 * @return
	 */
	public List<File> evidenceList();

}
