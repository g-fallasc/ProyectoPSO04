package Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Checkout {
	// Page without login
	By byTextCartEmpty = By.xpath("//p[contains(text(), 'Your shopping cart is empty!')]");
	By byButtonContinueCartEmpy = By.xpath("//a[contains(text(), 'Continue')]");
	// Checkout options
	By inputEmailLogin = By.id("input-email");
	By inputPasswordLogin = By.id("input-password");
	By buttonLogin = By.id("button-login");
	By optionRegisterAccount = By.xpath("//input[@value='register']");
	By optionGuestCheckout = By.xpath("//input[@value='guest']");
	By byButtonContinueNewCustomer = By.id("button-account");
	// Register details
	By byEmail = By.id("input-payment-email");
	By byTelephone = By.id("placeholder");
	By byPasswordRegister = By.id("input-payment-password");
	By byConfirmPassRegister = By.id("input-payment-confirm");
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
	// Verify existing Address
	By byIsExistingAddress = By.xpath("//input[@type='radio'][@value='existing']");
	By byIsNewAddress = By.xpath("//input[@type='radio'][@value='new']");
	// Payment Method
	By byCommentOrder = By.xpath("//textarea[@name='comment']");
	By byCheckTermsConditions = By.xpath("//input[@type='checkbox'][@name='agree']");
	By byMessageTermsConditions = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	// Confirm Order
	By byMessagePrivacyPolicy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	// Button Continue
	By byButtonContinueCheckout = By.xpath("//input[@type='button'][@value='Continue']");
	By byButtonContinueBilling = By.xpath("//input[@type='button'][@value='Continue']");
	By byButtonContinuePayment = By.id("button-payment-method");

	private AdapterSelenium adapter;

	public Checkout(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public boolean isExistingAddress() {
		try {
			boolean state = adapter.isElementExisting(byIsExistingAddress);
			if (state) {
				Assert.assertTrue(adapter.isElementExisting(byIsExistingAddress), "Ya existe una dirección");
				return state;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("El elemento no se encuentra");
		}
		return false;

	}

	public void checkNewAddress() {
		adapter.clickByLocator(byIsNewAddress);
	}

	public void enterFirstName(String firstName) {
		adapter.enterText(byFirstName, firstName);
	}

	public void enterLastName(String lastName) {
		adapter.enterText(byLastName, lastName);
	}

	public void enterCompany(String company) {
		adapter.enterText(byCompany, company);
	}

	public void enterAddress1(String address) {
		adapter.enterText(byAddress1, address);
	}

	public void enterAddress2(String address) {
		adapter.enterText(byAddress2, address);
	}

	public void enterCity(String city) {
		adapter.enterText(byCity, city);
	}

	public void enterPostCode(String postCode) {
		adapter.enterText(byPostCode, postCode);
	}

	public void selectCounty(String country) {
		adapter.selectElement(byCountry, "text", country);
	}

	public void selectRegionState(String state) {
		adapter.selectElement(byRegionState, "text", state);
	}

	public void enterCommentOrder(String comment) {
		adapter.enterText(byCommentOrder, comment);
	}

	public void checkTermsConditions() {
		adapter.clickByLocator(byCheckTermsConditions);
	}

	public void validateMessageTermsConditions() {
		Assert.assertEquals(
				adapter.getText(byMessageTermsConditions).equals("Warning: You must agree to the Terms & Conditions!"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void validateCartEmpty() {
		Assert.assertTrue(adapter.isElementExisting(byTextCartEmpty), "El carrito si tiene productos!");
	}

	public void clickContinueCartEmpty() {
		adapter.clickByLocator(byButtonContinueCartEmpy);
	}

	public void clickContinueCheckout() {
		adapter.clickByLocator(byButtonContinueCheckout);
	}

	public void clickContinueBilling() {
		adapter.clickByLocator(byButtonContinueBilling);
	}

	public void clickContinuePayment() {
		adapter.clickByLocator(byButtonContinuePayment);
	}

}
