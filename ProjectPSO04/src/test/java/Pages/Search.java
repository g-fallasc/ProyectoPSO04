package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Search {
	// Inputs
	By byKeywords = By.id("input-search");
	By bySelectCategories = By.xpath("//select[@name='category_id']");
	By byCheckInSubCategories = By.xpath("//input[@type='checkbox'][@name='sub_category']");
	By byCheckInDescriptions = By.xpath("//input[@type='checkbox'][@name='description']");
	// Button search
	By byBtnSearch = By.xpath("//input[@type='button'][@value='Search']");
	// Result
	By byTitleProducts = By.xpath("//h2[contains(text(), 'Products meeting the search criteria')]");
	By byTextNoResulMatch = By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]");
	By bySelectSort = By.id("input-sort");
	By bySelectShow = By.id("input-limit");
	By byProductCompare = By.id("compare-total");

	private AdapterSelenium adapter;

	public Search(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void validateSearch(String value) {
		By byTitleSearchResult = By.xpath("//h1[contains(text(), 'Search - " + value + "')]");
		Assert.assertTrue(adapter.isElementExisting(byTitleSearchResult), "El titulo es incorrecto");
	}

	public boolean isMatchProduct() {
		if (adapter.isContainsPageSource("There is no product that matches the search criteria.")) {
			Assert.assertTrue(adapter.isElementExisting(byTextNoResulMatch), "No se encontro el producto");
			return false;
		} else {
			return true;
		}
	}

	public void enterKeywords(String value) {
		adapter.enterText(byKeywords, value);
	}

	public void selectCategory(String category) {
		adapter.selectElement(bySelectCategories, "text", category);
	}

	public void clickCheckSubcagetories() {
		adapter.clickElement(byCheckInSubCategories);
	}

	public void clickCheckProductDescriptions() {
		adapter.clickElement(byCheckInDescriptions);
	}

	// Product exist
	public void clickProductCompare() {
		adapter.clickElement(byProductCompare);
	}

	public void selectSortBy(String sortValue) {
		adapter.selectElement(bySelectSort, "text", sortValue);
	}

	public void selectShow(String viewValue) {
		adapter.selectElement(bySelectShow, "text", viewValue);

	}

	/**
	 * @param view "grid" or "list"
	 */
	public void selectViewProductResult(String view) {
		By bySelectView = By.id(view + "-view"); // grid-view or list-view
		adapter.clickElement(bySelectView);
	}

	public void clickSearchButton() {
		adapter.clickElement(byBtnSearch);
	}

}
