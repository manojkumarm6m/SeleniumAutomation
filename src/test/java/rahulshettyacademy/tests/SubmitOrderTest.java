package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String device) throws InterruptedException {
		String countryName = "India";
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		productCatalogue.addProductToCart(device);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(device);
		AssertJUnit.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.selectCountry(countryName);
		checkoutPage.clickPlaceOrderButton();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String message = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertEquals(message, "THANKYOU FOR THE ORDER.");
		Thread.sleep(5000);
	}

	@Test()
	public void OrderHistory() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("manojreddym18@gmail.com", "107Me13025@");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		AssertJUnit.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getsJsonDataMap(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");

		// Print to check the size and content of the data
		System.out.println("Data size: " + data.size());
		data.forEach(entry -> System.out.println(entry));

		// Dynamically create Object[][] based on the size of data
		Object[][] result = new Object[data.size()][];

		for (int i = 0; i < data.size(); i++) {
			result[i] = new Object[] { data.get(i).get("email"), data.get(i).get("password"),
					data.get(i).get("device") };
		}

		return result;
	}

}
