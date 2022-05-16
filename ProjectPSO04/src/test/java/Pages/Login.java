package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	WebDriver driver;

	//Login
	By byEmail = By.id("input-email");
	By byPassword = By.id("input-password");
	By byForgottenPassword = By.xpath("//a[contains(text(), 'Forgotten Password')]");
	By byButtonLogin = By.xpath("//input[@type='submit'][@value='Login']");
	
	//ToRegister
	By byRegisterPage = By.xpath("//a[contains(text(), 'Continue')]");

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
	
	
}
