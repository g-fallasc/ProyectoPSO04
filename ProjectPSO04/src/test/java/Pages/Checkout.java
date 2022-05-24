package Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Checkout {
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
	public By byButtonContinueCheckout = By.xpath("//input[@type='button'][@value='Continue']");
	public By byButtonContinueBilling = By.xpath("//input[@type='button'][@value='Continue']");
	public By byButtonContinuePayment = By.id("button-payment-method");

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
		adapter.clickElement(byIsNewAddress);
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
		adapter.clickElement(byCheckTermsConditions);
	}

	public void validateMessageTermsConditions() {
		Assert.assertEquals(
				adapter.getText(byMessageTermsConditions).equals("Warning: You must agree to the Terms & Conditions!"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void clickContinue(By byStep) {
		adapter.clickElement(byStep);
	}

}
