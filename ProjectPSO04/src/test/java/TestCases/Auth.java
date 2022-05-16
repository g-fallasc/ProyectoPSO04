package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Pages.Register;
import Pages.Login;
import Pages.Navigation;

public class Auth {

	WebDriver driver;

	Register PagRegister;
	Navigation Navigation;
	Login PagLogin;

	@Test
	public void TestCaseCorrectRegister() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation = new Navigation(driver);

		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Page Register Instance
		PagRegister = new Register(driver);

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
		PagRegister.selectSubscribe("Yes");
		Thread.sleep(1500);
		PagRegister.clickPrivayPolicy();
		Thread.sleep(2000);
		PagRegister.clickContinue();

		driver.quit();

	}

	@Test
	public void TestCaseValidateRegister() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation = new Navigation(driver);

		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Page Register Instance
		PagRegister = new Register(driver);

		// Enter personal details
		//TODO validar componentes de registro

		driver.quit();

	}

	// @Test
	public void TestCaseIncorrectRegister() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation = new Navigation(driver);

		// Navigation to register page
		Navigation.navToRegister();
		Thread.sleep(1000);

		// Page Register Instance
		PagRegister = new Register(driver);

		// Enter personal details
		PagRegister.enterFirstName("Prueba");
		PagRegister.enterLastName("Test Case");
		PagRegister.enterEmail("mail@mail@.com@.es");
		PagRegister.enterPhone("asbsf13456");

		PagRegister.clickContinue();

		driver.quit();

	}

	// @Test
	public void TestCaseCorrectLogin() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation = new Navigation(driver);

		// Navigation to register page
		Navigation.navToLogin();
		Thread.sleep(1000);

		// Page Login Instance
		PagLogin = new Login(driver);
		
		driver.quit();

	}

	// @Test
	public void TestCaseIncorrectLogin() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		Navigation = new Navigation(driver);

		// Navigation to register page
		Navigation.navToLogin();
		Thread.sleep(1000);

		// Page Login Instance

		driver.quit();

	}

}
