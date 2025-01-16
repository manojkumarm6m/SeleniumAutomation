package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {




	@Test
	public void submitOrder() throws IOException {
		String productName = "ZARA COAT 3";
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication("manojreddym18@gmail.com", "107Me13025@");
		productCatalogue.addProductToCart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.checkout();

		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.clickPlaceOrderButton();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.", "Order confirmation message mismatch");
	}
}
