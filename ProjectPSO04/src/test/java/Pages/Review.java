package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import Adapter.AdapterSelenium;

public class Review {
	// Tabs
	By byTabDescription = By.xpath("//a[@href='#tab-description']");
	By byTabReview = By.xpath("//a[@href='#tab-review']");
	// Inputs
	By byName = By.id("input-name");
	By byReview = By.id("input-review");
	By byBtnContinue = By.id("button-review");
	// Message status review
	By byMessageStatusErrorReview = By.xpath("//div[@class='alert alert-danger alert-dismissible']"); 
	By byMessageStatusSuccessReview = By.xpath("//div[@class='alert alert-success alert-dismissible']"); 

	private AdapterSelenium adapter;

	public Review(String browser, String driverPath) {
		adapter = AdapterSelenium.getAdapter(browser, driverPath);
	}

	public void openReviews() {
		adapter.clickElement(byTabReview);
	}

	public void enterYourName(String name) {
		adapter.enterText(byName, name);
	}

	public void enterReview(String review) {
		adapter.enterText(byReview, review);
	}

	/**
	 * @param rating | value 1 - 5
	 */
	public void enterRating(String rating) {
		By byRating = By.xpath("//input[@name='rating'][@value='" + rating + "']");

		adapter.clickElement(byRating);
	}

	public void clickContinue() {
		adapter.clickElement(byBtnContinue);
	}

	public void validateSubmittedReview() {
		Assert.assertEquals(
				adapter.getText(byMessageStatusSuccessReview)
						.equals("Thank you for your review. It has been submitted to the webmaster for approval."),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void validateSelectRating() {
		Assert.assertEquals(adapter.getText(byMessageStatusErrorReview).equals("Warning: Please select a review rating!"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void validateLengthReview() {
		Assert.assertEquals(
				adapter.getText(byMessageStatusErrorReview)
						.equals("Warning: Review Text must be between 25 and 1000 characters!"),
				true, "El mensaje de alerta no se desplego correctamente");
	}

	public void validateLengthReviewName() {
		Assert.assertEquals(
				adapter.getText(byMessageStatusErrorReview)
						.equals("Warning: Review Name must be between 3 and 25 characters!"),
				true, "El mensaje de alerta no se desplego correctamente");
	}
}
