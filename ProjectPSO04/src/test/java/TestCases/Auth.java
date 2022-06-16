
package TestCases;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Register;
import Utils.ReadData;
import Pages.Login;
import Pages.MyAccount;
import Pages.Navigation;

public class Auth {
	Register PagRegister;
	Navigation Navigation;
	Login PagLogin;
	MyAccount PagMyAccount;
	ReadData dataFile;

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

	@Parameters({ "browser", "driverPath", "dataPath", "evidencePath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath, String evidencePath) {
		// Read data from JSON {dataAuth.json}
		dataFile = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath, evidencePath);
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
		Navigation.setEvidencePath("ValidateOptionRegister");
		Navigation.navToRegister();
		PagRegister.validateTitlePageRegister();
		PagRegister.validateTextPageRegister();
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseRegisterSuccess() throws InterruptedException {
		Navigation.setEvidencePath("RegisterSucess");
		readDataForRegister("registerSuccess");
		Navigation.navToRegister();

		Navigation.takeScreenshot();
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
		Navigation.takeScreenshot();
		PagRegister.clickContinue();

		Navigation.takeScreenshot();
		PagRegister.validateRegister();
		PagRegister.clickBtnContinueCreate();
		Navigation.takeScreenshot();
		logout();
	}

	@Test
	public void TestCaseRegisterWithoutPrivacyPolicy() throws InterruptedException {
		Navigation.setEvidencePath("RegisterWithoutPrivacyPolicy");
		readDataForRegister("registerWithoutPrivacyPolicy");
		Navigation.navToRegister();

		Navigation.takeScreenshot();
		PagRegister.enterFirstName(firstName);
		PagRegister.enterLastName(lastName);
		PagRegister.enterEmail(email);
		PagRegister.enterPhone(phone);
		PagRegister.enterPassword(password);
		PagRegister.enterPasswordConfirm(passwordConfirm);
		Navigation.takeScreenshot();

		PagRegister.clickContinue();
		PagRegister.validateMessagePrivacyPolicy();
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseRegisterRepeatedEmail() throws InterruptedException {
		Navigation.setEvidencePath("RegisterRepeatedEmail");
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
		Navigation.takeScreenshot();
		PagRegister.clickContinue();

		PagRegister.validateMessageEmailExist();
		Navigation.takeScreenshot();
	}

	/**
	 * ------------- Login test cases -------------
	 */
	@Test
	public void TestCaseOptionLogin() throws InterruptedException {
		Navigation.setEvidencePath("ValidateOptionLogin");
		Navigation.navToLogin();
		PagLogin.validateTitlePageLogin();
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseLoginSuccess() throws InterruptedException {
		Navigation.setEvidencePath("LoginSucess");
		readDataForLogin("loginSuccess");
		Navigation.navToLogin();

		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		Navigation.takeScreenshot();

		PagLogin.clickLogin();
		PagMyAccount.verifyLogin();
		Navigation.takeScreenshot();
		logout();
	}

	@Test
	public void TestCaseLoginIncorrect() throws InterruptedException {
		Navigation.setEvidencePath("LoginIncorrect");
		Navigation.navToLogin();

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		PagLogin.enterEmail("email" + randomInt + "@no.existe");
		PagLogin.enterPassword("12345678");
		Navigation.takeScreenshot();

		PagLogin.clickLogin();

		PagLogin.validateMessageErrorLogin();
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseLogout() throws InterruptedException {
		readDataForLogin("logout");
		Navigation.setEvidencePath("Logout");
		Navigation.navToLogin();

		Navigation.takeScreenshot();
		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();

		Navigation.takeScreenshot();
		PagMyAccount.verifyLogin();
		logout();
		Navigation.takeScreenshot();
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
		JSONObject data = dataFile.readNode(nameTestCase);
		firstName = data.get("firstName").toString();
		lastName = data.get("lastName").toString();
		email = data.get("email").toString();
		phone = data.get("phone").toString();
		password = data.get("password").toString();
		passwordConfirm = data.get("passwordConfirm").toString();
		subscribe = data.get("subscribe").toString();
	}

	private void readDataForLogin(String nameTestCase) {
		JSONObject data = dataFile.readNode(nameTestCase);
		email = data.get("email").toString();
		password = data.get("password").toString();
	}
}
