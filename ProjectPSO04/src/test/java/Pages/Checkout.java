package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Checkout {
	WebDriver driver;

	// Checkout options

	// Billing details
	By byFirstName = By.id("input-payment-firstname");
	By byLastName = By.id("input-payment-lastname");
	By byCompany = By.id("input-payment-company");
	By byAddress1 = By.id("input-payment-address-1");
	By byAddress2 = By.id("input-payment-address-2");
	By byCity = By.id("input-payment-city");
	By byPostCode = By.id("input-payment-postcode");
	By byCountry = By.id("input-payment-country");
	By byRegionState = By.id("input-payment-zone");

	By byIsExistingAddress = By.xpath("//input[@type='radio'][@value='existing']");
	By byIsNewAddress = By.xpath("//input[@type='radio'][@value='new']");

	// Payment Method
	By byCommentOrder = By.xpath("//textarea[@name='comment']");
	By byCheckTermsConditions = By.xpath("//input[@type='checkbox'][@name='agree']");
	By byMessageTermsConditions = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// Confirm Order
	By byMessagePrivacyPolicy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// Button Continue
	public By byButtonContinueCheckout = By.xpath("//input[@type='button'][@value='Continue']");
	public By byButtonContinueBilling = By.xpath("//input[@type='button'][@value='Continue']");
	public By byButtonContinuePayment = By.id("button-payment-method");

	public Checkout(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isExistingAddress() {
		/*
		 * try { WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * 
		 * wait.until(ExpectedConditions.presenceOfElementLocated(byIsExistingAddress));
		 * 
		 * } catch (NoSuchElementException exception) { System.out.println(exception);
		 * exception.printStackTrace(); }
		 */
		if (driver.getPageSource().contains("I want to use an existing address")) {
			WebElement check = driver.findElement(byIsExistingAddress);
			Assert.assertTrue(check.isDisplayed(), "Ya existe una dirección");
			return check.isDisplayed();
		} else {
			return false;
		}
	}

	public void checkNewAddress() {
		WebElement check = driver.findElement(byIsNewAddress);
		check.click();
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

	public void enterCompany(String company) {
		WebElement inputEmail = driver.findElement(byCompany);
		inputEmail.clear();
		inputEmail.sendKeys(company);
	}

	public void enterAddress1(String address) {
		WebElement inputPhone = driver.findElement(byAddress1);
		inputPhone.clear();
		inputPhone.sendKeys(address);
	}

	public void enterAddress2(String address) {
		WebElement inputPassword = driver.findElement(byAddress2);
		inputPassword.clear();
		inputPassword.sendKeys(address);
	}

	public void enterCity(String city) {
		WebElement inputPasswordConfirm = driver.findElement(byCity);
		inputPasswordConfirm.clear();
		inputPasswordConfirm.sendKeys(city);
	}

	public void enterPostCode(String postCode) {
		WebElement inputPasswordConfirm = driver.findElement(byPostCode);
		inputPasswordConfirm.clear();
		inputPasswordConfirm.sendKeys(postCode);
	}

	public void selectCounty(String country) {
		Select ciudadOrigen = new Select(driver.findElement(byCountry));
		ciudadOrigen.selectByVisibleText(country);
	}

	public void selectRegionState(String state) {
		Select ciudadOrigen = new Select(driver.findElement(byRegionState));
		ciudadOrigen.selectByVisibleText(state);
	}

	public void enterCommentOrder(String comment) {
		WebElement textBox = driver.findElement(byCommentOrder);
		textBox.clear();
		textBox.sendKeys(comment);
	}

	public void checkTermsConditions() {
		WebElement checkboxTerms = driver.findElement(byCheckTermsConditions);
		checkboxTerms.click();
	}

	public void validateMessageTermsConditions() {
		WebElement alertWarning = driver.findElement(byMessageTermsConditions);
		Assert.assertEquals(alertWarning.getText().equals("Warning: You must agree to the Terms & Conditions!"), true,
				"El mensaje de alerta no se desplego correctamente");
	}

	public void clickContinue(By byStep) {
		WebElement buttonContinue = driver.findElement(byStep);
		buttonContinue.click();
	}

}
