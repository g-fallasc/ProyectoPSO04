package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Navigation;
import Pages.Product;
import Utils.ReadData;

public class ProductHomePage {
	Navigation Navigation;
	Product Product;
	ReadData dataFile;

	/**
	 * Data for Verify product
	 */
	String productName;
	String productDetail;
	String selectCurrency;

	@Parameters({ "browser", "driverPath", "dataPath", "evidencePath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath, String evidencePath) {
		// Read data from JSON {dataProduct.json}
		dataFile = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath, evidencePath);

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
		Navigation.setEvidencePath("SelectCurrency");
		readDataForTestCase("selectCurrency");
		Navigation.takeScreenshot();
		// Select currency type
		Navigation.selectCurrency(selectCurrency);
		Navigation.takeScreenshot();
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseValidateProduct() throws InterruptedException {
		Navigation.setEvidencePath("ValidateProduct");
		readDataForTestCase("validateProduct");
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseValidateProductNotExist() throws InterruptedException {
		Navigation.setEvidencePath("ValidateProductNotExist");
		readDataForTestCase("validateProductNotExist");
		// Validate product detail in home page
		Product.validateProducto(productDetail, false);
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseProductAddToCart() throws InterruptedException {
		Navigation.setEvidencePath("ProductAddToCart");
		readDataForTestCase("productAddToCart");
		// Find product 'productName' and add to cart
		Product.addProductToCart(productName);
		Navigation.takeScreenshot();
		// Verify if product add to cart
		Product.verifyProductToCart(productName);
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseProductAddToWishList() throws InterruptedException {
		Navigation.setEvidencePath("ProductAddToWishList");
		readDataForTestCase("productAddToWishList");
		// Find product 'productName' and add to cart
		Product.addProductToWishList(productName);
		Navigation.takeScreenshot();
		// Verify if product add to Wish list
		Product.verifyProductToWishList(productName);
		Navigation.takeScreenshot();
	}

	@Test
	public void TestCaseProductAddToCompare() throws InterruptedException {
		Navigation.setEvidencePath("ProductAddToCompare");
		readDataForTestCase("productAddToCompare");
		// Find product 'productName' and add to cart
		Product.addProductToCompare(productName);
		Navigation.takeScreenshot();
		// Verify if product add to Compare List
		Product.verifyProductToCompare(productName);
		Navigation.takeScreenshot();
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}

	private void readDataForTestCase(String nameTestCase) {
		JSONObject data = dataFile.readNode(nameTestCase);
		productName = data.get("productName").toString();
		productDetail = data.get("productDetail").toString();
		selectCurrency = data.get("selectCurrency").toString();
	}
}