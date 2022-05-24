package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Login {
	// Login
	By byEmail = By.id("input-email");
	By byPassword = By.id("input-password");
	By byForgottenPassword = By.xpath("//a[contains(text(), 'Forgotten Password')]");
	By byButtonLogin = By.xpath("//input[@type='submit'][@value='Login']");
	// Title Page Login
	By byTitlePageLogin = By.xpath("//h2[contains(text(), 'Returning Customer')]");

	// ToRegister
	By byRegisterPage = By.xpath("//a[contains(text(), 'Continue')]");

	// Error Login
	By byMessageErrorLogin = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// Logout
	By byTitleLogout = By.xpath("//h1[contains(text(), 'Account Logout')]");
	By byMessageLogout = By.xpath(
			"//p[contains(text(), 'You have been logged off your account. It is now safe to leave the computer.')]");
	By byBtnContinueLogout = By.xpath("//a[contains(text(), 'Continue')]");

	private AdapterSelenium adapter;

	public Login(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void enterEmail(String email) {
		adapter.enterText(byEmail, email);
	}

	public void enterPassword(String password) {
		adapter.enterText(byPassword, password);
	}

	public void clickForgottenPassword() {
		adapter.clickElement(byForgottenPassword);
	}

	public void clickLogin() {
		adapter.clickElement(byButtonLogin);
	}

	public void clickToRegisterPage() {
		adapter.clickElement(byRegisterPage);
	}

	public void validateMessageErrorLogin() {
		Assert.assertEquals(
				adapter.getText(byMessageErrorLogin).equals("Warning: No match for E-Mail Address and/or Password."),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyLogout() {
		Assert.assertTrue(adapter.isElementExisting(byTitleLogout), "No se cerro sesión correctamente");
		Assert.assertTrue(adapter.isElementExisting(byMessageLogout), "El mensaje no es el correcto");
	}

	public void clickBtnContinueLogout() {
		adapter.clickElement(byBtnContinueLogout);
	}

	public void validateTitlePageLogin() {
		Assert.assertTrue(adapter.isElementExisting(byTitlePageLogin), "El titulo es incorrecto");
	}

}
