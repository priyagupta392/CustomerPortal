package com.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.BasePage;

public class myHoldings extends BasePage {

	public myHoldings(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'My Holdings')]")
	WebElement holdings;

	@FindBy(xpath = "//div[contains(@class,'summary-card-highest')]//strong")
	WebElement highestHolding;

	@FindBy(xpath = "//span[@class='invested-cell']")
	List<WebElement> investedValue;

	@FindBy(xpath = "//div[contains(@class,'summary-card-primary')]//strong")
	WebElement totalValue;
	
	@FindBy(xpath = "//div[contains(@class,'summary-card-lowest')]//strong")
	WebElement lowestValue;
	
	@FindBy(xpath = "//div[contains(@class,'summary-card-average')]//strong")
	WebElement averageValue;

	public void clickHoldings() {
		waitforVisibilityofElement(holdings);
		Click(holdings);
	}

	public double getUIHoldingValue() {
		waitforVisibilityofElement(highestHolding);
		String value = highestHolding.getText().replace("₹", "").replace(",", "").trim();
		double uiValue = Double.parseDouble(value);
		System.out.println("UI highest value:" + uiValue);
		return uiValue;
	}

	public double getUITotalvalue() {
		waitforVisibilityofElement(totalValue);
		String value = totalValue.getText().replace("₹", "").replace(",", "").trim();
		double uiTotal = Double.parseDouble(value);
		System.out.println("UI Total Value:" + uiTotal);

		return uiTotal;
	}
	
	public double getUILowestValue() {
		waitforVisibilityofElement(lowestValue);
		String value = lowestValue.getText().replace("₹", "").replace(",", "").trim();
		double uiLowest = Double.parseDouble(value);
		System.out.println("Lowest Value: " +uiLowest);
		return uiLowest;
	}
	
	public double getUIAverageValue() {
		waitforVisibilityofElement(averageValue);
		String value = averageValue.getText().replace("₹", "").replace(",", "").trim();
		double uiAvg = Double.parseDouble(value);
		System.out.println("Lowest Value: " +uiAvg);
		return uiAvg;
	}
	
	public double getAverageValue() {

	    return getTotalInvestedvalue() / investedValue.size();
	}

	public double getTotalInvestedvalue() {

		waitforListofElement(investedValue);
		if (investedValue.isEmpty()) {
			throw new RuntimeException("No invested values found on the page");
		}

		double total = 0;
		for (WebElement element : investedValue) {
			String value = element.getText().replace("₹", "").replace(",", "").trim();
			double price = Double.parseDouble(value);
			total = total + price;
			

		}
		System.out.println("Total Value :" + total);
		return total;
	}

	public double getLowestInvestedValue() {
		waitforListofElement(investedValue);

		if (investedValue.isEmpty()) {
			throw new RuntimeException("No invested Value found on the page");
		}

		double lowest = Double.MAX_VALUE;

		for (WebElement element : investedValue) {
			String value = element.getText().replace("₹", "").replace(",", "").trim();
			double price = Double.parseDouble(value);

			if (price < lowest) {
				lowest = price;

				
			}

		}
		System.out.println("Lowest value is:" + lowest);
		return lowest;
	}

	public double getHighestInvestedValue() {

		waitforListofElement(investedValue);

		System.out.println("Number of invested values: " + investedValue.size());

		if (investedValue.isEmpty()) {
			throw new RuntimeException("No invested values found on the page");
		}

		double highest = Double.NEGATIVE_INFINITY;

		for (WebElement element : investedValue) {
			String value = element.getText().trim().replace("₹", "").replace(",", "");


			double price = Double.parseDouble(value);

			if (price > highest) {
				highest = price;
			}
		}

		System.out.println("Data highest:" + highest);
		return highest;
	}
}
