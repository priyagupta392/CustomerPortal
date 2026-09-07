package com.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.BasePage;

public class cart extends BasePage{
	
	public cart(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//a[@routerlink='/cart']")
	WebElement cartIcon;
	
	@FindBy(xpath = "//div[@class='cart-item-card']")
	List<WebElement> cartDetails;
	
	@FindBy(xpath = "//div[@class='cart-item-card']//h3")
	WebElement stockName;
	
	@FindBy(xpath = "//p[@class='cart-share-price']")
	WebElement stockPrice;
	
	@FindBy(xpath = "//input[@type='number']")
	WebElement stockQty;
	
	@FindBy(xpath = "//div[@class='cart-item-subtotal']//strong")
	WebElement stockTotalPrice;
	
	@FindBy(xpath = "//button[@class='cart-item-delete']")
	WebElement deleteIcon;
	
	@FindBy(xpath = "//div[contains(@class,'justify-between')]//span[2]")
	WebElement totalInvestments;
	
	
	public void clickCart() {
		waitforVisibilityofElement(cartIcon);
		Click(cartIcon);
	}
	
	public boolean verifyStockInvestment() {
		waitforListofElement(cartDetails);
		
		double expectedGrandTotal = 0;
	    double uiGrandTotal = 0;
		
		for(WebElement cards : cartDetails) {
			
			WebElement stockNames = cards.findElement(By.xpath(".//h3"));
			WebElement stockPrices = cards.findElement(By.xpath(".//p[@class='cart-share-price']"));
			WebElement stockQtys = cards.findElement(By.xpath(".//input[@type='number']"));
			WebElement stockTotalprice = cards.findElement(By.xpath(".//div[@class='cart-item-subtotal']//strong"));
			
			
			String Stckname = stockNames.getText();
			String[] stckprice = stockPrices.getText().split("/");
			String stkprice = stckprice[0].replace("₹","").replace(",","").trim();
			
			String stckQty = stockQtys.getAttribute("value");
			String stckttlprice = stockTotalprice.getText().replace("₹","").replace(",","").trim();
			
			double price = Double.parseDouble(stkprice);
			int qty = Integer.parseInt(stckQty);
	        double uiTotal = Double.parseDouble(stckttlprice);
			double expectedTotal  = price*qty;
			
			
			expectedGrandTotal += expectedTotal ;
			uiGrandTotal += uiTotal;
			
			System.out.println("Stock : " + Stckname);
	        System.out.println("Expected : " + expectedTotal);
	        System.out.println("UI : " + uiTotal);
	        
	        
	        if (Math.abs(expectedTotal - uiTotal) > 0.01) {
	            return false;
	        }
		}
	        
	        System.out.println("Expected Grand Total : " + expectedGrandTotal);
	        System.out.println("UI Grand Total       : " + uiGrandTotal);
	    
		
		if(Math.abs(expectedGrandTotal-uiGrandTotal)>0.01) {
			return false;
		}
		
		 double totalInvestment = gettotalInvestAmount();

		    System.out.println("Total Investment UI  : " + totalInvestment);

		    if (Math.abs(expectedGrandTotal - totalInvestment) > 0.01) {
		        return false;
		    }


	    return true;
	}
	
	
	
	public double gettotalInvestAmount() {
		String total = totalInvestments.getText().replace("₹","").replace(",","").trim();
		double amount = Double.parseDouble(total);
		return amount;
	}

	
	
}
