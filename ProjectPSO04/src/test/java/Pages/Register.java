package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Register {
	// Personal details
	By byFirstName = By.id("input-firstname");
	By byLastName = By.id("input-lastname");
	By byEmail = By.id("input-email");
	By byPhone = By.id("input-telephone");

	// Password
	By byPassword = By.id("input-password");
	By byPassConfirm = By.id("input-confirm");

	// Register
	By byPrivacyPolicy = By.xpath("//input[@type='checkbox'][@name='agree']");
	By byButtonContinue = By.xpath("//input[@type='submit'][@value='Continue']");

	// ToLogin
	By byLoginPage = By.xpath("//a[contains(text(),'login page')]");

	// Register Completed
	By byTitleCreated = By.xpath("//h1[contains(text(), 'Your Account Has Been Created!')]");
	By byBtnContinueCreated = By.xpath("//a[contains(text(),'Continue')]");

	// Message Register Error
	By byMessagePrivacyPolicy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	By byMessageEmailExist = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// Title and Text Page
	By byTitlePageRegister = By.xpath("//h1[contains(text(), 'Register Account')]");
	By byTextAlreadyAccount = By.xpath("//div[@id='content']//following-sibling::p");

	private AdapterSelenium adapter;

	public Register(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void enterFirstName(String firstName) {
		adapter.enterText(byFirstName, firstName);
	}

	public void enterLastName(String lastName) {
		adapter.enterText(byLastName, lastName);
	}

	public void enterEmail(String email) {
		adapter.enterText(byEmail, email);
	}

	public void enterPhone(String telephone) {
		adapter.enterText(byPhone, telephone);
	}

	public void enterPassword(String password) {
		adapter.enterText(byPassword, password);
	}

	public void enterPasswordConfirm(String passwordConfirm) {
		adapter.enterText(byPassConfirm, passwordConfirm);
	}

	/**
	 * @param select enter "Yes" or "No"
	 */
	public void selectSubscribe(String select) {
		String value = "0";
		if (select.equals("Yes")) {
			value = "1";
		}
		By bySubscribe = By.xpath("//input[@name='newsletter'][@value='" + value + "']");
		adapter.clickElement(bySubscribe);
	}

	public void clickPrivayPolicy() {
		adapter.clickElement(byPrivacyPolicy);
	}

	public void clickContinue() {
		adapter.clickElement(byButtonContinue);
	}

	public void clickToLoginPage() {
		adapter.clickElement(byLoginPage);
	}

	public void validateRegister() {
		Assert.assertTrue(adapter.isElementExisting(byTitleCreated), "Elemento no existe");
	}

	public void clickBtnContinueCreate() {
		adapter.clickElement(byBtnContinueCreated);
	}

	public void validateMessagePrivacyPolicy() {
		Assert.assertEquals(
				adapter.getText(byMessagePrivacyPolicy).equals("Warning: You must agree to the Privacy Policy!"), true,
				"El mensaje de alerta no se desplego correctamente");
	}

	public void validateMessageEmailExist() {
		Assert.assertEquals(
				adapter.getText(byMessageEmailExist).equals("Warning: E-Mail Address is already registered!"), true,
				"El mensaje de alerta no se desplego correctamente");
	}

	public void validateTitlePageRegister() {
		Assert.assertTrue(adapter.isElementExisting(byTitlePageRegister), "El titulo es incorrecto");
	}

	public void validateTextPageRegister() {
		Assert.assertEquals(
				adapter.getText(byTextAlreadyAccount)
						.equals("If you already have an account with us, please login at the login page."),
				true, "El texto no es el correcto");
	}
}
