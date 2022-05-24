package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Adapter.ReadData;
import Pages.Checkout;
import Pages.Login;
import Pages.Navigation;

public class CheckoutOrder {
	Navigation Navigation;
	Checkout PagCheckout;
	Login PagLogin;
	ReadData readData;

	/**
	 * Data for Checkout order
	 */
	String firstName;
	String lastName;
	String company;
	String address1;
	String address2;
	String city;
	String postCode;
	String country;
	String region;
	String comment;
	/**
	 * Data for Login user
	 */
	String email;
	String password;

	@Parameters({ "browser", "driverPath", "dataPath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath) {
		// Read data from JSON {dataCheckout.json}
		readData = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath);

		// Pages Instances
		PagCheckout = new Checkout(browser, driverPath);
		PagLogin = new Login(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseCheckoutOrder() throws InterruptedException {
		readDataTestCase("checkoutOrder");
		// Login Account
		LoginUser(email, password);
		// Navigation to register page
		Navigation.navToCheckout();

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
		} else {
			// Enter personal details
			PagCheckout.enterFirstName(firstName);
			PagCheckout.enterLastName(lastName);
			PagCheckout.enterCompany(company);
			PagCheckout.enterAddress1(address1);
			PagCheckout.enterCity(city);
			PagCheckout.enterPostCode(postCode);
			PagCheckout.selectCounty(country);
			PagCheckout.selectRegionState(region);
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
		}
		// Payment Method
		PagCheckout.enterCommentOrder(comment);
		PagCheckout.checkTermsConditions();
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		logout();
	}

	@Test
	public void TestCaseCheckoutOrderAddNewAddress() throws InterruptedException {
		readDataTestCase("checkoutOrderAddNewAddress");
		// Login Account
		LoginUser(email, password);
		// Navigation to register page
		Navigation.navToCheckout();

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.checkNewAddress();
		}
		// Enter personal details
		PagCheckout.enterFirstName(firstName);
		PagCheckout.enterLastName(lastName);
		PagCheckout.enterCompany(company);
		PagCheckout.enterAddress1(address1);
		PagCheckout.enterAddress2(address2);
		PagCheckout.enterCity(city);
		PagCheckout.enterPostCode(postCode);
		PagCheckout.selectCounty(country);
		PagCheckout.selectRegionState(region);
		PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
		// Payment Method
		PagCheckout.enterCommentOrder(comment);
		PagCheckout.checkTermsConditions();
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		logout();
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}

	public void LoginUser(String email, String password) throws InterruptedException {
		// Login user validate
		Navigation.navToLogin();
		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();
	}

	private void logout() {
		Navigation.navToLogout();
		PagLogin.verifyLogout();
		PagLogin.clickBtnContinueLogout();
	}

	private void readDataTestCase(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		firstName = data.get("firstName").toString();
		lastName = data.get("lastName").toString();
		company = data.get("company").toString();
		address1 = data.get("address1").toString();
		address2 = data.get("address2").toString();
		city = data.get("city").toString();
		postCode = data.get("postCode").toString();
		country = data.get("country").toString();
		region = data.get("region").toString();
		comment = data.get("comment").toString();
		email = data.get("email").toString();
		password = data.get("password").toString();
	}

}
