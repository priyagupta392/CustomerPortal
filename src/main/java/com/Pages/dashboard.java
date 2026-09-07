package com.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.junit.ArrayAsserts;

import com.Base.BasePage;
import com.util.ReadProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dashboard extends BasePage {
	
	public dashboard(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='relative']")
	WebElement initials;
	
	@FindBy(xpath = "//*[@role='menuitem']")
	List<WebElement> profileList;
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement toastMessage;
	
	@FindBy(xpath = "//button[@role='menuitem' and contains(normalize-space(), 'Profile')]")
	WebElement Profileoption;
	
	@FindBy(xpath = "//button[@role='menuitem' and contains(normalize-space(), 'About us')]")
	WebElement Aboutusoption;
	
	@FindBy(xpath = "//*[@role='menuitem' and contains(normalize-space(), 'View Sold Stock')]")
	WebElement viewSoldStockOpn;
	
	@FindBy(xpath = "//*[@role='menuitem' and contains(normalize-space(), 'FAQs')]")
	WebElement FaQsopn;
	
	@FindBy(xpath = "//*[@role='menuitem' and contains(normalize-space(), 'Contact us')]")
	WebElement contactOpn;
	
	@FindBy(xpath = "//*[@role='menuitem' and contains(normalize-space(), 'Log Out')]")
	WebElement logoutOpn;
	
	@FindBy(xpath = "//section[@class=\"support-card\"]")
	WebElement contactUsSection;
	
	@FindBy(xpath = "//button[@class='close-btn']")
	WebElement closeBtn;
	
	public String userInitials() {
		waitForInvisibilityOfElement(toastMessage);
		waitforVisibilityofElement(initials);
		return initials.getText();
		
	}
	
	public void clickProfileIcone() {
//				waitforVisibilityofElement(initials);
		Click(initials);
//		System.out.println("Profile icon clicked");
   
	}

	
	public void printListOfItemsinProfile() {
		waitforListofElement(profileList);
		System.out.println("Profile menu is visible");
		for(WebElement items:profileList) {
		String text = items.getText().trim();
        String[] lines = text.split("\\r?\\n");
        System.out.println(lines[lines.length - 1].trim());
		}
	}
	
	public boolean verifyProfileItems() throws IOException {
		
		String expectedMenu = ReadProperties.getProperties("profile.menu.items");

		List<String> expectedItems = Arrays.asList(expectedMenu.split(","));
		List<String> actualItems = new ArrayList();
		
		for(WebElement items : profileList) {
//			actualItems.add(items.getText().trim());
			
			String text = items.getText().trim();
	        String[] lines = text.split("\\r?\\n");
	        String menuText = lines[lines.length - 1].trim();
	        actualItems.add(menuText);
		}
		
		return actualItems.containsAll(expectedItems);
		
	}
	
	
	public void clickProfile() {
		hover(Profileoption);
		Click(Profileoption);
		
			}
	
	public void clickAboutUs() {
		hover(Aboutusoption);
		Click(Aboutusoption);
		
	}
	
	public void clickViewSoldStck() {
		hover(viewSoldStockOpn);
		Click(viewSoldStockOpn);
	}
	
	public void clickFAQs() {
		hover(FaQsopn);
		Click(FaQsopn);
	}
	
	public void clickContact() {
		hover(contactOpn);
		Click(contactOpn);
	}
	
	public void clickLogout() {
		hover(logoutOpn);
		Click(logoutOpn);
	}
	
	
	public boolean sectionContact() {
		waitforVisibilityofElement(contactUsSection);
		return isDisplayed(contactUsSection);
		
	}
	
	
	public void closeButton() {
		waitforVisibilityofElement(closeBtn);
		Click(closeBtn);
	}
}
