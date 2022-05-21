package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Checkout;
import Pages.Login;
import Pages.Navigation;

public class CheckoutOrder {

	WebDriver driver;

	Navigation Navigation;
	Checkout PagCheckout;
	Login PagLogin;

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
		// Instance Navigation class
		Navigation = new Navigation(driver, URL);

		// Pages Instances
		PagCheckout = new Checkout(driver);
		PagLogin = new Login(driver);
	}

	@Test
	public void TestCaseCheckoutOrder() throws InterruptedException {
		// Login Account
		LoginUser("gfallasc@ucenfotec.ac.cr", "12345678");

		// Navigation to register page
		Navigation.navToCheckout();
		Thread.sleep(1000);

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
			Thread.sleep(2000);
		} else {
			// Enter personal details
			PagCheckout.enterFirstName("Prueba");
			Thread.sleep(500);
			PagCheckout.enterLastName("Test Case");
			Thread.sleep(500);
			PagCheckout.enterCompany("Cenfotec");
			Thread.sleep(500);
			PagCheckout.enterAddress1("Calle 1");
			PagCheckout.enterCity("San Jose");
			Thread.sleep(500);
			PagCheckout.enterPostCode("12345");
			Thread.sleep(500);
			PagCheckout.selectCounty("Costa Rica");
			Thread.sleep(500);
			PagCheckout.selectRegionState("San Jose");
			Thread.sleep(1000);
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
			Thread.sleep(1000);
		}
		// Payment Method
		PagCheckout.enterCommentOrder("Orden de prueba");
		PagCheckout.checkTermsConditions();
		Thread.sleep(2000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		Thread.sleep(2500);
	}

	@Test
	public void TestCaseCheckoutOrderAddNewAddress() throws InterruptedException {
		// Login Account
		LoginUser("gus@email.com", "1234A");

		// Navigation to register page
		Navigation.navToCheckout();
		Thread.sleep(1000);

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.checkNewAddress();
			Thread.sleep(1000);
		}
		// Enter personal details
		PagCheckout.enterFirstName("Prueba");
		Thread.sleep(500);
		PagCheckout.enterLastName("Test Case");
		Thread.sleep(500);
		PagCheckout.enterCompany("Cenfotec");
		Thread.sleep(500);
		PagCheckout.enterAddress1("Calle 1");
		PagCheckout.enterCity("San Jose");
		Thread.sleep(500);
		PagCheckout.enterPostCode("12345");
		Thread.sleep(500);
		PagCheckout.selectCounty("Costa Rica");
		Thread.sleep(500);
		PagCheckout.selectRegionState("San Jose");
		Thread.sleep(1000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
		Thread.sleep(1000);
		// Payment Method
		PagCheckout.enterCommentOrder("Orden de prueba");
		PagCheckout.checkTermsConditions();
		Thread.sleep(1000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		Thread.sleep(3500);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {

	}

	public void LoginUser(String email, String password) throws InterruptedException {
		// Login user validate
		Navigation.navToLogin();
		Thread.sleep(1000);
		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();
		Thread.sleep(1000);
	}

}
