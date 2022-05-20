package TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Checkout;
import Pages.Login;
import Pages.Navigation;

public class CheckoutOrder {

	WebDriver driver;

	Navigation Navigation;
	Checkout PagCheckout;
	Login PagLogin;

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
		PagCheckout = new Checkout(driver);
		PagLogin = new Login(driver);
	}

	// ----------------------------------------------------------------------------------------\\
	@Test
	public void TestAddCart1() throws InterruptedException {
		String nameProduct = "MacBook";

		addProductToCart(nameProduct);
		Thread.sleep(2000);
		verifyProductToCart(nameProduct);
		Thread.sleep(1500);
	}

	@Test
	public void TestAddCart2() throws InterruptedException {
		String nameProduct = "iPhone";

		addProductToCart(nameProduct);
		Thread.sleep(2000);
		verifyProductToCart(nameProduct);
		Thread.sleep(1500);
	}

	@Test
	public void TestAddCart3() throws InterruptedException {
		String nameProduct = "iPhone 13XL";

		addProductToCart(nameProduct);
		Thread.sleep(2000);
		verifyProductToCart(nameProduct);
		Thread.sleep(1500);
	}

	public void addProductToCart(String nameProduct) {
		By byNameProduct = By.xpath("//div[@class='caption']//a[contains(text(), '" + nameProduct + "')]");
		WebElement product = driver.findElement(byNameProduct);

		// get product id
		String productId = product.getAttribute("href").split("product_id=", 2)[1];

		if (product.isDisplayed()) {
			By byBtnAddCart = By.xpath("//button[@type='button' and contains(@onclick, '" + productId + "')]");
			WebElement buttonAddProductCart = driver.findElement(byBtnAddCart);
			buttonAddProductCart.click();
		} else {
			Assert.fail("El producto no fue encontrado");
		}
	}

	public void verifyProductToCart(String nameProduct) {
		By byAlertMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
		WebElement alertMessage = driver.findElement(byAlertMessage);
		Assert.assertEquals(
				alertMessage.getText().equals("Success: You have added " + nameProduct + " to your shopping cart!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void validateProducto(String description) {
		By bydescription = By.xpath("//div[@class='caption']");
		boolean num = false;

		List<WebElement> listDescriptions = driver.findElements(bydescription);
		for (int i = 0; i < listDescriptions.size(); i++) {
			String st = listDescriptions.get(i).getText();
			st = st.replaceAll("\n", " ");
			if (st.equals(description)) {
				num = true;
			}
		}
		Assert.assertTrue(num, "No se encontro el producto");
	}

	// ----------------------------------------------------------------------------------------\\

	@Test
	public void TestCaseCheckoutOrder() throws InterruptedException {
		// Login Account
		LoginUser("gfallasc@ucenfotec.ac.cr", "12345678");

		// Navigation to register page
		Navigation.navToCheckout();
		Thread.sleep(1000);

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
			Thread.sleep(2000);
		} else {
			// Enter personal details
			PagCheckout.enterFirstName("Prueba");
			Thread.sleep(500);
			PagCheckout.enterLastName("Test Case");
			Thread.sleep(500);
			PagCheckout.enterCompany("Cenfotec");
			Thread.sleep(500);
			PagCheckout.enterAddress1("Calle 1");
			PagCheckout.enterCity("San Jose");
			Thread.sleep(500);
			PagCheckout.enterPostCode("12345");
			Thread.sleep(500);
			PagCheckout.selectCounty("Costa Rica");
			Thread.sleep(500);
			PagCheckout.selectRegionState("San Jose");
			Thread.sleep(1000);
			PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
			Thread.sleep(1000);
		}
		// Payment Method
		PagCheckout.enterCommentOrder("Orden de prueba");
		PagCheckout.checkTermsConditions();
		Thread.sleep(2000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		Thread.sleep(2500);
	}

	@Test
	public void TestCaseCheckoutOrderAddNewAddres() throws InterruptedException {
		// Login Account
		LoginUser("gus@email.com", "1234A");

		// Navigation to register page
		Navigation.navToCheckout();
		Thread.sleep(1000);

		if (PagCheckout.isExistingAddress()) {
			PagCheckout.checkNewAddress();
			Thread.sleep(1000);
		}
		// Enter personal details
		PagCheckout.enterFirstName("Prueba");
		Thread.sleep(500);
		PagCheckout.enterLastName("Test Case");
		Thread.sleep(500);
		PagCheckout.enterCompany("Cenfotec");
		Thread.sleep(500);
		PagCheckout.enterAddress1("Calle 1");
		PagCheckout.enterCity("San Jose");
		Thread.sleep(500);
		PagCheckout.enterPostCode("12345");
		Thread.sleep(500);
		PagCheckout.selectCounty("Costa Rica");
		Thread.sleep(500);
		PagCheckout.selectRegionState("San Jose");
		Thread.sleep(1000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinueBilling);
		Thread.sleep(1000);
		// Payment Method
		PagCheckout.enterCommentOrder("Orden de prueba");
		PagCheckout.checkTermsConditions();
		Thread.sleep(1000);
		PagCheckout.clickContinue(PagCheckout.byButtonContinuePayment);
		Thread.sleep(3500);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {

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
