package com.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Base.BaseTest;
import com.Pages.VerifyOtp;
import com.Pages.dashboard;
import com.Pages.discover;
import com.Pages.login;
import com.Pages.myHoldings;
import com.database.db;
import com.util.ReadProperties;

public class HoldingTest extends BaseTest{
	
	login log;
	VerifyOtp vOtp;
	db databse;
	dashboard disc;
	discover discoverpage;
	myHoldings holdings;
	
	
	
	@BeforeClass
	
	public void setUp() throws IOException {
		
	launchBrowser();

	log = new login(driver);
	vOtp = new VerifyOtp(driver);
	databse = new db();
	disc = new dashboard(driver);
	discoverpage = new discover(driver);
	holdings = new myHoldings(driver);
	
	}
	
	@AfterClass
    public void tearDown() {

        closeBrowser();
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
	
	@Test(priority = 2, description = "verifyHighestInvestmentCalculation")
	public void verifyHighestInvestmentCalculation() {
		
		holdings.clickHoldings();
		Assert.assertEquals(holdings.getHighestInvestedValue(),holdings.getUIHoldingValue());
		
	}
	
	@Test(priority = 2, description = "verifyTotalInvestment")
	public void verifyTotalInvestedValue() {
		holdings.clickHoldings();
		Assert.assertEquals(holdings.getTotalInvestedvalue(),holdings.getUITotalvalue());
	}
	
	@Test(priority = 2, description = "verifyTotalInvestment")
	public void verifyLowestInvestedValue() {
		holdings.clickHoldings();
		Assert.assertEquals(holdings.getLowestInvestedValue(),holdings.getUILowestValue());
	}
	
	@Test(priority = 2, description = "verifyTotalInvestment")
	public void verifyAverageInvestedValue() {
		holdings.clickHoldings();
		Assert.assertEquals(holdings.getAverageValue(),holdings.getUIAverageValue(),0.01);
	}
	
}
