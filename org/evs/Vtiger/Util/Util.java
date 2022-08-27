package org.evs.Vtiger.Util;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class Util {
	

		public WebDriver driver;
		

		public  void browser() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinod\\eclipse-workspace\\vTigerCRMframeWork\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("launch the browser");


		}
		public void inputData() {
			 
		}

		public   void seturl(String inputurl) {
			driver.get(inputurl);
			System.out.println("to hit the url");
		
		}

		public  void sendkey(String locator,String locatorType,String inputData ) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			we.sendKeys(inputData);
			System.out.println("input data is pass");
			}catch(Exception e) {
				we.sendKeys(inputData);
				System.out.println("input data is pass");
 			System.out.println("input data is pass");	
			}
		}
		public WebElement getWebElement(String locator,String locatorType) {
			WebElement we=null;	
		By byobj=getByObj(locator, locatorType);
		
		try {
		we= driver.findElement(byobj);
		}catch(NoSuchElementException e) {
			if(waitUntilElement(byobj,30)==true){
				we=driver.findElement(byobj);
						return we;
			}else {
			System.out.println(locator +"is not fount on this page");
			e.printStackTrace();
			}
		}
		return we;
		}
		public By getByObj(String locator,String locatorType) {
			By byobj=null;
			if(locatorType.equalsIgnoreCase("xpath")) {
				byobj=By.xpath(locator);
			}else if(locatorType.equalsIgnoreCase("className")) {
				byobj=By.className(locator);
			}else if(locatorType.equalsIgnoreCase("cssSelector")) {
				byobj=By.cssSelector(locator);
			}else if(locatorType.equalsIgnoreCase("id")) {
				byobj=By.id(locator);
			}else if(locatorType.equalsIgnoreCase("linkText")) {
				byobj=By.linkText(locator);
			}else if(locatorType.equalsIgnoreCase("partialLinkText")) {
				byobj=By.partialLinkText(locator);
			}else if(locatorType.equalsIgnoreCase("name")) {
				byobj=By.name(locator);
			}else if(locatorType.equalsIgnoreCase("tagName")) {
				byobj=By.tagName(locator);
			}else {
				System.out.println("locator is wrong");
			}
			return byobj;
		}
		
		public  void select(String locator,String locatorType,String visibletext) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			Select sc=new Select(we);
			sc.selectByVisibleText(visibletext);
			System.out.println("selectbyVisible text is pass");
			}catch(StaleElementReferenceException e) {
				Select sc=new Select(we);
				sc.selectByVisibleText(visibletext);
				System.out.println("selectbyVisible text is pass");	
			}
		}

		
		public void selectbyvalue(String locator,String locatorType, String value) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			Select obj=new Select(we);
			obj.selectByValue(value);
			System.out.println("selectbyvalue box is pass");
			}catch(StaleElementReferenceException e) {
				Select obj=new Select(we);
				obj.selectByValue(value);
				System.out.println("selectbyvalue box is pass");
				
			}
		}

		
		public  void index(String locator,String locatorType,int Indexnumber) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			Select obj=new Select(we);
			obj.selectByIndex(3);
			System.out.println("Selectbyindex is pass");
			}catch(StaleElementReferenceException a) {
				Select obj=new Select(we);
				obj.selectByIndex(3);
				System.out.println("Selectbyindex is pass");
			}
		}
		

		public  void getSelected(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			boolean select=false;
			try {
			select=we.isSelected();
			}catch(StaleElementReferenceException e) {
				select=we.isSelected();
			}
			if(select==true) {
				System.out.println("text box id selected");
			}else {
				System.out.println("text box is not selected");
			}
		}

		

		public  void click(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			we.click();
			System.out.println("click is successfully");
			}catch(ElementNotInteractableException a) {
				we.click();
				System.out.println("click is successfully");	
			}
		}

		
		public  void getpagetitle( ) {
			String actualtitle=driver.getTitle();
			if(actualtitle.equalsIgnoreCase(actualtitle)) {

				System.out.println("Title is pass");
			}else {
				System.out.println("title is fail");
			}
			
		}

		
		public  void gettext(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			String tt=	we.getText(); 
				System.out.println(tt);

		}

		
		public  void getEnabled(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			Boolean enabled=false;
			try {
			 enabled=we.isEnabled();
			}catch(StaleElementReferenceException a) {
				enabled=we.isEnabled();	
			}
			if(enabled==true) {
				System.out.println("login bt is enabled");
			}else {
				System.out.println("login bt is not enabled");
			}
			
			

		}

		
		public  void getDisplayed(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			boolean displayed=false;
			try {
			 displayed =we.isDisplayed();
			}catch(StaleElementReferenceException a) {
				displayed =we.isDisplayed();
			}
			if(displayed==true) {
				System.out.println("login bt is Displayed");
			}else {
				System.out.println("login bt is not displayed");
			}

		}

		

		public  void mouseHover(String locator,String locatorType) {////////mousehoever/////
			WebElement we=getWebElement(locator, locatorType);
			Actions sc=new Actions(driver);
			try {
			sc.moveToElement(we).build().perform();
			System.out.println("MouseHover is success");
			}catch(StaleElementReferenceException w) {
				sc.moveToElement(we).build().perform();
				System.out.println("MouseHover is success");
			}

		}

		
		
		public  void market(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			try {
			we.click();
			System.out.println("click is pass");
			}catch(ElementNotInteractableException a) {
				we.click();
				System.out.println("click is pass");	
			}
		}

		
		
		public  void rightClick(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			Actions obj1=new Actions(driver);
			try {
			obj1.contextClick().build().perform();
			System.out.println("Double Click is success");
			}catch(ElementNotInteractableException a) {
				obj1.contextClick().build().perform();
				System.out.println("Double Click is success");
			}
		}

		public  void doubleClick(String locator,String locatorType) {
			WebElement we=getWebElement(locator, locatorType);
			Actions obj1=new Actions(driver);
			try {
			obj1.doubleClick().build().perform();
			System.out.println("double click is pass");
			}catch(ElementNotInteractableException a) {
				obj1.doubleClick().build().perform();
				System.out.println("double click is pass");
			}
		}

		
		public void dragAndDrop( String locator,String locatorType  ) {
			WebElement we=getWebElement(locator, locatorType);
			Actions act=new Actions(driver);
			try {
			act.clickAndHold().moveToElement(null).release().build().perform();
			System.out.println("Drag and Drop is success");
			}catch(ElementNotInteractableException a) {
				act.clickAndHold().moveToElement(null).release().build().perform();
				System.out.println("Drag and Drop is success");
			}
		}
		
		public  void handleframe(String locator,String locatorType,int number) {
			driver.switchTo().frame(number);
			System.out.println("Handleframe is pass");
		}
		
		public  void handleframe( String locator,String locatorType) {
			driver.switchTo().frame(locator);
			System.out.println("Handleframe is pass");
		}

		
		public  void handleframe(String locator,String locatorType,String idorname) {
			driver.switchTo().frame(idorname);
			System.out.println("Handleframe is pass");

		}

		
		public  void scroll( int x,int y) {
			JavascriptExecutor obj=(JavascriptExecutor) driver; 
			obj.executeScript("window.scrollBy("+x+","+y+")");
			System.out.println("scroll by is successfully");

		} 

		
		public  void jsclick(WebElement we,String elementName) {
			JavascriptExecutor obj=(JavascriptExecutor)driver;
			obj.executeScript("argument[0].click()",we);
			System.out.println("Click is done successfully "+" "+elementName);

		}

		public  void inputdata( String data, WebElement we ) {
			JavascriptExecutor obj=(JavascriptExecutor)driver;
			obj.executeScript("argument[0].value='"+data+"'",we);
			System.out.println("data is print successfully on text box");
		}

      public boolean waitUntilElement(By byObj, int timeInSec ) {
    	  try {
    		  WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
    		  wt.until(ExpectedConditions.invisibilityOfElementLocated(byObj));
    		  return true;
    		  
    	  }catch(TimeoutException e) {
    		  return false;
    	  }
      }

		
			
		}




	







