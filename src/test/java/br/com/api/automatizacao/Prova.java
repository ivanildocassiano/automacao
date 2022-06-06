package br.com.api.automatizacao;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Prova {

	private WebDriver driver;

	@BeforeMethod
	public void init() {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
	}

	@AfterMethod
	public void termino() {
		driver.quit();
	}

	@Test
	public void testConsultaRestricao() throws InterruptedException {
		driver.get("http://localhost:8080/swagger-ui.html");
		Thread.sleep(1000l);
		driver.findElement(ByCssSelector.cssSelector("#operations-tag-Restrições > a > span")).click();
		driver.findElement(ByCssSelector
				.cssSelector("#operations-Restrições-oneUsingGET > div > span.opblock-summary-path > a > span"))
				.click();
		driver.findElement(ByCssSelector.cssSelector(
				"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.opblock-section > div.opblock-section-header > div.try-out > button"))
				.click();
		List<String> cpfResticao = Arrays.asList("97093236014", "60094146012", "84809766080", "62648716050",
				"26276298085", "01317496094", "55856777050", "19626829001", "24094592008", "58063164083");

		for (String cpf : cpfResticao) {
			driver.findElement(ByCssSelector.cssSelector(
					"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.opblock-section > div.table-container > table > tbody > tr > td.col.parameters-col_description > input[type=text]"))
					.sendKeys(cpf);
			driver.findElement(ByCssSelector.cssSelector(
					"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.execute-wrapper > button"))
					.click();
			String codigoRetorno = driver.findElement(ByCssSelector.cssSelector(
					"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.responses-wrapper > div.responses-inner > div > div > table > tbody > tr > td.col.response-col_status"))
					.getText();
			Assert.assertEquals(codigoRetorno, "200");
			driver.findElement(ByCssSelector.cssSelector(
					"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.btn-group > button.btn.btn-clear.opblock-control__btn"))
					.click();
		}

	}

	@Test
	public void testSemConsultaRestricao() throws InterruptedException {
		driver.get("http://localhost:8080/swagger-ui.html");
		Thread.sleep(1000l);
		driver.findElement(ByCssSelector.cssSelector("#operations-tag-Restrições > a > span")).click();
		driver.findElement(ByCssSelector
				.cssSelector("#operations-Restrições-oneUsingGET > div > span.opblock-summary-path > a > span"))
				.click();
		driver.findElement(ByCssSelector.cssSelector(
				"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.opblock-section > div.opblock-section-header > div.try-out > button"))
				.click();
		String cpfSemRestricao = "72793079065";
		driver.findElement(ByCssSelector.cssSelector(
				"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.opblock-section > div.table-container > table > tbody > tr > td.col.parameters-col_description > input[type=text]"))
				.sendKeys(cpfSemRestricao);
		driver.findElement(ByCssSelector.cssSelector(
				"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.execute-wrapper > button")).click();
		String codigoRetorno = driver.findElement(ByCssSelector.cssSelector(
				"#operations-Restrições-oneUsingGET > div:nth-child(2) > div > div.responses-wrapper > div.responses-inner > div > div > table > tbody > tr > td.col.response-col_status"))
				.getText();
		Assert.assertEquals(codigoRetorno, "201");
	}

	@Test
	public void testSimulacao() throws InterruptedException {

	}

}
