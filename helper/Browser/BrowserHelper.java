
package com.manish.qbo.subscription.framework.helper.Browser;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;
import com.intuit.qbo.subscription.framework.testbase.TestBase;

public class BrowserHelper extends TestBase{

	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(BrowserHelper.class);

	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("BrowserHelper : " + this.driver.hashCode());
	}

	public void goBack() {
		driver.navigate().back();
		Log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		Log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		Log.info("");
	}

	public Set<String> getWindowHandlens() {
		Log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		Log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		Log.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			Log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}
	public void switchToChildWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(
				getWindowHandlens());
		driver.switchTo().window(windowsId.get(1));
		Log.info("switching to child window");
	}
}
