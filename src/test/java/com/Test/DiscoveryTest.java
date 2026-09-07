package com.Test;

import static org.testng.Assert.assertTrue;

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
import com.database.db;
import com.util.ReadProperties;
import com.util.RetryAnalyzer;
import com.util.dataProvider;

public class DiscoveryTest extends BaseTest{

	login log;
	VerifyOtp vOtp;
	db databse;
	dashboard disc;
	discover discoverpage;
	
	
	
	@BeforeClass
	
	public void setUp() throws IOException {
		
	launchBrowser();

	log = new login(driver);
	vOtp = new VerifyOtp(driver);
	databse = new db();
	disc = new dashboard(driver);
	discoverpage = new discover(driver);
	
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
	
	@Test(priority = 2,description = "Verify all sector filter buttons are displayed", dependsOnMethods = "loginwithvalidotp")
	public void isSectorFilterDisplayed() {
		
		Assert.assertTrue(discoverpage.isAllSectorsDisplayed(),"Sector list is not displayed");
			}
	
	@Test(priority = 5,description = "Verify company search by full company name, partial name", dataProviderClass = dataProvider.class, dataProvider = "companysearchcard", dependsOnMethods = "loginwithvalidotp")
	public void testSearchFucntionality(String searchtext, String expectedName) {
		discoverpage.searchCompany(searchtext);
		assertTrue(discoverpage.isSearchedCompanyDisplayed(expectedName), "Expected company '" + expectedName +  "' was not displayed for search '" + searchtext + "'");
	}
	
	@Test(priority = 4, description = "Verify Invalid Search response")
	public void VerifyInvalidData() throws IOException {
		String searchtext = ReadProperties.getProperties("invalidcompName");
		discoverpage.searchCompany(searchtext);
		Assert.assertTrue(discoverpage.isNoDataTextDisplayed());
	}
	
	
	@Test(priority = 3 , description = "Verify company name, Sector, stock Price is displayed")
	public void VerfiyCardDetails() {
		
		Assert.assertTrue(discoverpage.isCompanyCardDisplaysAllRecord(), "Company Card Details are Missing");
		
	}
	
	@Test(priority=6, description = "Verify If Add OR Minus Delimeter are functioning", retryAnalyzer = RetryAnalyzer.class )
	public void AddOrRemoveStockQtyUsingIcon() throws IOException {
		
		String company = ReadProperties.getProperties("company");
		discoverpage.searchCompany(company);
		discoverpage.addStockUsingIcon();
		discoverpage.decreaseStockQtyusinIcon();
	}
	
	@Test(priority = 7, description = "Verify Add to Cart adds selected stock")
	public void EnterStockQtyAndAddtoCart() throws IOException {
		String Qty = ReadProperties.getProperties("StockQty");
		discoverpage.enterStckQty(Qty);
		discoverpage.addToCart();
	}
}
