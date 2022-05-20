package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Search {
	WebDriver driver;

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

	public Search(WebDriver driver) {
		this.driver = driver;
	}

	public void validateSearch(String value) {
		By byTitleSearchResult = By.xpath("//h1[contains(text(), 'Search - " + value + "')]");
		WebElement title = driver.findElement(byTitleSearchResult);
		Assert.assertTrue(title.isDisplayed(), "El titulo es incorrecto");
	}

	public boolean isMatchProduct() {
		if (driver.getPageSource().contains("There is no product that matches the search criteria.")) {
			WebElement text = driver.findElement(byTextNoResulMatch);
			Assert.assertTrue(text.isDisplayed(), "No se encontro el producto");
			return !text.isDisplayed();
		} else {
			return true;
		}
	}

	public void enterKeywords(String value) {
		WebElement input = driver.findElement(byKeywords);
		input.clear();
		input.sendKeys(value);
	}

	public void selectCategory(String category) {
		Select categories = new Select(driver.findElement(bySelectCategories));
		categories.selectByVisibleText(category);
	}

	public void clickCheckSubcagetories() {
		WebElement checkboxSubCategories = driver.findElement(byCheckInSubCategories);
		checkboxSubCategories.click();
	}

	public void clickCheckProductDescriptions() {
		WebElement checkboxDescriptions = driver.findElement(byCheckInDescriptions);
		checkboxDescriptions.click();
	}

	// Product exist
	public void clickProductCompare() {
		WebElement productCompare = driver.findElement(byProductCompare);
		productCompare.click();
	}

	public void selectSortBy(String sortValue) {
		Select sortBy = new Select(driver.findElement(bySelectSort));
		sortBy.selectByVisibleText(sortValue);
	}

	public void selectShow(String viewValue) {
		Select show = new Select(driver.findElement(bySelectShow));
		show.selectByVisibleText(viewValue);
	}

	/**
	 * @param view "grid" or "list"
	 */
	public void selectViewProductResult(String view) {
		By bySelectView = By.id(view + "-view"); // grid-view or list-view
		WebElement selectView = driver.findElement(bySelectView);
		selectView.click();
	}

	public void clickSearchButton() {
		WebElement button = driver.findElement(byBtnSearch);
		button.click();
	}

}
