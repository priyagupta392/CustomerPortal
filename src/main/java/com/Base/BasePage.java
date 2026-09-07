package com.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.ReadProperties;

public class BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;
	
	public BasePage(WebDriver driver) {
		
		this.driver=driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		this.js= (JavascriptExecutor)driver;
		action = new Actions(driver);
	}
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement toastMessage;
	
		
	public void Click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void Click(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public String getToastMessage() {
		waitforVisibilityofElement(toastMessage);
	    return toastMessage.getText();
	}
	
	public boolean isDisplayed(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public void clearInput(WebElement element) {
		element.clear();
	}
	
	public void waitforVisibilityofElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitforVisibilityofElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitforListofElement(List<WebElement> element) {
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void javascriptclick(WebElement element) {
		
		js.executeScript("arguments[0].click();", element);
	}
	
	public void waitForInvisibilityOfElement(WebElement element) {
	    wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	protected String getRegisteredEmail() throws IOException {
	    return ReadProperties.getProperties("email");
	}
	
	public void hover(WebElement element) {
		waitforVisibilityofElement(element);
		action.moveToElement(element).perform();
	}
	
	public String switchToNewTab(String parentwindow) {
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		
		Set<String> windows = driver.getWindowHandles();
		
		for(String window : windows) {
			if(!window.equals(parentwindow)) {
				driver.switchTo().window(window);
				return window;
			}
		}
		return null;
	}
}
