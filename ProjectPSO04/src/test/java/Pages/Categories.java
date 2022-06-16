package Pages;

import org.openqa.selenium.By;

import Adapter.AdapterSelenium;

public class Categories {

	private AdapterSelenium adapter;

	public Categories(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void clickCategory(String category) {
		By byCategory = By.xpath("//nav//a[text() = '" + category + "']");
		adapter.clickByLocator(byCategory);
	}

	public void clickType(String type) {
		By byType = By.xpath("//nav//a[contains(text(),'" + type + "')]");
		adapter.clickByLocator(byType);
	}

	public void clickShowAll(String showAll) {
		By byShowAll = By.xpath("//nav//a[contains(text(),'" + showAll + "')][@class='see-all']");
		adapter.clickByLocator(byShowAll);
	}

}
