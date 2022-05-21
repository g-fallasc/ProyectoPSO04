package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Navigation {
	WebDriver driver;

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

	public Navigation(WebDriver driver, String URL) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.manage().deleteAllCookies();
		this.driver.get(URL);
	}

	// Open menu 'Currency'
	public void navToCurrency() {
		WebElement currency = driver.findElement(byBtnCurrency);
		currency.click();
	}

	/**
	 * @param enter "EUR" or "GBP" or "USD"
	 */
	public void selectCurrency(String currency) {
		navToCurrency();
		By byBtnCurrency = By.name(currency);
		WebElement selectType = driver.findElement(byBtnCurrency);
		selectType.click();
	}

	// Open menu 'My Account'
	private void navToMyAccount() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
	}

	public void navToRegister() {
		navToMyAccount();
		WebElement toRegister = driver.findElement(byRegister);
		toRegister.click();
	}

	public void navToLogin() {
		navToMyAccount();
		WebElement toLogin = driver.findElement(byLogin);
		toLogin.click();
	}

	public void navToOrderHistory() {
		navToMyAccount();
		WebElement toOrderHistory = driver.findElement(byOrderHistory);
		toOrderHistory.click();
	}

	public void navToTransactions() {
		navToMyAccount();
		WebElement toTransactions = driver.findElement(byTransactions);
		toTransactions.click();
	}

	public void navToDownloads() {
		navToMyAccount();
		WebElement toDownloads = driver.findElement(byDownloads);
		toDownloads.click();
	}

	public void navToLogout() {
		navToMyAccount();
		WebElement toLogout = driver.findElement(byLogout);
		toLogout.click();
	}

	public void navToWishList() {
		WebElement toWishList = driver.findElement(byWishList);
		toWishList.click();
	}

	public void navToShoppingCart() {
		WebElement toShoppingCart = driver.findElement(byShoppingCart);
		toShoppingCart.click();
	}

	public void navToCheckout() {
		WebElement toCheckout = driver.findElement(byCheckout);
		toCheckout.click();
	}

	public void enterValueSearch(String value) {
		WebElement input = driver.findElement(bySearchBar);
		input.clear();
		input.sendKeys(value);
	}

	public void clickButtonSearch() {
		WebElement button = driver.findElement(byButtonSearch);
		button.click();
	}

}
