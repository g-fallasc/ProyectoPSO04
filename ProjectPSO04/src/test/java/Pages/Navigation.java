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
	By byOrderHistory = By.xpath("//a[contains(text(),'Login')]");
	By byTransactions = By.xpath("//a[contains(text(),'Login')]");
	By byDownloads = By.xpath("//a[contains(text(),'Login')]");
	By byLogout = By.xpath("//a[contains(text(),'Login')]");

	// Options
	By byWishList = By.id("wishlist-total");
	By byShoppingCart = By.xpath("//a[@title='Shopping Cart']");
	By byCheckout = By.xpath("//a[@title='Checkout']");

	public Navigation(WebDriver driver, String URL) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.manage().deleteAllCookies();
		this.driver.get(URL);
	}

	public void navToRegister() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
		WebElement toRegister = driver.findElement(byRegister);
		toRegister.click();
	}

	public void navToLogin() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
		WebElement toLogin = driver.findElement(byLogin);
		toLogin.click();
	}

	public void navToOrderHistory() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
		WebElement toOrderHistory = driver.findElement(byOrderHistory);
		toOrderHistory.click();
	}

	public void navToTransactions() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
		WebElement toTransactions = driver.findElement(byTransactions);
		toTransactions.click();
	}

	public void navToDownloads() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
		WebElement toDownloads = driver.findElement(byDownloads);
		toDownloads.click();
	}

	public void navToLogout() {
		WebElement dropdownMyAccount = driver.findElement(byMyAccount);
		dropdownMyAccount.click();
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

}
