package com.intuit.qbo.subscription.framework.helper.assertionHelper;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;
import com.intuit.qbo.subscription.framework.helper.configuration.config.ObjectRepo;
import com.intuit.qbo.subscription.framework.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author bsingh5
 *
 */
public class VerificationHelper extends TestBase {

	private static final  Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public static synchronized boolean verifyElementPresent( WebElement element) {
		log.info("verifying for the presence of element "+element);
		boolean isDispalyed = false;
		try {
			isDispalyed= element.isDisplayed();
			log.info(element+" present.."+isDispalyed);
			test.log(LogStatus.PASS, element.getText()+" present.."+isDispalyed);
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, ex);
			log.error("Element not found " + ex);
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyElementPresent( WebElement element,String elemntTextName) {
		log.info("verifying for the presence of element "+elemntTextName);
		boolean isPresent = false;
		try {
			isPresent= element.isDisplayed();
			log.info(element+" present.."+isPresent);
			test.log(LogStatus.PASS, element.getText()+" present.."+isPresent);
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, elemntTextName+"..not found"+ex);
			log.error("Element not found " + ex);
		}

		return isPresent;
	}

	public static synchronized boolean verifyElementNotPresent( WebElement element, String elemntTextName) {
		log.info("verifying for the presence of element "+elemntTextName);
		boolean isDispalyed = false;
		try {
			element.isDisplayed();
			log.info(element+" present.."+isDispalyed);
			test.log(LogStatus.ERROR, element.getText()+" present.."+isDispalyed);
		}
		catch(Exception ex) {
			test.log(LogStatus.PASS, elemntTextName+" not found which is expected");
			log.error("Element found ");
			isDispalyed = true;
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyElementNotPresent( WebElement element,WebDriver driver) {
		log.info("verifying for the presence of element "+element);
		boolean isDispalyed = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			log.error(element+"not present.."+isDispalyed);
			test.log(LogStatus.ERROR, element.getText()+"not present.."+isDispalyed);
		}
		catch(Exception ex) {
			//	test.log(LogStatus.PASS, elemntTextName+" not found which is expected");
			isDispalyed = true;
			log.info(element+"not present.."+isDispalyed);

		}

		return isDispalyed;
	}

	public static synchronized boolean verifyTextEquals( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			log.info("Expected Text : "+expectedText + "::   ActualText : "+actualText);
			if(actualText.equals(expectedText)) {
				test.log(LogStatus.PASS, expectedText+" present.."+actualText);
				log.info(element+" present.."+actualText);
				return flag=true;
			}
			else {
				return flag;
			}
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, " test is failed" + ex);
			log.info("Element not found " + ex);
			return flag;
		}
	}

	public static synchronized boolean verifyComapnyIdLength(String companyId) {
		boolean flag = false;
		if(null== companyId)
			return flag;

		if(companyId.length()>5) {
			test.log(LogStatus.PASS, "company Id.."+companyId);
			log.info(" company Id.."+companyId);
			return flag=true;
		}
		else {
			test.log(LogStatus.ERROR, " Company creaation failed ");
			log.info("Comapany id is not valid "  + companyId);
			return flag;
		}
	}

	public static synchronized boolean verifyText(String expectedText , String actualText) {
		boolean flag = false;

		if(actualText.equals(expectedText)) {
			test.log(LogStatus.PASS, expectedText+" present.."+actualText);
			log.info(" present.."+actualText);
			return flag=true;
		}
		else {
			test.log(LogStatus.ERROR, " test is failed expected Text :" +expectedText  +" actual Text :"+ actualText );
			log.info("Strings not same expected Text :" +expectedText  +" actual Text :"+ actualText );
			return flag;
		}
	}

	public static synchronized boolean verifyContainsText(String expectedText , String actualText) {
		boolean flag = false;
        if(actualText == null) {
        		log.info("actual Text :" +expectedText  +" does not contain :"+ actualText  );
          return flag;	
        }
		
        if(actualText.contains(expectedText)) {
			test.log(LogStatus.PASS, expectedText+" conatains.."+actualText);
			log.info(" conatins.."+actualText);
			return flag=true;
		}
		else {
			test.log(LogStatus.ERROR, " test is failed actual Text :" +expectedText  +" does not contain :"+ actualText );
			log.info("actual Text :" +expectedText  +" does not contain :"+ actualText  );
			return flag;
		}
	}


	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		log.info(flag);
		System.out.println();
		return flag;
	}


	/*
	 * This method was created to 
	 * verify dynamic elements present or not
	 */
	public static synchronized boolean verifyElementPresent( String locator,String text,WebDriver driver,String elementIdentifier) {
		boolean isDispalyed = false;
		log.info("verifying element present ");

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			String locator1[]=locator.split("\\+");
			locator=locator1[0]+text+locator1[1];
			isDispalyed= driver.findElement(By.xpath(locator)).isDisplayed();
			log.info(elementIdentifier+" present.."+isDispalyed);
			test.log(LogStatus.PASS, elementIdentifier+" present.."+isDispalyed);
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, ex);
			log.error("Element "+elementIdentifier+" not found " + ex);
		}
		finally{
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyElementNotVisible( String locator,String text,WebDriver driver,String elementIdentifier) {
		boolean isVisible = false;
		log.info("verifying element not visible ");

		try {
			String locator1[]=locator.split("\\+");
			locator=locator1[0]+text+locator1[1];
			WebDriverWait wait = new WebDriverWait(driver,10);
			isVisible = wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(locator))));	
			log.info(elementIdentifier+" not visible.."+isVisible);
			test.log(LogStatus.PASS, elementIdentifier+"not visible.."+isVisible);
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, ex);
			log.error("Element "+elementIdentifier+" found " + ex);
			isVisible = true;
		}

		return isVisible;
	}

	public static synchronized boolean verifyTextContains( WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();
			if(actualText.contains(expectedText)) {
				test.log(LogStatus.PASS, expectedText+" present.."+actualText);
				log.info(element+" present.."+actualText);
				return flag=true;
			}
			else {
				return flag;
			}
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, " test is failed" + ex);
			log.info("Element not found " + ex);
			return flag;
		}
	}

	public static synchronized boolean verifyElementEnabled( WebElement element,String elementIdentifier) {
		boolean isenabled = false;
		log.info("verifying element is enabled ");

		try {
			isenabled=element.isEnabled();
			log.info(elementIdentifier+" is enabled.."+isenabled);
			test.log(LogStatus.PASS, elementIdentifier+"not visible.."+isenabled);
		}
		catch(Exception ex) {
			test.log(LogStatus.ERROR, ex);
			log.error("Element "+elementIdentifier+" not found " + ex);
		}

		return isenabled;
	}

	public static synchronized boolean verifyNextChargeDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		LocalDate actualDate;
		try {
			date = sdf.parse(dateStr);
			LocalDate dateToVerify = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate curentDate=LocalDate.now();
			Month currentMonth=curentDate.getMonth();
			String currentMonthText=currentMonth.toString();
			if(currentMonthText.equalsIgnoreCase("FEBRUARY")) {
				actualDate = LocalDate.now().plusDays(28);
			}
			else {
				actualDate = LocalDate.now().plusDays(30);
			}
			long days = ChronoUnit.DAYS.between( actualDate, dateToVerify);
			log.info(" actualDate::  " + actualDate + " date to verify "  + dateToVerify  + " dates different by number of days ::" +days);
			return  days==0 || days== -1 ||days==1;
		} catch (ParseException e) {
			return false;
		}
	}

	public static synchronized boolean verifyFrText(String expectedText , String actualText) {
		boolean flag = false;

		final Collator instance = Collator.getInstance();
		// This strategy mean it’ll ignore the accents
		instance.setStrength(Collator.NO_DECOMPOSITION);

		int temp = instance.compare(expectedText, actualText);

		if(temp==0) {
			test.log(LogStatus.PASS, expectedText+" present.."+actualText);
			log.info(" present.."+actualText);
			return flag=true;
		}
		else {
			test.log(LogStatus.ERROR, " test is failed expected Text :" +expectedText  +" actual Text :"+ actualText );
			log.info("Strings not same expected Text :" +expectedText  +" actual Text :"+ actualText );
			return flag;
		}
	}

}