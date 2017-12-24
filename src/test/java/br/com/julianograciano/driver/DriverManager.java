package br.com.julianograciano.driver;

/**
 * Armazena uma única instância do WebDrive por thread.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
public final class DriverManager {
	private static ThreadLocal<DriverProvider> driver = new ThreadLocal<DriverProvider>();

	public final static DriverProvider getDriverProvider() {
		return driver.get();
	}

	public final static void setDriverProvider(DriverProvider driverProvider) {
		driver.set(driverProvider);
	}
}
