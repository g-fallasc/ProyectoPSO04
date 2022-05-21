package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Product {

	WebDriver driver;

	By byProductDetail = By.xpath("//div[@class='caption']");

	public Product(WebDriver driver) {
		this.driver = driver;
	}

	public void validateProducto(String description, boolean expectedResult) {
		boolean isEquals = false;
		List<WebElement> listDescriptions = driver.findElements(byProductDetail);
		for (int i = 0; i < listDescriptions.size(); i++) {
			String textProductDetail = listDescriptions.get(i).getText();
			textProductDetail = textProductDetail.replaceAll("\n", " ");
			if (textProductDetail.equals(description)) {
				isEquals = true;
			}
		}
		Assert.assertEquals(isEquals, expectedResult, "No se encontro el Producto");
	}

	public void addProductToCart(String productName) {
		By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
		WebElement product = driver.findElement(byProductName);
		String productId = getProductId(product);

		if (product.isDisplayed()) {
			By byBtnAddCart = By.xpath("//button[@type='button' and contains(@onclick, '" + productId + "')]");
			WebElement buttonAddProductCart = driver.findElement(byBtnAddCart);
			buttonAddProductCart.click();
		} else {
			Assert.fail("El producto no fue encontrado");
		}
	}

	public void addProductToWishList(String productName) {
		By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
		WebElement product = driver.findElement(byProductName);
		String productId = getProductId(product);

		if (product.isDisplayed()) {
			By byBtnAddWishList = By.xpath("//button[@type='button' and contains(@onclick, '" + productId
					+ "')][@data-original-title='Add to Wish List']");
			WebElement buttonAdd = driver.findElement(byBtnAddWishList);
			buttonAdd.click();
		} else {
			Assert.fail("El producto no fue encontrado");
		}
	}

	public void addProductToCompare(String productName) {
		By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
		WebElement product = driver.findElement(byProductName);
		String productId = getProductId(product);

		if (product.isDisplayed()) {
			By byBtnAddCompare = By.xpath("//button[@type='button' and contains(@onclick, '" + productId
					+ "')][@data-original-title='Compare this Product']");
			WebElement buttonAdd = driver.findElement(byBtnAddCompare);
			buttonAdd.click();
		} else {
			Assert.fail("El producto no fue encontrado");
		}
	}

	// Get product id
	private String getProductId(WebElement product) {
		String id = product.getAttribute("href").split("product_id=", 2)[1];
		return id;
	}

	public void verifyProductToCart(String nameProduct) {
		By byAlertMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
		WebElement alertMessage = driver.findElement(byAlertMessage);
		Assert.assertEquals(
				alertMessage.getText().equals("Success: You have added " + nameProduct + " to your shopping cart!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyProductToWishList(String nameProduct) {
		By byAlertMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
		WebElement alertMessage = driver.findElement(byAlertMessage);
		Assert.assertEquals(
				alertMessage.getText().equals(
						"You must login or create an account to save " + nameProduct + " to your wish list!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyProductToCompare(String nameProduct) {
		By byAlertMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
		WebElement alertMessage = driver.findElement(byAlertMessage);
		Assert.assertEquals(
				alertMessage.getText()
						.equals("Success: You have added " + nameProduct + " to your product comparison!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

}