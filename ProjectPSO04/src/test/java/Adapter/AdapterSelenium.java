package Adapter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdapterSelenium {

	private WebDriver driver;
	private static AdapterSelenium adapter = null;
	int counter = 0;

	public static AdapterSelenium getAdapter(String browser, String driverPath) {
		if (adapter == null) {
			adapter = new AdapterSelenium(browser, driverPath);
		}
		return adapter;
	}

	public AdapterSelenium(String browser, String driverPath) {
		try {
			if (browser.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
			} else if (browser.equals("Firefox")) {
				System.setProperty("webdriver.geckod.driver", driverPath);
				// driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (WebDriverException e) {
			Assert.fail(e.getMessage());
		}

	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void openURL(String URL) {
		driver.get(URL);
	}

	public void closeDriver() {
		adapter = null;
		driver.quit();
	}

	public WebElement createElement(By byLocator, String action) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
		} catch (NoSuchElementException e) {
			Assert.fail(e.getMessage());
		}
		return element;
	}

	public void enterText(By byLocator, String text) {
		WebElement element = createElement(byLocator, "INGRESAR");
		element.clear();
		element.sendKeys(text);
	}

	public void clickElement(By byLocator) {
		WebElement element = null;
		try {
			element = createElement(byLocator, "CLICK");
			element.click();
		} catch (ElementClickInterceptedException e) {
			Assert.fail(e.getMessage());

			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].click()", element);
		}

	}

	public String getText(By byLocator) {
		String text = "";
		try {
			text = createElement(byLocator, "OBTENER").getText();
		} catch (ElementNotInteractableException e) {
			Assert.fail(e.getMessage());
		}
		return text;
	}

	public List<WebElement> getListElements(By byLocator) {
		List<WebElement> listElements = null;
		try {
			listElements = driver.findElements(byLocator);
		} catch (NoSuchElementException e) {
			Assert.fail(e.getMessage());
		}
		return listElements;
	}

	public void selectElement(By byLocator, String typeSelect, String value) {
		try {
			Select select = new Select(createElement(byLocator, "SELECCIONAR"));
			if (typeSelect.equals("value")) {
				select.selectByValue(value);
			} else if (typeSelect.equals("text")) {
				select.selectByVisibleText(value);
			}
		} catch (InvalidSelectorException e) {
			Assert.fail(e.getMessage());
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

	public void captureScreen(String path) {
		try {
			String nameCapture = "img" + getCounter() + ".png";
			// Convert web driver object to TakeScreenshot
			TakesScreenshot screenShot = ((TakesScreenshot) driver);
			// Call getScreenshotAs method to create image file
			File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
			// Move image file to new destination
			File destinationFile = new File(path + "\\" + nameCapture);
			// Copy file at destination
			FileUtils.copyFile(srcFile, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getCounter() {
		counter++;
		return counter;
	}

}
