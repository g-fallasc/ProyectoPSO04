package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {
	WebDriver driver;

	// Login
	By byEmail = By.id("input-email");
	By byPassword = By.id("input-password");
	By byForgottenPassword = By.xpath("//a[contains(text(), 'Forgotten Password')]");
	By byButtonLogin = By.xpath("//input[@type='submit'][@value='Login']");

	// ToRegister
	By byRegisterPage = By.xpath("//a[contains(text(), 'Continue')]");

	// Error Login
	By byMessageErrorLogin = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// Logout
	By byTitleLogout = By.xpath("//h1[contains(text(), 'Account Logout')]");
	By byMessageLogout = By.xpath(
			"//p[contains(text(), 'You have been logged off your account. It is now safe to leave the computer.')]");
	By byBtnContinueLogout = By.xpath("//a[contains(text(), 'Continue')]");

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void enterEmail(String email) {
		WebElement inputEmail = driver.findElement(byEmail);
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement inputPassword = driver.findElement(byPassword);
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	public void clickForgottenPassword() {
		WebElement buttonForgotten = driver.findElement(byForgottenPassword);
		buttonForgotten.click();
	}

	public void clickLogin() {
		WebElement buttonLogin = driver.findElement(byButtonLogin);
		buttonLogin.click();
	}

	public void clickToRegisterPage() {
		WebElement linkRegisterPage = driver.findElement(byRegisterPage);
		linkRegisterPage.click();
	}

	public void validateMessageErrorLogin() {
		WebElement alertWarning = driver.findElement(byMessageErrorLogin);
		Assert.assertEquals(alertWarning.getText().equals("Warning: No match for E-Mail Address and/or Password."),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyLogout() {
		WebElement titleLogout = driver.findElement(byTitleLogout);
		WebElement messageLogout = driver.findElement(byMessageLogout);
		Assert.assertTrue(titleLogout.isDisplayed(), "No se cerro sesión correctamente");
		Assert.assertTrue(messageLogout.isDisplayed(), "El mensaje no es el correcto");
	}
	
	public void clickBtnContinueLogout() {
		WebElement btnContinue = driver.findElement(byBtnContinueLogout);
		btnContinue.click();
	}

}
