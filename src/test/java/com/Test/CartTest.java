package com.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.checkerframework.checker.units.qual.t;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.BaseTest;
import com.Pages.VerifyOtp;
import com.Pages.cart;
import com.Pages.dashboard;
import com.Pages.discover;
import com.Pages.login;
import com.Pages.myHoldings;
import com.database.db;
import com.util.ReadProperties;
import com.util.RetryAnalyzer;

public class CartTest extends BaseTest{
	

	login log;
	VerifyOtp vOtp;
	db databse;
	dashboard disc;
	discover discoverpage;
	myHoldings holdings;
	cart cartpage;
	
	
	
	@BeforeClass
	
	public void setUp() throws IOException {
		
	launchBrowser();

	log = new login(driver);
	vOtp = new VerifyOtp(driver);
	databse = new db();
	disc = new dashboard(driver);
	discoverpage = new discover(driver);
	holdings = new myHoldings(driver);
	cartpage = new cart(driver);
	
	}
	
	@AfterClass
    public void tearDown() {

//   	     closeBrowser();
    }
	

	@Test(priority = 1, description = "Verify User logged in using Valid details", groups ="Login")
	public void loginwithvalidotp() throws SQLException, IOException {
		String Email = ReadProperties.getProperties("email");
		log.enterEmail(Email);
		log.clickTandC();
		log.clickOtpBtn();
		String otp = databse.getOtp();
		vOtp.enterOtp(otp);
		vOtp.clickVerfiyOtp();
	}
	
	@Test(priority = 2,description = "verify if user Can Go to Cart Page", retryAnalyzer = RetryAnalyzer.class)
	public void goToCart() {
		cartpage.clickCart();
	
	}
	
	
	@Test(priority = 3,description = "Stock added to cart with selected quantity,price calculates correct Total Value",retryAnalyzer = RetryAnalyzer.class)
	public void validateStockInvestmentDetails() {
		
		Assert.assertTrue(cartpage.verifyStockInvestment(),"Stock investment calculation is incorrect");
	}
	

}
