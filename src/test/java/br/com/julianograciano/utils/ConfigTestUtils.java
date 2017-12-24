package br.com.julianograciano.utils;

/**
 * 
 * Parâmetros de testes passados por linha de comando.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class ConfigTestUtils {

	/**
	 * 
	 * Url da aplicação.
	 * 
	 * @return urlBase
	 */
	public final static String getUrlBase() {
		String urlBase = System.getProperty("urlBase");
		return urlBase;
	}

	/**
	 * 
	 * Usuário para acesso a aplicação.
	 * 
	 * @return username
	 */
	public final static String getUsername() {
		String username = System.getProperty("username");
		return username;
	}

	/**
	 * 
	 * Senha para acesso.
	 * 
	 * @return password
	 */
	public final static String getPassword() {
		String password = System.getProperty("password");
		return password;
	}

	/**
	 * 
	 * Tempo de esperada entre as requisições, caso não seja informado é
	 * considerado 300 segundos.
	 * 
	 * @return password
	 */
	public static int getTimeOutInSeconds() {
		int timeOutInSeconds;
		try {
			timeOutInSeconds = Integer.valueOf(System.getProperty("timeoutInSeconds"));
		} catch (Exception e) {
			timeOutInSeconds = 300;
		}
		return timeOutInSeconds;
	}

}
