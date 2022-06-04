package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Product {
	// Product all detail
	By byProductDetail = By.xpath("//div[@class='caption']");
	// Message alert
	By byAlertMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");

	private AdapterSelenium adapter;

	public Product(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void validateProducto(String description, boolean expectedResult) {
		boolean isEquals = false;
		List<WebElement> listDescriptions = adapter.getListElements(byProductDetail);
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
		try {
			By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
			String productId = getProductId(byProductName);

			if (adapter.isElementExisting(byProductName)) {
				By byBtnAddCart = By.xpath("//button[@type='button' and contains(@onclick, '" + productId + "')]");
				adapter.clickElement(byBtnAddCart);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("El producto: " + productName + " no fue encontrado");
		}
	}

	public void addProductToWishList(String productName) {
		try {
			By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
			String productId = getProductId(byProductName);

			if (adapter.isElementExisting(byProductName)) {
				By byBtnAddWishList = By.xpath("//button[@type='button' and contains(@onclick, '" + productId
						+ "')][@data-original-title='Add to Wish List']");
				adapter.clickElement(byBtnAddWishList);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("El producto: " + productName + " no fue encontrado");
		}

	}

	public void addProductToCompare(String productName) {
		try {
			By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
			String productId = getProductId(byProductName);

			if (adapter.isElementExisting(byProductName)) {
				By byBtnAddCompare = By.xpath("//button[@type='button' and contains(@onclick, '" + productId
						+ "')][@data-original-title='Compare this Product']");
				adapter.clickElement(byBtnAddCompare);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("El producto: " + productName + " no fue encontrado");
		}
	}
	
	public void openDetailProduct(String productName) {
		try {
			By byProductName = By.xpath("//div[@class='caption']//a[contains(text(), '" + productName + "')]");
			adapter.clickElement(byProductName);
		} catch (NoSuchElementException e) {
			System.out.println(e);
			Assert.fail("El producto: " + productName + " no fue encontrado");
		}
	}

	// Get product id
	private String getProductId(By byProductName) {
		return adapter.getAttribute(byProductName, "href").split("product_id=", 2)[1];
	}

	public void verifyProductToCart(String nameProduct) {
		Assert.assertEquals(
				adapter.getText(byAlertMessage)
						.equals("Success: You have added " + nameProduct + " to your shopping cart!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyProductToWishList(String nameProduct) {
		Assert.assertEquals(
				adapter.getText(byAlertMessage).equals(
						"You must login or create an account to save " + nameProduct + " to your wish list!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void verifyProductToCompare(String nameProduct) {
		Assert.assertEquals(
				adapter.getText(byAlertMessage)
						.equals("Success: You have added " + nameProduct + " to your product comparison!\n×"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

}