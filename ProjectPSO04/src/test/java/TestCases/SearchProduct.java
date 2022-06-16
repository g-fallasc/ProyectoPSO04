package TestCases;

import org.json.simple.JSONObject;
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
import Utils.ReadData;

public class SearchProduct {
	Navigation Navigation;
	Search PagSearch;
	Login PagLogin;
	Product Product;
	ReadData dataFile;

	/**
	 * Data for Search product
	 */
	String productName;
	String productDetail;
	String selectSort;
	String selectShow;
	String optionViewProduct;

	@Parameters({ "browser", "driverPath", "dataPath", "evidencePath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath, String evidencePath) {
		// Read data from JSON {dataSearch.json}
		dataFile = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath, evidencePath);

		// Pages Instances
		PagSearch = new Search(browser, driverPath);
		Product = new Product(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseSearchProductExist() throws InterruptedException {
		Navigation.setEvidencePath("SearchProductExist");
		readDataForProduct("searchProductExist");
		// Enter value and search
		searchProduct(productName);
		// Validate search
		PagSearch.validateSearch(productName);
		Navigation.takeScreenshot();
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
	}

	@Test
	public void TestCaseSearchProductNotExist() throws InterruptedException {
		Navigation.setEvidencePath("SearchProductNotExist");
		readDataForProduct("searchProductNotExist");
		// Enter value and search
		searchProduct(productName);
		// Validate search
		PagSearch.validateSearch(productName);
		Navigation.takeScreenshot();
		// Validate if not exist
		if (PagSearch.isMatchProduct()) {
			Assert.fail("El producto: " + productName + " si existe.");
		}
	}

	@Test
	public void TestCaseSearchChangeCriteria() throws InterruptedException {
		Navigation.setEvidencePath("SearchChangeCriteria");
		readDataForChangeCriteria();
		// Enter value and search
		searchProduct(productName);
		// Validate search
		PagSearch.validateSearch(productName);
		Navigation.takeScreenshot();
		PagSearch.clickCheckProductDescriptions();
		Navigation.takeScreenshot();
		PagSearch.clickSearchButton();
		Navigation.takeScreenshot();
		PagSearch.selectSortBy(selectSort);
		Navigation.takeScreenshot();
		PagSearch.selectShow(selectShow);
		Navigation.takeScreenshot();
		PagSearch.selectViewProductResult(optionViewProduct);
		Navigation.takeScreenshot();
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}

	public void searchProduct(String name) {
		Navigation.takeScreenshot();
		// Enter search from home page
		Navigation.enterValueSearch(name);
		Navigation.takeScreenshot();
		Navigation.clickButtonSearch();
		Navigation.takeScreenshot();
	}

	public void LoginUser(String email, String password) throws InterruptedException {
		// Login user validate
		Navigation.navToLogin();
		PagLogin.enterEmail(email);
		PagLogin.enterPassword(password);
		PagLogin.clickLogin();
	}

	private void readDataForProduct(String nameTestCase) {
		JSONObject data = dataFile.readNode(nameTestCase);
		productName = data.get("productName").toString();
		productDetail = data.get("productDetail").toString();
	}

	private void readDataForChangeCriteria() {
		JSONObject data = dataFile.readNode("searchChangeCriteria");
		productName = data.get("productName").toString();
		productDetail = data.get("productDetail").toString();
		selectSort = data.get("selectSort").toString();
		selectShow = data.get("selectShow").toString();
		optionViewProduct = data.get("optionViewProduct").toString();
	}

}
