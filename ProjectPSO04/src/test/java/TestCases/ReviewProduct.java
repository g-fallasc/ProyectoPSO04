package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.Navigation;
import Pages.Product;
import Pages.Review;
import Pages.Search;
import Utils.ReadData;

public class ReviewProduct {
	Navigation Navigation;
	Search PagSearch;
	Review PagReview;
	Login PagLogin;
	Product Product;
	ReadData readData;

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
		readData = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath, evidencePath);

		// Pages Instances
		PagSearch = new Search(browser, driverPath);
		Product = new Product(browser, driverPath);
		PagReview = new Review(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseReviewProduct() throws InterruptedException {
		readDataForProduct("searchProductExist");
		// Enter value and search
		// searchProduct(productName);
		// Validate search
		PagSearch.validateSearch(productName);
		// Validate product detail in home page
		Product.validateProducto(productDetail, true);
	}

	private void readDataForProduct(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		productName = data.get("productName").toString();
		productDetail = data.get("productDetail").toString();
	}

}
