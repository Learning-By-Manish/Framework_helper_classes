package com.intuit.qbo.subscription.framework.helper.genericHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;
import com.intuit.qbo.subscription.framework.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author bsingh5
 *
 */
public class GenericHelper extends TestBase{
	
	private static final Logger log = LoggerHelper.getLogger(GenericHelper.class);
	
	public String readValueFromElement(WebElement element, String elemntTextName) {

		if (null == element){
			log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element,elemntTextName);
			test.log(LogStatus.PASS, elemntTextName+" present..");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, elemntTextName+" not found.." + e);
			log.error(e);
			return null;
		}

		if (!displayed)
			return null;
		String text = element.getText();
		log.info("weblement valus is.."+text);
		return text;
	}
	
	public String readValueFromElement(WebElement element) {

		if (null == element){
			log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
			test.log(LogStatus.PASS, element.getText()+" present..");
		} catch (Exception e) {
			test.log(LogStatus.ERROR,e);
			log.error(e);
			return null;
		}

		if (!displayed)
			return null;
		String text = element.getText();
		log.info("weblement valus is.."+text);
		return text;
	}
	

	public String readValueFromInput(WebElement element,String elemntTextName) {
		if (null == element)
			return null;
		if (!isDisplayed(element,elemntTextName))
			return null;
		String value = element.getAttribute("value");
		log.info("weblement valus is.."+value);
		test.log(LogStatus.PASS, "weblement valus is.."+value);
		return value;
	}
	
	public String readValueFromInput(WebElement element) {
		if (null == element)
			return null;
		if (!isDisplayed(element))
			return null;
		String value = element.getAttribute("value");
		log.info("weblement valus is.."+value);
		test.log(LogStatus.PASS, "weblement valus is.."+value);
		return value;
	}
	
	public boolean isDisplayed(WebElement element,String elemntTextName) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element);
			test.log(LogStatus.PASS, element+" present..");
			return true;
		} catch (Exception e) {
			test.log(LogStatus.ERROR, " test is failed" + e);
			log.info(e);
			return false;
		}
	}
	
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element.getText());
			test.log(LogStatus.PASS, element.getText()+" present..");
			return true;
		} catch (Exception e) {
			test.log(LogStatus.ERROR, " test is failed" + e);
			log.info(e);
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element,String elemntTextName) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element);
			test.log(LogStatus.PASS, elemntTextName+" present..");
			return false;
		} catch (Exception e) {
			test.log(LogStatus.ERROR, elemntTextName+" not found.." + e);
			log.error(e);
			return true;
		}
	}
	
	public String getDisplayText(WebElement element, String elemntTextName) {
		if (null == element)
			return null;
		if (!isDisplayed(element,elemntTextName))
			return null;
		return element.getText();
	}
	
	public String getDisplayText(WebElement element) {
		if (null == element)
			return null;
		if (!isDisplayed(element))
			return null;
		return element.getText();
	}
	

	public static synchronized String getElementText( WebElement element,String elemntTextName) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
			test.log(LogStatus.PASS, elemntTextName+" present.."+elementText);
		} catch (Exception e) {
			test.log(LogStatus.ERROR, elemntTextName+" not found.." + e);
			log.error("Element not found " + e);
		}
		return elementText;
	}

	public static synchronized String getElementText( WebElement element) {
		
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
			test.log(LogStatus.PASS, element.getText()+" present.."+elementText);
		} catch (Exception e) {
			test.log(LogStatus.ERROR,e);
			log.error("Element not found " + e);
		}
		return elementText;
	}
	

	public static synchronized String getElementTextFromTextbox( WebElement element) {
		
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getAttribute("value");
			test.log(LogStatus.PASS, element.getText()+" present.."+elementText);
		} catch (Exception e) {
			test.log(LogStatus.ERROR,e);
			log.error("Element not found " + e);
		}
		return elementText;
	}

}
