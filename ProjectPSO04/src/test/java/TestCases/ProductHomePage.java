package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Adapter.ReadData;
import Pages.Navigation;
import Pages.Product;

public class ProductHomePage {
	Navigation Navigation;
	Product Product;
	ReadData readData;

	/**
	 * Data for Verify product
	 */
	String productName;
	String productDetail;
	String selectCurrency;

	@Parameters({ "browser", "driverPath", "dataPath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath) {
		// Read data from JSON {dataProduct.json}
		readData = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath);

		// Pages Instances
		Product = new Product(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseSelectCurrency() throws InterruptedException {
		readDataForTestCase("selectCurrency");
		// Select currency type
		Navigation.selectCurrency(selectCurrency);
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
	}

	@Test
	public void TestCaseValidateProduct() throws InterruptedException {
		readDataForTestCase("validateProduct");
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
	}

	@Test
	public void TestCaseValidateProductNotExist() throws InterruptedException {
		readDataForTestCase("validateProductNotExist");
		// Validate product detail in home page
		Product.validateProducto(productDetail, false);
	}

	@Test
	public void TestCaseProductAddToCart() throws InterruptedException {
		readDataForTestCase("productAddToCart");
		// Find product 'productName' and add to cart
		Product.addProductToCart(productName);
		// Verify if product add to cart
		Product.verifyProductToCart(productName);
	}

	@Test
	public void TestCaseProductAddToWishList() throws InterruptedException {
		readDataForTestCase("productAddToWishList");
		// Find product 'productName' and add to cart
		Product.addProductToWishList(productName);
		// Verify if product add to Wish list
		Product.verifyProductToWishList(productName);
	}

	@Test
	public void TestCaseProductAddToCompare() throws InterruptedException {
		readDataForTestCase("productAddToCompare");
		// Find product 'productName' and add to cart
		Product.addProductToCompare(productName);
		// Verify if product add to Compare List
		Product.verifyProductToCompare(productName);
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}

	private void readDataForTestCase(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		productName = data.get("productName").toString();
		productDetail = data.get("productDetail").toString();
		selectCurrency = data.get("selectCurrency").toString();
	}
}