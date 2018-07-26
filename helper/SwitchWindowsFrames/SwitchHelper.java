package com.intuit.qbo.subscription.framework.helper.SwitchWindowsFrames;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;
import com.intuit.qbo.subscription.framework.testbase.TestBase;

/**
 * 
 * @author bsingh5
 *
 */
public class SwitchHelper extends TestBase {

	private static final  Logger log = LoggerHelper.getLogger(SwitchHelper.class);

	public static boolean SwitchToParentWindow(WebDriver driver) {
		boolean flag=false;
		log.info("Switching to Parent window....");
		driver.switchTo().defaultContent();
		flag=true;	
		return flag;
	}
	
	public static boolean SwitchToFrame( WebDriver driver,WebElement elem,String elemName) {
		boolean flag=false;
		log.info("Switching to Frame :"+elemName);
		driver.switchTo().frame(elem);
		flag=true;	
		return flag;
	}
	
	public static void  closeTabsandSwitchToCurrentTab( WebDriver driver) {
		
		 Set <String> windows = driver.getWindowHandles();
		    String mainwindow = driver.getWindowHandle();

		    for (String handle: windows)
		    {
		        driver.switchTo().window(handle);
		        if(!handle.equalsIgnoreCase(mainwindow))
		        {
		            driver.close();
		        }
		    }

		   driver.switchTo().window(mainwindow);	
		
	}

}