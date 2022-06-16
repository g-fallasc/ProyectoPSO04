package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Search {
	// Inputs
	@FindBy(id = "input-search")
	WebElement inputKeywords;
	@FindBy(xpath = "//select[@name='category_id']")
	WebElement selectCategories;
	@FindBy(id = "description")
	WebElement checkInDescriptions;
	By byCheckInSubCategories = By.xpath("//input[@type='checkbox'][@name='sub_category']");
	// Button search
	By byBtnSearch = By.xpath("//input[@type='button'][@value='Search']");
	// Result
	@FindBy(xpath = "//h2[contains(text(), 'Products meeting the search criteria')]")
	WebElement textTitleProducts;
	@FindBy(id = "input-sort")
	WebElement selectSort;
	@FindBy(id = "input-limit")
	WebElement selectShow;
	@FindBy(id = "compare-total")
	WebElement linkToProductCompare;
	By byTextNoResulMatch = By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]");

	private AdapterSelenium adapter;

	public Search(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
		PageFactory.initElements(adapter.getDriver(), this);
	}

	public void validateSearch(String value) {
		By byTitleSearchResult = By.xpath("//h1[contains(text(), 'Search - " + value + "')]");
		Assert.assertTrue(adapter.isElementExisting(byTitleSearchResult), "El titulo es incorrecto");
	}

	public boolean isMatchProduct() {
		boolean isVisibleText = adapter.isElementExisting(byTextNoResulMatch);
		return !isVisibleText;
	}

	public void enterKeywords(String value) {
		adapter.enterTextByWebElement(inputKeywords, value);
	}

	public void selectCategory(String category) {
		adapter.selectElementByWebElement(selectCategories, "text", category);
	}

	public void clickCheckSubcagetories() {
		adapter.clickByLocator(byCheckInSubCategories);
	}

	public void clickCheckProductDescriptions() {
		adapter.clickByWebElement(checkInDescriptions);
	}

	// Product exist
	public void clickProductCompare() {
		adapter.clickByWebElement(linkToProductCompare);
	}

	public void selectSortBy(String sortValue) {
		adapter.selectElementByWebElement(selectSort, "text", sortValue);
	}

	public void selectShow(String viewValue) {
		adapter.selectElementByWebElement(selectShow, "text", viewValue);

	}

	/**
	 * @param view "grid" or "list"
	 */
	public void selectViewProductResult(String view) {
		By bySelectView = By.id(view + "-view"); // grid-view or list-view
		adapter.clickByLocator(bySelectView);
	}

	public void clickSearchButton() {
		adapter.clickByLocator(byBtnSearch);
	}

}
