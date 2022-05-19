package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MyAccount {
	WebDriver driver;

	// My Account
	public By byEditAccountInfo = By.xpath("//a[contains(text(), 'Edit your account information')]");
	public By byChangePass = By.xpath("//a[contains(text(), 'Change your password')]");
	public By byEditAdressBook = By.xpath("//a[contains(text(), 'Modify your address book entries')]");
	public By byEditWishList = By.xpath("//a[contains(text(), 'Modify your wish list')]");
	By byTitleMyAccount = By.xpath("//h2[contains(text(), 'My Account')]");

	// My Orders
	public By byViewOrderHistory = By.xpath("//a[contains(text(), 'View your order history')]");
	public By byDownloads = By.xpath("//a[contains(text(), 'Downloads')]");
	public By byRewardPoints = By.xpath("//a[contains(text(), 'Your Reward Points')]");
	public By byRequests = By.xpath("//a[contains(text(), 'View your return requests')]");
	public By byTransactions = By.xpath("//a[contains(text(), 'Your Transactions')]");
	public By byPayments = By.xpath("//a[contains(text(), 'Recurring payments')]");
	By byTitleMyOrders = By.xpath("//h2[contains(text(), 'My Orders')]");

	// My Affiliate Account
	public By byAffiliateAccount = By.xpath("//a[contains(text(), 'Register for an affiliate account')]");
	By byTitleMyAffiliate = By.xpath("//h2[contains(text(), 'My Affiliate Account')]");

	// Newsletter
	public By byEditNewsletter = By.xpath("//a[contains(text(), 'Subscribe / unsubscribe to newsletter')]");
	By byTitleNewsletter = By.xpath("//h2[contains(text(), 'Newsletter')]");

	public MyAccount(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param byToLink name link to open
	 */
	public void clickToLink(By byToLink) {
		WebElement link = driver.findElement(byToLink);
		link.click();
	}

	/**
	 * public void clickToEditAccountInfo() { WebElement link =
	 * driver.findElement(byEditAccountInfo); link.click(); }
	 * 
	 * public void clickToChangePassword() { WebElement link =
	 * driver.findElement(byChangePass); link.click(); }
	 * 
	 * public void clickToEditAdressBook() { WebElement link =
	 * driver.findElement(byEditAdressBook); link.click(); }
	 * 
	 * public void clickToEditWhisList() { WebElement link =
	 * driver.findElement(byEditWishList); link.click(); }
	 * 
	 * public void clickToOrderHistory() { WebElement link =
	 * driver.findElement(byViewOrderHistory); link.click(); }
	 * 
	 * public void clickToDownloads() { WebElement link =
	 * driver.findElement(byDownloads); link.click(); }
	 * 
	 * public void clickToRewardPoints() { WebElement link =
	 * driver.findElement(byRewardPoints); link.click(); }
	 * 
	 * public void clickToRequests() { WebElement link =
	 * driver.findElement(byRequests); link.click(); }
	 * 
	 * public void clickToTransactions() { WebElement link =
	 * driver.findElement(byTransactions); link.click(); }
	 * 
	 * public void clickToPayments() { WebElement link =
	 * driver.findElement(byPayments); link.click(); }
	 * 
	 * public void clickToAffiliateAccount() { WebElement link =
	 * driver.findElement(byAffiliateAccount); link.click(); }
	 * 
	 * public void clickToEditNewsletter() { WebElement link =
	 * driver.findElement(byEditNewsletter); link.click(); }
	 * 
	 **/

	public void verifyLogin() {
		WebElement title = driver.findElement(byAffiliateAccount);
		Assert.assertEquals(title.getText().equals("My Account"), true, "El login fue incorrecto");
	}

}
