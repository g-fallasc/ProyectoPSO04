package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Register {
	WebDriver driver;

	//Personal details
	By byFirstName = By.id("input-firstname");
	By byLastName = By.id("input-lastname");
	By byEmail = By.id("input-email");
	By byPhone = By.id("input-telephone");
	
	//Password
	By byPassword = By.id("input-password");
	By byPassConfirm = By.id("input-confirm");
		
	//Register
	By byPrivacyPolicy = By.xpath("//input[@type='checkbox'][@name='agree']");
	By byButtonContinue = By.xpath("//input[@type='submit'][@value='Continue']");
	
	//ToLogin
	By byLoginPage = By.xpath("//a[contains(text(),'login page')]");

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
	
	
}
