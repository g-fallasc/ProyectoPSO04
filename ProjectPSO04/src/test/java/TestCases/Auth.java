package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Register;
import Pages.Login;
import Pages.MyAccount;
import Pages.Navigation;

public class Auth {

	WebDriver driver;

	Register PagRegister;
	Navigation Navigation;
	Login PagLogin;
	MyAccount PagMyAccount;

	@BeforeClass
	public void beforeClass() {

	}

	@Parameters({ "browser", "driverPath", "URL" })
	@BeforeMethod
	public void beforeTest(String browser, String driverPath, String URL) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.geckod.driver", driverPath);
			// driver = new FirefoxDriver();
		}
		// Set Navigation class
		Navigation = new Navigation(driver, URL);

		// Pages Instances
		PagRegister = new Register(driver);
		PagLogin = new Login(driver);
		PagMyAccount = new MyAccount(driver);
	}

	@Test
	public void TestCaseCorrectRegister() throws InterruptedException {
		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Enter personal details
		PagRegister.enterFirstName("Prueba");
		PagRegister.enterLastName("Test Case");
		PagRegister.enterEmail("email2@mail.com");
		PagRegister.enterPhone("+50688997766");
		Thread.sleep(1500);

		// Enter password
		PagRegister.enterPassword("12345678");
		PagRegister.enterPasswordConfirm("12345678");
		Thread.sleep(1000);

		// Enter checks
		PagRegister.selectSubscribe("Yes");
		Thread.sleep(1500);
		PagRegister.clickPrivayPolicy();
		Thread.sleep(2000);
		PagRegister.clickContinue();
		Thread.sleep(1000);

		// Validate complete register
		PagRegister.validateRegister();
		Thread.sleep(1000);
		PagRegister.clickBtnContinueCreate();
	}

	@Test
	public void TestCaseIncompleteRegister() throws InterruptedException {
		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Enter personal details
		PagRegister.enterFirstName("Prueba");
		PagRegister.enterLastName("Test Case");
		PagRegister.enterEmail("email@mail.com");
		PagRegister.enterPhone("+50688997766");
		Thread.sleep(1500);

		PagRegister.clickContinue();
		Thread.sleep(1000);

		PagRegister.validateMessagePrivacyPolicy();
		Thread.sleep(1000);
	}

	// @Test
	public void TestCaseRepeatedRegister() throws InterruptedException {
		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Enter personal details
		PagRegister.enterFirstName("Prueba");
		PagRegister.enterLastName("Test Case");
		PagRegister.enterEmail("email@mail.com");
		PagRegister.enterPhone("+50688997766");
		Thread.sleep(1500);

		// Enter password
		PagRegister.enterPassword("12345678");
		PagRegister.enterPasswordConfirm("12345678");
		Thread.sleep(1000);

		// Enter checks
		PagRegister.selectSubscribe("No");
		Thread.sleep(1500);
		PagRegister.clickPrivayPolicy();
		Thread.sleep(2000);
		PagRegister.clickContinue();
		Thread.sleep(1000);
	}

	// @Test
	public void TestCaseCorrectLogin() throws InterruptedException {
		// Navigation to register page
		Navigation.navToLogin();
		Thread.sleep(1000);

		// Enter credentials
		PagLogin.enterEmail("gfallasc@ucenfotec.ac.cr");
		PagLogin.enterPassword("12345678");
		Thread.sleep(1000);

		// Button Login
		PagLogin.clickLogin();
		Thread.sleep(1000);

		// Verify My Account Page
		PagMyAccount.verifyLogin();
		Thread.sleep(1000);
	}

	// @Test
	public void TestCaseIncorrectLogin() throws InterruptedException {
		// Navigation to register page
		Navigation.navToLogin();
		Thread.sleep(1000);

		// Enter credentials
		PagLogin.enterEmail("email@no.existe");
		PagLogin.enterPassword("12345678");
		Thread.sleep(1000);

		// Button Login
		PagLogin.clickLogin();
		Thread.sleep(1000);

		// Verify message error
		PagLogin.validateMessageErrorLogin();
		Thread.sleep(1000);
	}

	@Test
	public void TestCaseLogout() throws InterruptedException {
		// Navigation to register page
		Navigation.navToLogin();

		// Enter credentials
		PagLogin.enterEmail("gfallasc@ucenfotec.ac.cr");
		PagLogin.enterPassword("12345678");

		// Button Login
		PagLogin.clickLogin();

		// Verify My Account Page
		PagMyAccount.verifyLogin();
		Thread.sleep(1500);

		// Logout
		Navigation.navToLogout();
		Thread.sleep(1000);

		// Verify message logout
		PagLogin.verifyLogout();
		Thread.sleep(1000);
		PagLogin.clickBtnContinueLogout();
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {

	}

}
