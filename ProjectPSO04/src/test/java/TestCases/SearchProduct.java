package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.Navigation;
import Pages.Product;
import Pages.Search;

public class SearchProduct {

	WebDriver driver;

	Navigation Navigation;
	Search PagSearch;
	Login PagLogin;
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
		PagSearch = new Search(driver);
		Product = new Product(driver);
	}

	@Test
	public void TestCaseSearchProductExist() throws InterruptedException {
		String productName = "iPhone";

		// Enter value and search
		searchProduct(productName);

		// Validate search
		PagSearch.validateSearch(productName);
		Thread.sleep(1000);

		// Validate product result
		String productDetail = "iPhone iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a name o.. $123.20 Ex Tax: $101.00";
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
		Thread.sleep(1000);
	}

	@Test
	public void TestCaseSearchProductNotExist() throws InterruptedException {
		String productName = "iPhone 2";

		// Enter value and search
		searchProduct(productName);
		Thread.sleep(1500);

		// Validate search
		PagSearch.validateSearch(productName);
		Thread.sleep(1000);

		// Validate if not exist
		if (PagSearch.isMatchProduct()) {
			Assert.fail("El producto: " + productName + " si existe.");
		}
	}

	@Test
	public void TestCaseSearchChangeCriteria() throws InterruptedException {
		String productName = "iPhone";

		// Enter value and search
		searchProduct(productName);

		// Validate search
		PagSearch.validateSearch(productName);
		Thread.sleep(1000);

		PagSearch.clickCheckProductDescriptions();
		Thread.sleep(1000);
		PagSearch.clickSearchButton();

		PagSearch.selectSortBy("Model (A - Z)");
		Thread.sleep(1000);

		PagSearch.selectShow("100");
		Thread.sleep(2000);

		PagSearch.selectViewProductResult("list");
		Thread.sleep(2000);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {

	}

	public void searchProduct(String name) {
		// Enter search from home page
		Navigation.enterValueSearch(name);
		Navigation.clickButtonSearch();
	}

	public void LoginUser(String email, String password) throws InterruptedException {
		// Login user validate
		Navigation.navToLogin();
		Thread.sleep(1000);
		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();
		Thread.sleep(1000);
	}

}
