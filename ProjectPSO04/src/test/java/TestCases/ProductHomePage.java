package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Navigation;
import Pages.Product;

public class ProductHomePage {
	WebDriver driver;

	Navigation Navigation;
	Product Product;

	@BeforeClass
	public void beforeClass() {
	}

	@Parameters({ "browser", "driverPath", "URL" })
	@BeforeMethod
	public void beforeTest(String browser, String driverPath, String URL) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.geckod.driver", driverPath);
			// driver = new FirefoxDriver();
		}
		// Instance Navigation class
		Navigation = new Navigation(driver, URL);

		// Pages Instances
		Product = new Product(driver);
	}

	@Test
	public void TestCaseSelectCurrency() throws InterruptedException {
		// Select currency type
		Navigation.selectCurrency("EUR");
		Thread.sleep(1500);

		// Validate change currency in product detail
		String productDetailEUR = "MacBook Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1.. 472.33€ Ex Tax: 392.30€";
		// Validate product detail in home page
		Product.validateProducto(productDetailEUR, true);
		Thread.sleep(1000);
	}

	@Test
	public void TestCaseValidateProduct() throws InterruptedException {
		String productDetail = "iPhone iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a nam.. $123.20 Ex Tax: $101.00";

		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
		Thread.sleep(1000);
	}

	@Test
	public void TestCaseValidateProductNotExist() throws InterruptedException {
		String productDetail = "iPhone 13XL iPhone.. $555.20 Ex Tax: $110.99";

		// Validate product detail in home page
		Product.validateProducto(productDetail, false);
		Thread.sleep(1000);
	}

	@Test
	public void TestCaseProductAddToCart() throws InterruptedException {
		String productName = "MacBook";

		// Find product 'productName' and add to cart
		Product.addProductToCart(productName);
		Thread.sleep(2000);

		// Verify if product add to cart
		Product.verifyProductToCart(productName);
		Thread.sleep(1500);
	}

	@Test
	public void TestCaseProductAddToWishList() throws InterruptedException {
		String productName = "iPhone";

		// Find product 'productName' and add to cart
		Product.addProductToWishList(productName);
		Thread.sleep(1000);

		// Verify if product add to Wish list
		Product.verifyProductToWishList(productName);
		Thread.sleep(2500);
	}

	@Test
	public void TestCaseProductAddToCompare() throws InterruptedException {
		String productName = "iPhone";

		// Find product 'productName' and add to cart
		Product.addProductToCompare(productName);
		Thread.sleep(1000);

		// Verify if product add to Compare List
		Product.verifyProductToCompare(productName);
		Thread.sleep(2500);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
	}

}