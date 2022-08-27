package org.evs.vtiger.beta.utils;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtil {

	public  WebDriver driver;
	public WebDriverWait expWait;

	/*
	//if I don't have Static variable of WebDriver driver

	public WebDriver launchBroWser() { 

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	 */

	/*Description-For Browser Launching
	 * Parameter Type-String
	 * Work-It will Launch Browser And Delete All Cookies And also provide implicitly wait
	 * Return Value Purpose-.....
	 */

	public void launchBroWser(String browserType,String browserName) {
		if(browserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver=new ChromeDriver();
			System.out.println(browserName+" has been Launched");
		}else if(browserType.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver=new ChromeDriver();
			System.out.println(browserName+" has been Launched");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void visibilityOfElementLocated(By xpath) {
		expWait=new WebDriverWait(driver, Duration.ofSeconds(5));
		expWait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}

	/*Purpose-For Hitting URL
	 * Parameter Type-String
	 * Work-It will Hit the URL
	 * Return Value Purpose-.....
	 */
	public void url(String inputUrl) {
		driver.get(inputUrl);
	}
	/*Purpose-For Hitting URL
	 * Parameter Type-String
	 * Work-It will Hit the URL
	 * Return Value Purpose-.....
	 */
	public void navigateUrl(String inputUrl) {
		driver.navigate().to(inputUrl);
	}

	/*Purpose-For Clicking
	 * Parameter Type-WebElement
	 * Work-It will click on element
	 * Return Value Purpose-.....
	 */
	public void click(String locator,String locatorType,String ElementName) {
		WebElement we=	getWebElement(locator, locatorType);
		try {
			we.click();
			System.out.println("clicked on "+ElementName);
		}catch(ElementClickInterceptedException e) {
			try {
				Actions ac=new Actions(driver);
				ac.click().build().perform();
			}catch(ElementNotInteractableException f) {
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", we);
			}
		}catch(ElementNotInteractableException e) {
			jsClick(locator,locatorType);
		}
	}

	public void staleElementRefrenseException(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		String str=	we.toString();
		String arr[]=str.split(":");
		str=arr[arr.length-1];
		String freshXpath=str.substring(0, str.length()-1).trim();
		we=driver.findElement(By.xpath(freshXpath));
	}


	/*Purpose-For Sending Massage
	 * Parameter Type-WebElement,String
	 * Work-It will Send Massage on any EditBox
	 * Return Value Purpose-.....
	 */
	public void setTextboxValue(String locator,String locatorType,String input,String ElementName) {
		WebElement we=	getWebElement(locator, locatorType);

		try {
			we.click();
			we.clear();
			we.sendKeys(input);
			System.out.println("input on "+ElementName);
		}catch(ElementNotInteractableException e) {
			try {
				Actions ac=new Actions(driver);
				ac.sendKeys(we, input);
			}catch(Exception f) {
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].values='"+input+"'", we);
			}
		}
	}
	/*Purpose-For Selecting Dropdown
	 * Parameter Type-WebElement,String
	 * Work-It will Select Dropdown options by visibleText Or Value
	 * Return Value Purpose-.....
	 */
	public void selectByVisibleTextOrValue(String locator, String locatorType,String visibleTextOrValue,String ElementName) {
		WebElement we=getWebElement(locator,locatorType);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		try {
			Select sc=new Select(we);
			sc.selectByVisibleText(visibleTextOrValue);
			System.out.println(ElementName+"is selected");
		}catch(NoSuchElementException e) {
			Select sc=new Select(we);
			sc.selectByValue(visibleTextOrValue);	
			System.out.println(ElementName+"is selected");
		}
	}

	//	/*Purpose-For Selecting Dropdown
	//	 * Parameter Type-WebElement,String
	//	 * Work-It will Select Dropdown options by valueAttribute
	//	 * Return Value Purpose-.....
	//	 */
	//	public void selectByValue(String locator,String value) {
	//		Select sc=new Select(we);
	//		sc.selectByValue(value);	
	//	}


	/*Purpose-For Selecting Dropdown
	 * Parameter Type-WebElement,int
	 * Work-It will Select Dropdown options by indexNo.
	 * Return Value Purpose-.....
	 */
	public void selectByIndex(String locator, String locatorType,int index,String alphagrey,String ElementName) {
		WebElement we=getWebElement(locator,locatorType);
		Select sc=new Select(we);
		sc.selectByIndex(index);	
		System.out.println(ElementName+" is selected");
	}

	/*Purpose-For mouse hover-over
	 * Parameter Type-WebElement
	 * Work-Mouse will move on given Element
	 * Return Value Purpose-.....
	 */
	public void moveToElement(String locator, String locatorType,String ElementName) {
		WebElement we=getWebElement(locator,locatorType);
		Actions ac=new Actions(driver);
		ac.moveToElement(we).build().perform();
		System.out.println("mouse is moved on "+ElementName);
	}

	/*Purpose-For RightClick
	 * Parameter Type-WebElement
	 * Work-Right Click on element
	 * Return Value Purpose-.....
	 */
	public void rightClickByWebElement(String locator, String locatorType,String ElementName) {
		WebElement we=getWebElement(locator,locatorType);
		Actions ac=new Actions(driver);
		ac.contextClick(we).build().perform();
		System.out.println("Right click on "+ElementName);
	}

	/*Purpose-For Window Handle By title
	 * Parameter Type-String
	 * Work-If our window focus change then we move focus on that window Where we work
	 * Return Value Purpose-.....
	 */
	public void windowHandlesByTitle(String expectedTitle) {
		Set<String> handleValues=driver.getWindowHandles();
		Iterator<String> values=	handleValues.iterator();
		while(values.hasNext()) {
			String val=values.next();
			driver.switchTo().window(val);
			String actualTitle=	driver.getTitle();
			if(actualTitle.contains(expectedTitle)) {
				break;
			}
		}
	}

	/*Purpose-For Window Handle By URL
	 * Parameter Type-String
	 * Work-If our window focus change then we move focus on that window Where we work
	 * Return Value Purpose-.....
	 */
	public void windowHandlesByURL(String expectedURL) {
		Set<String>handleValues= driver.getWindowHandles();
		for(String values:handleValues) {
			driver.switchTo().window(values);
			String actualUrl=	driver.getCurrentUrl();
			if(actualUrl.contains(expectedURL)) {
				break;
			}
		}

	}

	/*Purpose-For Handling Frame
	 * Parameter Type-String
	 * Work-By using this method we move on that frame Where Our Element Exist
	 * Return Value Purpose-.....
	 */
	public void frameHandleByNameOrId(String NameOrId) {
		driver.switchTo().frame(NameOrId);

	}

	/*Purpose-For Handling Frame
	 * Parameter Type-String
	 * Work-By using this method we move on that frame Where Our Element Exist
	 * Return Value Purpose-.....
	 */
	public void frameHandleByFrameXpath(String locator) {
		WebElement we=	getWebElement(locator, "name");
		driver.switchTo().frame(we);

	}

	/*Purpose-For Existing from frame
	 * Parameter Type-....
	 * Work-By using this method we exit from frame
	 * Return Value Purpose-.....
	 */
	public void frameExit() {
		driver.switchTo().defaultContent();
	}

	/*Purpose-For fetching the InnerText
	 * Parameter Type-WebElement
	 * Work-By using this method we will fetch the innerText of Any WebElement
	 * Return Value Purpose-It will return String type value.
	 */
	public String getText(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		String actualText=we.getText();
		return actualText;
	}

	/*Purpose-For fetching Title
	 * Parameter Type-....
	 * Work-By using this method we will fetch the title  of Any WebPage
	 * Return Value Purpose-It will return String type value.
	 */
	public String getTitle() {
		String actualTitle=driver.getTitle();
		return actualTitle;
	}
	
	public By getByObject(String locator, String locatorType) {
		  By byObj=null;
		if(locatorType.equalsIgnoreCase("xpath")) {
			byObj =	By.xpath(locator);
		}else if(locatorType.equalsIgnoreCase("name")) {
			byObj =	By.name(locator);
		}else if(locatorType.equalsIgnoreCase("id")) {
			byObj =	By.id(locator);
		}else if(locatorType.equalsIgnoreCase("linkText")) {
			byObj =	By.linkText(locator);
		}else if(locatorType.equalsIgnoreCase("class")) {
			byObj =	By.className(locator);
		}else if(locatorType.equalsIgnoreCase("css")) {
			byObj =	By.cssSelector(locator);
		}else {
			System.out.println("Locator Type is wrong. Locator Type-"+locatorType);
		}
		return byObj;
		
	}
	

	public WebElement getWebElement(String locator, String locatorType) {
		WebElement we=null;  ///  return 
		By byObj=getByObject(locator, locatorType);
		try {
			
			we=driver.findElement(byObj);
			
		}catch(NoSuchElementException e) {
			System.out.println(we);
          if(waitUntilElementIsVisible(byObj, 60)==true) {
        	  we=driver.findElement(byObj);
          }else {
        	  System.out.println(locator +" is not found after searching 90 seconds on the page");
              e.printStackTrace();
          }
         
		}

		return we;
	}

   public boolean waitUntilElementIsVisible(By byObj, int timeInSeconds) {
	   
	   try {
		  
		   WebDriverWait wt =  new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
	        wt.until(ExpectedConditions.visibilityOfElementLocated(byObj));
	        return true;
	   }catch(TimeoutException e) {
		   return false;
	   }
	  
   
   }
	
	

	/*Purpose-For fetching URL
	 * Parameter Type-....
	 * Work-By using this method we will fetch the URL  of Any WebPage
	 * Return Value Purpose-It will return String type value.
	 */
	public String getURL() {
		String actualUrl=	driver.getCurrentUrl();
		return actualUrl;
	}


	/*Purpose-For verifying Element is enable or not
	 * Parameter Type-WebElement
	 * Work-By using this method we will verify any element is enable or not.
	 * Return Value Purpose-It will return String type value.
	 */
	public boolean isEnable(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		boolean status=we.isEnabled();
		return status;
	}


	/*Purpose-For verifying Element is visible or not
	 * Parameter Type-WebElement
	 * Work-By using this method we will verify any element is visible or not.
	 * Return Value Purpose-It will return String type value.
	 */
	public boolean isDisplay(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		boolean status=	we.isDisplayed();
		return status;
	}

	/*Purpose-For verifying any radio button or cheakbox is selected or not
	 * Parameter Type-WebElement
	 * Work-By using this method we will verify any radio button or cheakbox is selected or not.
	 * Return Value Purpose-It will return String type value.
	 */
	public boolean isSelected(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		boolean status=	we.isSelected();
		return status;
	}
	
	public void moveToElementAndClick(String locator, String locatorType) {
		WebElement we=getWebElement(locator, locatorType);
		Actions ac=new Actions(driver);
		ac.moveToElement(we).click().build().perform();
		
		
	}

	/*Purpose-For clicking any element by using Actions Class
	 * Parameter Type-
	 * Work-By using this method we will click on any element.
	 * Return Value Purpose-
	 */
	public void click() {
		Actions ac=new Actions(driver);
		ac.click().build().perform();

	}
	public void jsClick(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", we);

	}
	public void jsSendkey(String locator, String locatorType,String inputdata) {
		WebElement we=getWebElement(locator,locatorType);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[].value='"+inputdata+"'", we);
	}
	public void scrolling(int x,int y) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}

	public void printAllOptions(String locator, String locatorType) {
		WebElement we=getWebElement(locator,locatorType);
		Select sc=new Select(we);
		List<WebElement>allOptions=	sc.getOptions();
		for(int i=0;i<allOptions.size();i++) {
			WebElement op=	allOptions.get(i);
			System.out.println(op.getText());
			System.out.println();
		}

	}
	public void printAllOptionsByWrappedElement(String locator, String locatorType) {
		WebElement we=	getWebElement(locator,locatorType);
		Select sc=new Select(we);
		WebElement we1=sc.getWrappedElement();
		System.out.println(we1.getText());
	}
	public void  WebDriverWaitElementToBeClickable(By locator,int time) {
		expWait=new WebDriverWait(driver,Duration.ofSeconds(time));
		expWait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void  WebDriverWaitElementDisable(By locator,int time) {
		expWait=new WebDriverWait(driver,Duration.ofSeconds(time));
		expWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
	}
	public void  WebDriverWaitTextChange(By locator,int time,String text) {
		expWait=new WebDriverWait(driver,Duration.ofSeconds(time));
		expWait.until(ExpectedConditions.textToBePresentInElementLocated(locator,text));
	}
	public void alertAccept() {
		driver.switchTo().alert().accept();
	}
	public void alertDismiss() {
		driver.switchTo().alert().dismiss();
	}	
	public String switchToMainWidow() {
		String mainWindowHandleValue=	driver.getWindowHandle();
		driver.switchTo().window(mainWindowHandleValue);
		return mainWindowHandleValue;	
	}

}
