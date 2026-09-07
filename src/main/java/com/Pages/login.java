package com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.BasePage;

public class login extends BasePage{

	public login(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//input[@name='identifier']")
	WebElement emailinput;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement getotpBtn;
	
	@FindBy(xpath = "//a[starts-with(text(),'Terms &')]")
	WebElement terms;
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement toastMessage;
	
	@FindBy(xpath = "//div[contains(@class,'flex items-start')]//p")
	WebElement invalidemailmsg;
	
	@FindBy(id = "acceptTerms")
	WebElement clickTerms;
	
	
	public boolean isEmailInputDisplayed() {
//		return emailinput.isDisplayed();
		waitforVisibilityofElement(emailinput);
		return isDisplayed(emailinput);
	}
	
	public void clearEmailInput() {
		waitforVisibilityofElement(emailinput);
		clearInput(emailinput);
	}
	
	public WebElement getGetOtpButton() {
		waitforVisibilityofElement(getotpBtn);
	    return getotpBtn;
	}
	
	public void enterEmail(String input) {
		waitforVisibilityofElement(emailinput);
		emailinput.sendKeys(input);
	}
	
	public void clickTandC(){
		waitforVisibilityofElement(clickTerms);
		Click(clickTerms);
	}
	
	public void clickOtpBtn() {
		waitforVisibilityofElement(getotpBtn);
		Click(getotpBtn);
	}
	
	public boolean isTermsDisplayed() {
		waitforVisibilityofElement(terms);
		return terms.isDisplayed();
	}
	
	
	public void clickTermsandCondition() {
		waitforVisibilityofElement(terms);
		Click(terms);
	}
	
	
	public String getToastMessage() {
		waitforVisibilityofElement(toastMessage);
	    return toastMessage.getText();
	}
	
	public void enterMobile(String mob) {
		waitforVisibilityofElement(emailinput);
		emailinput.sendKeys(mob);
	}
	
	
	public String isInvalidEmailValidationMsgDisplayed() {
		waitforVisibilityofElement(invalidemailmsg);
		System.out.println(invalidemailmsg.getText());
		return invalidemailmsg.getText();
		
	}
	
}
