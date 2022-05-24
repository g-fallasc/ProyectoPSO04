package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Adapter.AdapterSelenium;

public class Navigation {
	/*
	 * TopNavBar
	 */
	// Select My Account
	By byMyAccount = By.xpath("//a[@title='My Account']");
	By byRegister = By.xpath("//a[contains(text(),'Register')]");
	By byLogin = By.xpath("//a[contains(text(),'Login')]");
	By byOrderHistory = By.xpath("//a[contains(text(),'Order History')]");
	By byTransactions = By.xpath("//a[contains(text(),'Transactions')]");
	By byDownloads = By.xpath("//a[contains(text(),'Downloads')]");
	By byLogout = By.xpath("//a[contains(text(),'Logout')]");

	// Select currency type
	By byBtnCurrency = By.id("form-currency");

	// Options
	By byWishList = By.id("wishlist-total");
	By byShoppingCart = By.xpath("//a[@title='Shopping Cart']");
	By byCheckout = By.xpath("//a[@title='Checkout']");

	// Search bar
	By bySearchBar = By.xpath("//input[@type='text'][@name='search']");
	By byButtonSearch = By.xpath("//button[@type='button'][@class='btn btn-default btn-lg']");

	private AdapterSelenium adapter;

	public Navigation(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void openURL(String URL) {
		adapter.openURL(URL);
	}

	public void closeDriver() {
		adapter.closeDriver();
	}

	// Open menu 'Currency'
	public void navToCurrency() {
		adapter.clickElement(byBtnCurrency);
	}

	/**
	 * @param enter "EUR" or "GBP" or "USD"
	 */
	public void selectCurrency(String currency) {
		navToCurrency();
		By byCurrency = By.name(currency);
		adapter.clickElement(byCurrency);
	}

	// Open menu 'My Account'
	private void navToMyAccount() {
		adapter.clickElement(byMyAccount);
	}

	public void navToRegister() {
		navToMyAccount();
		adapter.clickElement(byRegister);
	}

	public void navToLogin() {
		navToMyAccount();
		adapter.clickElement(byLogin);
	}

	public void navToOrderHistory() {
		navToMyAccount();
		adapter.clickElement(byOrderHistory);
	}

	public void navToTransactions() {
		navToMyAccount();
		adapter.clickElement(byTransactions);
	}

	public void navToDownloads() {
		navToMyAccount();
		adapter.clickElement(byDownloads);
	}

	public void navToLogout() {
		navToMyAccount();
		adapter.clickElement(byLogout);
	}

	public void navToWishList() {
		adapter.clickElement(byWishList);
	}

	public void navToShoppingCart() {
		adapter.clickElement(byShoppingCart);
	}

	public void navToCheckout() {
		adapter.clickElement(byCheckout);
	}

	public void enterValueSearch(String value) {
		adapter.enterText(bySearchBar, value);
	}

	public void clickButtonSearch() {
		adapter.clickElement(byButtonSearch);
	}
}
