package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Login {
	// Login
	@FindBy(id = "input-email")
	WebElement inputEmail;
	@FindBy(id = "input-password")
	WebElement inputPassword;
	@FindBy(xpath = "//a[contains(text(), 'Forgotten Password')]")
	WebElement linkToForgottenPassword;
	@FindBy(xpath = "//input[@type='submit'][@value='Login']")
	WebElement buttonLogin;
	// Title Page Login
	@FindBy(xpath = "//h2[contains(text(), 'Returning Customer')]")
	WebElement textTitlePageLogin;
	// ToRegister
	@FindBy(xpath = "//a[contains(text(), 'Continue')]")
	WebElement buttonToRegisterPage;
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
		PageFactory.initElements(adapter.getDriver(), this);
	}

	public void enterEmail(String email) {
		adapter.enterTextByWebElement(inputEmail, email);
	}

	public void enterPassword(String password) {
		adapter.enterTextByWebElement(inputPassword, password);
	}

	public void clickForgottenPassword() {
		adapter.clickByWebElement(linkToForgottenPassword);
	}

	public void clickLogin() {
		adapter.clickByWebElement(buttonLogin);
	}

	public void clickToRegisterPage() {
		adapter.clickByWebElement(buttonToRegisterPage);
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
		adapter.clickByLocator(byBtnContinueLogout);
	}

	public void validateTitlePageLogin() {
		Assert.assertTrue(adapter.isElementExistingByWebElement(textTitlePageLogin), "El titulo es incorrecto");
	}

}
