package br.com.julianograciano.testdata;


/**
 * 
 * Manipulação de massa de dados.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public interface TestData {
	
	
	/**
	 * 
	 * Obtém o valor com base na chave informada.
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key);

	/**
	 * 
	 * Cria ou atualiza um valor.
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);
}
