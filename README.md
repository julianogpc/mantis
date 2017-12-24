# mantis
Automation Tests on Mantis Bug Tracker versão 1.2.19.

### Build With
* [Eclipse IDE](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/neon3/) 
* [Maven](https://maven.apache.org/download.cgi/) - Dependency Management
* [TestNG](http://testng.org/doc/) - Testing Framework Inspired from JUnit and NUnit
* [Selenium WebDriver](http://www.seleniumhq.org/) - Web Browser Automation
* [Selenium Shutterbug](https://github.com/assertthat/selenium-shutterbug/) - Make Screenshots Using Selenium WebDriver
* [Apache POI](https://poi.apache.org/overview.html) - Java API To Access Microsoft Format Files


### Running the tests
```
mvn clean test -Dwebdriver.gecko.driver="driver/geckodriver.exe" -Dwebdriver.chrome.driver="driver/chromedriver.exe" -DtimeoutInSeconds=300 -DurlBase=<url> -Dusername=<username> -Dpassword=<password> -DsuiteFile=xml\<xml_testng>
```
