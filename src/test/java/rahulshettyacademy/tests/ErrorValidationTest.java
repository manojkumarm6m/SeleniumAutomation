package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginError() throws IOException, InterruptedException {
		landingPage.loginApplication("manojreddym18@gmail.com", "10jdvhidk");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication("manojreddym18@gmail.com", "107Me13025@");
		productCatalogue.addProductToCart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay(productName + 3);
		Assert.assertFalse(match);
	}

}
