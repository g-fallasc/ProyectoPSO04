package Adapter;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdapterSelenium {

	private WebDriver driver;
	private static AdapterSelenium adapter = null;

	public static AdapterSelenium getAdapter(String browser, String driverPath) {
		if (adapter == null) {
			adapter = new AdapterSelenium(browser, driverPath);
		}
		return adapter;
	}

	public AdapterSelenium(String browser, String driverPath) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.geckod.driver", driverPath);
			// driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public void openURL(String URL) {
		driver.get(URL);
	}

	public void closeDriver() {
		adapter = null;
		driver.quit();
	}

	public WebElement createElement(By byLocator, String action) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = null;
		if (action.equals("CLICK")) {
			element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		} else if (action.equals("INGRESAR")) {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		} else if (action.equals("OBTENER")) {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		} else if (action.equals("SELECCIONAR")) {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		} else {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		}
		return element;
	}

	public void enterText(By byLocator, String text) {
		WebElement element = createElement(byLocator, "INGRESAR");
		element.clear();
		element.sendKeys(text);
	}

	public void clickElement(By byLocator) {
		createElement(byLocator, "CLICK").click();
	}

	public String getText(By byLocator) {
		return createElement(byLocator, "OBTENER").getText();
	}

	public List<WebElement> getListElements(By byLocator) {
		return driver.findElements(byLocator);
	}

	public void selectElement(By byLocator, String typeSelect, String value) {
		Select select = new Select(createElement(byLocator, "SELECCIONAR"));
		if (typeSelect.equals("value")) {
			select.selectByValue(value);
		} else if (typeSelect.equals("text")) {
			select.selectByVisibleText(value);
		}
	}

	public String getAttribute(By byLocator, String attribute) {
		return createElement(byLocator, "").getAttribute(attribute);
	}

	public boolean isElementExisting(By byLocator) {
		return createElement(byLocator, "").isDisplayed();
	}

	public boolean isContainsPageSource(String text) {
		return driver.getPageSource().contains(text);
	}

}
