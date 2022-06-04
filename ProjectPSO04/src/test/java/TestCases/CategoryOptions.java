package TestCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utils.ReadData;
import Pages.Categories;
import Pages.Navigation;
import Pages.Product;

public class CategoryOptions {
	Categories Category;
	Navigation Navigation;
	ReadData readData;
	Product Product;

	/**
	 * Data
	 */
	String categoryName;
	String typeName;
	String detail;

	@Parameters({ "browser", "driverPath", "dataPath", "evidencePath" })
	@BeforeClass
	public void beforeClass(String browser, String driverPath, String dataPath, String evidencePath) {
		// Read data from JSON {dataAuth.json}
		readData = new ReadData(dataPath);
		// Instance Navigation class
		Navigation = new Navigation(browser, driverPath, evidencePath);
		// Pages Instances
		Category = new Categories(browser, driverPath);
		Product = new Product(browser, driverPath);

	}

	@Parameters({ "URL" })
	@BeforeMethod
	public void beforeTest(String URL) {
		Navigation.openURL(URL);
	}

	@Test
	public void TestCaseSelectCategory() throws InterruptedException {
		readDataForCategory("selectCategory");
		Navigation.setEvidencePath("TestCaseSelectCategory");
		Navigation.takeScreenshot();
		Category.clickCategory(categoryName);
		Navigation.takeScreenshot();
		Category.clickType(typeName);
		Navigation.takeScreenshot();
		Product.validateProducto(detail, true);
	}

	@AfterMethod
	public void afterTest() {

	}

	@AfterClass
	public void afterClass() {
		Navigation.closeDriver();

	}

	private void readDataForCategory(String nameTestCase) {
		JSONObject data = readData.readNode(nameTestCase);
		categoryName = data.get("categoryName").toString();
		typeName = data.get("typeName").toString();
		detail = data.get("detail").toString();
	}

}
