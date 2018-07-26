package com.intuit.qbo.subscription.framework.helper.wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;
import com.intuit.qbo.subscription.framework.helper.configuration.config.ObjectRepo;
import com.intuit.qbo.subscription.framework.testbase.TestBase;

/**
 * 
 * @author bsingh5
 *
 */
public class WaitHelper {

	private WebDriver driver;
	private Logger Log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("WaitHelper : " + this.driver.hashCode());
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		Log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void checkForServerError() {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
		Log.info("Checking for Server error.......");

		try {
			
			List<WebElement> Error01 = driver.findElements(By.xpath("//*[@class='type danger']"));
			List<WebElement> Error02 = driver.findElements(By.xpath("//*[@class='wwsui-error-title']"));
			//List<WebElement> Error03 = driver.findElements(By.xpath("//*[@data-automation-id='ac_save_error_text' and not @type='warn']"));
			List<WebElement> Error04 = driver.findElements(By.xpath("//*[@class='alert-view errorMessage alert-error']"));
			List<WebElement> Error05 = driver.findElements(By.xpath("//*[@class='alert-view alert-error errorMessage']//*[@data-dojo-attach-point='titleNode' and text()='Something’s not quite right']"));
			List<WebElement> Error06 = driver.findElements(By.xpath("//*[@data-automation-id='ac_save_error_text']//*[text()='It looks like there was a problem on our end. Click \"Save\" again.']"));
			List<WebElement> Error07 = driver.findElements(By.xpath("//*[@class='alert-view alert-error errorMessage']"));
			List<WebElement> Error08 = driver.findElements(By.xpath("//*[@class='errorMsg red' and text()='An error occurred during payment method validation.']"));
			List<WebElement> Error09 = driver.findElements(By.xpath("//*[@class='alert alert-danger alert-dismissible ng-star-inserted']"));
			//List<WebElement> Error10 = driver.findElements(By.xpath("//*[@type='warn']//*[text()='Try something else.']"));
			List<WebElement> Error11 = driver.findElements(By.xpath("//*[@data-automation-id='ac_product_error']//*[contains(@class,'alert')]"));
			
			if ((!Error01.isEmpty()) || (!Error02.isEmpty()) || (!Error02.isEmpty())|| (!Error04.isEmpty())|| (!Error06.isEmpty())|| (!Error07.isEmpty())|| (!Error08.isEmpty())|| (!Error09.isEmpty())|| (!Error09.isEmpty())|| (!Error11.isEmpty()) ) {
				new TestBase().getNavigationScreen(driver);
				Log.info("=> => Service Error has occured <= <=");
				Assert.fail("=> => Service Error has occured <= <=");
			} else {
				Log.info("=> = > Service error has not occured <= <=");
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);

		}

	}

	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		try {
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
			Log.info("Waiting for Element to load..." + element.toString());
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.info("element found..." + element.getText());
		}
		finally {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	public WebElement waitForElement(WebDriver driver, long time, WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
			Log.info("Waiting for Element to load..." + element.toString());
			WebDriverWait wait = new WebDriverWait(driver, time);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		finally {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	public Boolean waitForElementNotPresent(WebDriver driver, long time, WebElement element) {
		Log.info("waiting for element not present...");
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementUntillAlertComes(WebDriver driver, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForSpinnerToDisappear(WebDriver driver, List<WebElement> element, long timeout) {
		int i=0;
		Log.info("=>=>  Waiting for Spinner to disappear  <=<=");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {

			for (i = 0; i <= timeout; ++i) {
				if (element.isEmpty()) {
					// here your code, if element finally disappeared
					break;
				} else {

					new TestBase().sleep(1);

				}
				if (i == ObjectRepo.reader.getExplicitWait())
					Assert.fail("Spinner not disappeared within seconds :" + ObjectRepo.reader.getExplicitWait());
			}

		} catch (Exception e) {
			Log.info("Exception  :" + e.getMessage());
		} finally {

			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);

		}

		Log.info("=>=> Spiner has disappeared <=<= :Time taken :"+i+" seconds");
	}

	public void waitForCookiePopup(WebDriver driver, WebElement element) {
		Log.info("wait for cookie popup notification");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {

			for (int i = 0; i <= ObjectRepo.reader.getExplicitWait(); ++i) {
				if (element.isDisplayed()) {
					element.click();
					break;
				} else {
					// do nothing
				}
				if (i == ObjectRepo.reader.getExplicitWait())
					Log.info("cookie popup notification not present");
			}
		} catch (Exception e) {
			Log.info("Exception  :" + e.getMessage());
		} finally {
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		}
	}

	public Boolean waitForUserAccessAlert(WebDriver driver, WebElement element) {
		Log.info("wait for user access alert");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			for (int i = 0; i <= ObjectRepo.reader.getExplicitWait();) {
				if (element.isDisplayed()) {
					return true;
				} else {
					return false;
				}
			}			
		} catch (Exception e) {
			Log.info("Exception  :" + e.getMessage());
		} finally {
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		}
		return null;
	}


	public boolean waitForElemenWithCustomWait(WebDriver driver, WebElement element, long timeout) {
		boolean flag=false;
		try {
			//Overrides global Implicit wait to 1 second
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			if(element.isDisplayed()) {
				flag=true;				
			}			
		}
		catch(Exception e) {
			Log.info("Element is not displayed :"+element.toString());
			return false;			
		}
		finally {
			//Resets to Global Implicit wait.
			driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);			
		}
		return flag;
	}


}
