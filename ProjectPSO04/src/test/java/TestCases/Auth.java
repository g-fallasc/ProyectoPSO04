
package TestCases;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Adapter.ReadData;
import Pages.Register;
import Pages.Login;
import Pages.MyAccount;
import Pages.Navigation;

public class Auth {
	Register PagRegister;
	Navigation Navigation;
	Login PagLogin;
	MyAccount PagMyAccount;
	ReadData readData;

	/**
	 * Data for Register & Login/Logout
	 */
	String firstName;
	String lastName;
	String email;
	String phone;
	String password;
	String passwordConfirm;
	String subscribe;

	@Parameters({ "browser", "driverPath", "dataPath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath) {
		// Read data from JSON {dataAuth.json}
		readData = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath);
		// Pages Instances
		PagRegister = new Register(browser, driverPath);
		PagLogin = new Login(browser, driverPath);
		PagMyAccount = new MyAccount(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseOptionRegister() throws InterruptedException {
		Navigation.navToRegister();
		PagRegister.validateTitlePageRegister();
		PagRegister.validateTextPageRegister();
	}

	@Test
	public void TestCaseRegisterSuccess() throws InterruptedException {
		readDataForRegister("registerSuccess");
		Navigation.navToRegister();

		PagRegister.enterFirstName(firstName);
		PagRegister.enterLastName(lastName);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		PagRegister.enterEmail("email" + randomInt + "@mail.com");
		PagRegister.enterPhone(phone);
		PagRegister.enterPassword(password);
		PagRegister.enterPasswordConfirm(passwordConfirm);
		PagRegister.selectSubscribe(subscribe);
		PagRegister.clickPrivayPolicy();
		PagRegister.clickContinue();

		PagRegister.validateRegister();
		PagRegister.clickBtnContinueCreate();
		logout();
	}

	@Test
	public void TestCaseRegisterWithoutPrivacyPolicy() throws InterruptedException {
		readDataForRegister("registerWithoutPrivacyPolicy");
		Navigation.navToRegister();

		PagRegister.enterFirstName(firstName);
		PagRegister.enterLastName(lastName);
		PagRegister.enterEmail(email);
		PagRegister.enterPhone(phone);
		PagRegister.enterPassword(password);
		PagRegister.enterPasswordConfirm(passwordConfirm);

		PagRegister.clickContinue();
		PagRegister.validateMessagePrivacyPolicy();
	}

	@Test
	public void TestCaseRegisterRepeatedEmail() throws InterruptedException {
		readDataForRegister("registerRepeatedEmail");
		Navigation.navToRegister();

		PagRegister.enterFirstName(firstName);
		PagRegister.enterLastName(lastName);
		PagRegister.enterEmail(email);
		PagRegister.enterPhone(phone);
		PagRegister.enterPassword(password);
		PagRegister.enterPasswordConfirm(passwordConfirm);
		PagRegister.selectSubscribe(subscribe);
		PagRegister.clickPrivayPolicy();
		PagRegister.clickContinue();

		PagRegister.validateMessageEmailExist();
	}

	/*
	 * ------------- Login test cases -------------
	 */
	@Test
	public void TestCaseOptionLogin() throws InterruptedException {
		Navigation.navToLogin();
		PagLogin.validateTitlePageLogin();
	}

	@Test
	public void TestCaseLoginSuccess() throws InterruptedException {
		readDataForLogin("loginSuccess");
		Navigation.navToLogin();

		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);

		PagLogin.clickLogin();
		PagMyAccount.verifyLogin();
		logout();
	}

	@Test
	public void TestCaseLoginIncorrect() throws InterruptedException {
		Navigation.navToLogin();

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		PagLogin.enterEmail("email" + randomInt + "@no.existe");
		PagLogin.enterPassword("12345678");

		PagLogin.clickLogin();

		PagLogin.validateMessageErrorLogin();
	}

	@Test
	public void TestCaseLogout() throws InterruptedException {
		readDataForLogin("logout");
		Navigation.navToLogin();

		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();

		PagMyAccount.verifyLogin();
		logout();
	}

	@AfterMethod
	public void afterTest() {
		
	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}
	
	private void logout() {
		Navigation.navToLogout();
		PagLogin.verifyLogout();
		PagLogin.clickBtnContinueLogout();
	}

	private void readDataForRegister(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		firstName = data.get("firstName").toString();
		lastName = data.get("lastName").toString();
		email = data.get("email").toString();
		phone = data.get("phone").toString();
		password = data.get("password").toString();
		passwordConfirm = data.get("passwordConfirm").toString();
		subscribe = data.get("subscribe").toString();
	}

	private void readDataForLogin(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		email = data.get("email").toString();
		password = data.get("password").toString();
	}
}
