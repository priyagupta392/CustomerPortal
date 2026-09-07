package com.Pages;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Base.BasePage;

public class discover extends BasePage{

	public discover(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='sector-filter-control']")
	WebElement filterValues;
	
	@FindBy(xpath = "//input[@placeholder='Search companies...']")
	WebElement searchcomp;
	
	@FindBy(xpath = "//div[contains(@class,'sector-filter-scroll')]//button")
	WebElement sectorList;
	
	@FindBy(xpath = "//div[contains(@class,'rounded-2xl')]")
	List<WebElement> companyCard;
	
	@FindBy(xpath = "//div[contains(@class,'min-w-0')]//p")
	WebElement companysector;
	
	@FindBy(xpath = "//div[contains(@class,'min-w-0')]//h3")
	WebElement companyName;
	
	@FindBy(xpath = "//div[contains(@class,'py-4')]//span[contains(@class,'text-xl')]")
	WebElement stockPrice;
	
	@FindBy(xpath = "//span[starts-with(text(),'remove')]")
	WebElement minusQty;
	
	@FindBy(xpath = "//span[starts-with(text(),'add')]")
	WebElement addQty;
	
	@FindBy(xpath = "//input[@inputmode='numeric']")
	WebElement enterQty;
	
	@FindBy(xpath = "//p[contains(text(),'No Data Found')]")
	WebElement invalidText;
	
	@FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
	WebElement addtocart;
	
	
	public boolean isAllSectorsDisplayed() {
		return isDisplayed(sectorList);
	}

	
	public void searchCompany(String searchText) {
		searchcomp.clear();
		searchcomp.sendKeys(searchText);
	}
	
	public boolean isSearchedCompanyDisplayed(String compName) {
		
		By companyNameLocator = By.xpath("//h3[@title='" + compName + "']");
		
							
		    
			WebElement company = waitforVisibilityofElement(companyNameLocator);
		System.out.println("Search text: " + compName);
		System.out.println( "Company displayed: " + company.getText());
		
		return isDisplayed(company);
		
		
	}
	
	public boolean isNoDataTextDisplayed() {
		searchcomp.clear();
	return isDisplayed(invalidText);	
	}
	
	public boolean isCompanyCardDisplaysAllRecord() {
		
		waitforListofElement(companyCard);
	    System.out.println("Number of company cards: " + companyCard.size());
 
	    if (companyCard.isEmpty()) {
	        System.out.println("No company cards found!");
	        return false;
	    }
	    boolean allCardsValid = true;
		for(WebElement cards : companyCard) {
			
			WebElement companyName = cards.findElement(By.xpath(".//div[contains(@class,'min-w-0')]//h3"));
		    WebElement stockPrice = cards.findElement(By.xpath(".//div[contains(@class,'py-4')]//span[contains(@class,'text-xl')]"));
		    WebElement companySector = cards.findElement(By.xpath(".//div[contains(@class,'min-w-0')]//p")); 
			
			  // Get card details
	        String companyNameText = companyName.getText().trim();
	        String priceText = stockPrice.getText().trim();
	        String sectorText = companysector.getText().trim();

	        // Print card details
	        System.out.println("Company Name : " + companyNameText);
	        System.out.println("Price        : " + priceText);
	        System.out.println("Sector       : " + sectorText);
	        System.out.println("--------------------------------");
	        if (!companyName.isDisplayed() || companyNameText.isEmpty()) {
	            System.out.println("❌ Company Name missing");
	            allCardsValid = false;
	        }

	        if (!stockPrice.isDisplayed() || priceText.isEmpty()) {
	            System.out.println("❌ Stock Price missing");
	            allCardsValid = false;
	        }

	        if (!companySector.isDisplayed() || sectorText.isEmpty()) {
	            System.out.println("❌ Sector missing for: " + companyNameText);
	            allCardsValid = false;
	        }
		}
		return allCardsValid;
		
	}
	
	
	public void addStockUsingIcon() {
		
		
//		WebElement firstcard = companyCard.get(0);
//		WebElement firstaddbutton = firstcard.findElement(By.xpath(".//span[starts-with(text(),'add')]"));	
//		for(int i = 0;i<50;i++) {	
//		waitforVisibilityofElement(firstaddbutton);
//		hover(firstaddbutton);
//		Click(firstaddbutton);	
//		System.out.println("clicked times" +(i+1));
			By addButton = By.xpath(
			        "(//div[contains(@class,'rounded-2xl')])[1]" +
			        "//button[.//span[normalize-space()='add']]"
			    );

			   
				
			    for (int i = 0; i < 50; i++) {
			    	Click(addButton);
			        
			    }
			
		
		
		
	}
	
	public void decreaseStockQtyusinIcon() {
		System.out.println("Decreasing Stock Qty");
		for(int i =0;i<15;i++) {
		WebElement firstcard = companyCard.get(0);
		WebElement firstremovebtn = firstcard.findElement(By.xpath(".//span[starts-with(text(),'remove')]"));
		waitforVisibilityofElement(firstremovebtn);
		hover(firstremovebtn);
		Click(firstremovebtn);
		}
		
	}
	
	public void enterStckQty(String qty) {
		waitforVisibilityofElement(enterQty);
		enterQty.clear();
		enterQty.sendKeys(qty);
	}
	
	
	public void addToCart() {
		waitforVisibilityofElement(addtocart);
		Click(addtocart);
		System.out.println("Stock Added to Cart are: " +getToastMessage());
	}
		
}
