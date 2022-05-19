package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Register {
	WebDriver driver;

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

	// Incomplete Register
	By byMessagePrivacyPolicy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	public Register(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFirstName(String firstName) {
		WebElement inputFirstName = driver.findElement(byFirstName);
		inputFirstName.clear();
		inputFirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		WebElement inputLastName = driver.findElement(byLastName);
		inputLastName.clear();
		inputLastName.sendKeys(lastName);
	}

	public void enterEmail(String email) {
		WebElement inputEmail = driver.findElement(byEmail);
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void enterPhone(String telephone) {
		WebElement inputPhone = driver.findElement(byPhone);
		inputPhone.clear();
		inputPhone.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		WebElement inputPassword = driver.findElement(byPassword);
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	public void enterPasswordConfirm(String passwordConfirm) {
		WebElement inputPasswordConfirm = driver.findElement(byPassConfirm);
		inputPasswordConfirm.clear();
		inputPasswordConfirm.sendKeys(passwordConfirm);
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
		WebElement inputNewSletter = driver.findElement(bySubscribe);
		inputNewSletter.click();
	}

	public void clickPrivayPolicy() {
		WebElement checkboxPolicy = driver.findElement(byPrivacyPolicy);
		checkboxPolicy.click();
	}

	public void clickContinue() {
		WebElement buttonContinue = driver.findElement(byButtonContinue);
		buttonContinue.click();
	}

	public void clickToLoginPage() {
		WebElement linkLoginPage = driver.findElement(byLoginPage);
		linkLoginPage.click();
	}

	public void validateRegister() {
		WebElement title = driver.findElement(byTitleCreated);
		Assert.assertTrue(title.isDisplayed(), "El titulo es incorrecto");
	}

	public void clickBtnContinueCreate() {
		WebElement btnContinue = driver.findElement(byBtnContinueCreated);
		btnContinue.click();
	}

	public void validateMessagePrivacyPolicy() {
		WebElement alertWarning = driver.findElement(byMessagePrivacyPolicy);
		Assert.assertEquals(alertWarning.getText().equals("Warning: You must agree to the Privacy Policy!"), true,
				"El mensaje de alerta no se desplego correctamente");
	}

}
