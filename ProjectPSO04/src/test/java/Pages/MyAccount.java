package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class MyAccount {
	// My Account
	@FindBy(xpath = "//a[contains(text(), 'Edit your account information')]")
	WebElement linkToEditAccountInfo;
	@FindBy(xpath = "//a[contains(text(), 'Change your password')]")
	WebElement linkToChangePass;
	@FindBy(xpath = "//a[contains(text(), 'Modify your address book entries')]")
	WebElement linkToEditAdressBook;
	@FindBy(xpath = "//a[contains(text(), 'Modify your wish list')]")
	WebElement linkToEditWishList;
	@FindBy(xpath = "//h2[contains(text(), 'My Account')]")
	WebElement textTitleMyAccount;
	// My Orders
	@FindBy(xpath = "//a[contains(text(), 'View your order history')]")
	WebElement linkToViewOrderHistory;
	@FindBy(xpath = "//a[contains(text(), 'Downloads')]")
	WebElement linkToDownloads;
	@FindBy(xpath = "//a[contains(text(), 'Your Reward Points')]")
	WebElement linkToRewardPoints;
	@FindBy(xpath = "//a[contains(text(), 'View your return requests')]")
	WebElement linkToRequests;
	@FindBy(xpath = "//a[contains(text(), 'Your Transactions')]")
	WebElement linkToTransactions;
	@FindBy(xpath = "//a[contains(text(), 'Recurring payments')]")
	WebElement linkToPayments;
	@FindBy(xpath = "//h2[contains(text(), 'My Orders')]")
	WebElement textTitleMyOrders;
	// My Affiliate Account
	@FindBy(xpath = "//a[contains(text(), 'Register for an affiliate account')]")
	WebElement linkToAffiliateAccount;
	@FindBy(xpath = "//h2[contains(text(), 'My Affiliate Account')]")
	WebElement textTitleMyAffiliate;

	By byEditAffiliateInfo = By.xpath("//a[contains(text(), 'Edit your affiliate information')]");
	By byCustomTrackingCode = By.xpath("//a[contains(text(), 'Custom Affiliate Tracking Code')]");

	// Newsletter
	@FindBy(xpath = "//a[contains(text(), 'Subscribe / unsubscribe to newsletter')]")
	WebElement linkToEditNewsletter;
	@FindBy(xpath = "//h2[contains(text(), 'Newsletter')]")
	WebElement textTitleNewsletter;

	private AdapterSelenium adapter;

	public MyAccount(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
		PageFactory.initElements(adapter.getDriver(), this);
	}

	public void clickToEditAccountInfo() {
		adapter.clickByWebElement(linkToEditAccountInfo);
	}

	public void clickToChangePassword() {
		adapter.clickByWebElement(linkToChangePass);
	}

	public void clickToEditAdressBook() {
		adapter.clickByWebElement(linkToEditAccountInfo);
	}

	public void clickToEditWhisList() {
		adapter.clickByWebElement(linkToEditWishList);
	}

	public void clickToOrderHistory() {
		adapter.clickByWebElement(linkToViewOrderHistory);
	}

	public void clickToDownloads() {
		adapter.clickByWebElement(linkToDownloads);
	}

	public void clickToRewardPoints() {
		adapter.clickByWebElement(linkToRewardPoints);
	}

	public void clickToRequests() {
		adapter.clickByWebElement(linkToRequests);
	}

	public void clickToTransactions() {
		adapter.clickByWebElement(linkToTransactions);
	}

	public void clickToPayments() {
		adapter.clickByWebElement(linkToPayments);
	}

	public void clickToAffiliateAccount() {
		adapter.clickByWebElement(linkToAffiliateAccount);
	}

	public void clickToEditNewsletter() {
		adapter.clickByWebElement(linkToEditNewsletter);
	}

	public void verifyLogin() {
		Assert.assertEquals(adapter.getTextByWebElement(textTitleMyAccount).equals("My Account"), true,
				"El login fue incorrecto");
	}

}
