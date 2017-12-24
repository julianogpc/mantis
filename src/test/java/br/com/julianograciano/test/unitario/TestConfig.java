package br.com.julianograciano.test.unitario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.julianograciano.utils.ConfigTestUtils;

/**
 * 
 * Teste dos parâmetros passados por linha de comando.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public class TestConfig {

	/**
	 * Validar se o parâmetro -DurlBase foi passado com sucesso.
	 */
	@Test(priority = 1)
	public void testConfigTestUrlBase() {
		String urlBase = ConfigTestUtils.getUrlBase();
		Assert.assertNotNull(urlBase, "Parâmetro urlBase não informado.");
		System.out.println("URL Base: " + urlBase);
	}

	/**
	 * Validar se o parâmetro -Dusername foi passado com sucesso.
	 */
	@Test(priority = 2)
	public void testConfigTestUsername() {
		String username = ConfigTestUtils.getUsername();
		Assert.assertNotNull(username, "Parâmetro username não informado.");
		System.out.println("Username: " + username);
	}

	/**
	 * Validar se o parâmetro -Dpassword foi passado com sucesso.
	 */
	@Test(priority = 3)
	public void testConfigTestPassword() {
		String password = ConfigTestUtils.getPassword();
		Assert.assertNotNull(password, "Parâmetro password não informado.");
		System.out.println("Password: " + password);
	}
}
