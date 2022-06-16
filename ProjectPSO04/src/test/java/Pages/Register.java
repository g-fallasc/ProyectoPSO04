package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Register {
	// Personal details
	@FindBy(id = "input-firstname")
	WebElement inputFirstName;
	@FindBy(id = "input-lastname")
	WebElement inputLastName;
	@FindBy(id = "input-email")
	WebElement inputEmail;
	@FindBy(id = "input-telephone")
	WebElement inputPhone;
	// Password
	@FindBy(id = "input-password")
	WebElement inputPassword;
	@FindBy(id = "input-confirm")
	WebElement inputConfirmPass;
	// Register
	@FindBy(xpath = "//input[@type='checkbox'][@name='agree']")
	WebElement checkBoxPrivacyPolicy;
	@FindBy(xpath = "//input[@type='submit'][@value='Continue']")
	WebElement buttonContinue;
	// ToLogin
	@FindBy(xpath = "//a[contains(text(),'login page')]")
	WebElement linkToLoginPage;
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
		PageFactory.initElements(adapter.getDriver(), this);
	}

	public void enterFirstName(String firstName) {
		adapter.enterTextByWebElement(inputFirstName, firstName);
	}

	public void enterLastName(String lastName) {
		adapter.enterTextByWebElement(inputLastName, lastName);
	}

	public void enterEmail(String email) {
		adapter.enterTextByWebElement(inputEmail, email);
	}

	public void enterPhone(String telephone) {
		adapter.enterTextByWebElement(inputPhone, telephone);
	}

	public void enterPassword(String password) {
		adapter.enterTextByWebElement(inputPassword, password);
	}

	public void enterPasswordConfirm(String passwordConfirm) {
		adapter.enterTextByWebElement(inputConfirmPass, passwordConfirm);
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
		adapter.clickByLocator(bySubscribe);
	}

	public void clickPrivayPolicy() {
		adapter.clickByWebElement(checkBoxPrivacyPolicy);
	}

	public void clickContinue() {
		adapter.clickByWebElement(buttonContinue);
	}

	public void clickToLoginPage() {
		adapter.clickByWebElement(linkToLoginPage);
	}

	public void validateRegister() {
		Assert.assertTrue(adapter.isElementExisting(byTitleCreated), "Elemento no existe");
	}

	public void clickBtnContinueCreate() {
		adapter.clickByLocator(byBtnContinueCreated);
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
