package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.Navigation;
import Pages.Product;
import Pages.Review;
import Pages.Search;
import Utils.ReadData;

@Listeners(Listener.ListenerTestNG.class)
public class ReviewProduct {
	Navigation Navigation;
	Search PagSearch;
	Review PagReview;
	Login PagLogin;
	Product Product;
	ReadData dataFile;

	/**
	 * Data for Search product
	 */
	String productName;
	String userName;
	String review;
	String rating;

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
		PagReview = new Review(browser, driverPath);
	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseReviewProduct() throws InterruptedException {
		Navigation.setEvidencePath("ReviewProduct");
		readDataForProduct("addReviewProduct");
		// Enter value and search
		searchProduct(productName);
		// Validate search
		PagSearch.validateSearch(productName);
		// Open Page Detail Product
		Product.openDetailProduct(productName);
		Navigation.takeScreenshot();
		// Add Review
		PagReview.openReviews();
		Navigation.takeScreenshot();
		PagReview.enterYourName(userName);
		PagReview.enterReview(review);
		PagReview.enterRating(rating);
		Navigation.takeScreenshot();
		PagReview.clickContinue();
		// Validate Review
		PagReview.validateSubmittedReview();
		Navigation.takeScreenshot();
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();
	}

	private void readDataForProduct(String nameTestCase) {
		JSONObject data = dataFile.readNode(nameTestCase);
		productName = data.get("productName").toString();
		userName = data.get("userName").toString();
		review = data.get("review").toString();
		rating = data.get("rating").toString();
	}

	public void searchProduct(String name) {
		Navigation.takeScreenshot();
		// Enter search from home page
		Navigation.enterValueSearch(name);
		Navigation.takeScreenshot();
		Navigation.clickButtonSearch();
		Navigation.takeScreenshot();
	}

}
