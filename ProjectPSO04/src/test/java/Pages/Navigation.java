package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Adapter.AdapterSelenium;
import Utils.CustomScreenRecorder;

public class Navigation {
	/*
	 * TopNavBar
	 */
	// Select My Account
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement dropdownMyAccount;
	By byRegister = By.xpath("//a[contains(text(),'Register')]");
	By byLogin = By.xpath("//a[contains(text(),'Login')]");
	By byOrderHistory = By.xpath("//a[contains(text(),'Order History')]");
	By byTransactions = By.xpath("//a[contains(text(),'Transactions')]");
	By byDownloads = By.xpath("//a[contains(text(),'Downloads')]");
	By byLogout = By.xpath("//a[contains(text(),'Logout')]");
	// Select currency type
	@FindBy(id = "form-currency")
	WebElement dropdownCurrency;
	// Options
	@FindBy(id = "wishlist-total")
	WebElement linkToWishList;
	@FindBy(xpath = "//a[@title='Shopping Cart']")
	WebElement linkToShoppingCart;
	@FindBy(xpath = "//a[@title='Checkout']")
	WebElement linkToCheckout;
	/*
	 * Search
	 */
	@FindBy(xpath = "//input[@type='text'][@name='search']")
	WebElement inputSearchBar;
	@FindBy(xpath = "//button[@type='button'][@class='btn btn-default btn-lg']")
	WebElement buttonSearch;

	String evidencePath;
	String defaultPath;
	private AdapterSelenium adapter;

	public Navigation(String browser, String driverPath, String evidencePath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
		PageFactory.initElements(adapter.getDriver(), this);
		this.evidencePath = evidencePath;
		this.defaultPath = evidencePath;
	}

	/**
	 ** -------- Options Adapter --------**
	 */
	public void openURL(String URL) {
		adapter.openURL(URL);
	}

	public void closeDriver() {
		adapter.closeDriver();
	}

	public void takeScreenshot() {
		adapter.captureScreen(evidencePath);
	}

	public void setEvidencePath(String nameTestCase) {
		this.evidencePath = this.defaultPath + "\\" + nameTestCase;
	}

	private int getSizeHeight() {
		return adapter.getSize().getHeight();
	}

	private int getSizeWidth() {
		return adapter.getSize().getWidth();
	}

	/**
	 * -------- Options Screen Record --------
	 * 
	 * @throws Exception **
	 */
	public void starRecording(String nameFile) {
		try {
			CustomScreenRecorder.startRecording(nameFile, getSizeHeight(), getSizeWidth());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopRecording() {
		try {
			CustomScreenRecorder.stopRecording();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 ** -------- NavBar Top --------**
	 */
	// Open menu 'Currency'
	public void navToCurrency() {
		adapter.clickByWebElement(dropdownCurrency);
	}

	/**
	 * @param enter "EUR" or "GBP" or "USD"
	 */
	public void selectCurrency(String currency) {
		navToCurrency();
		By byCurrency = By.name(currency);
		adapter.clickByLocator(byCurrency);
	}

	// Open menu 'My Account'
	private void navToMyAccount() {
		adapter.clickByWebElement(dropdownMyAccount);
	}

	public void navToRegister() {
		navToMyAccount();
		adapter.clickByLocator(byRegister);
	}

	public void navToLogin() {
		navToMyAccount();
		adapter.clickByLocator(byLogin);
	}

	public void navToOrderHistory() {
		navToMyAccount();
		adapter.clickByLocator(byOrderHistory);
	}

	public void navToTransactions() {
		navToMyAccount();
		adapter.clickByLocator(byTransactions);
	}

	public void navToDownloads() {
		navToMyAccount();
		adapter.clickByLocator(byDownloads);
	}

	public void navToLogout() {
		navToMyAccount();
		adapter.clickByLocator(byLogout);
	}

	public void navToWishList() {
		adapter.clickByWebElement(linkToWishList);
	}

	public void navToShoppingCart() {
		adapter.clickByWebElement(linkToShoppingCart);
	}

	public void navToCheckout() {
		adapter.clickByWebElement(linkToCheckout);
	}

	/**
	 ** -------- Search Bar --------**
	 */
	public void enterValueSearch(String value) {
		adapter.enterTextByWebElement(inputSearchBar, value);
	}

	public void clickButtonSearch() {
		adapter.clickByWebElement(buttonSearch);
	}
}
