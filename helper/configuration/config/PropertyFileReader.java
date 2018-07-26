
package com.intuit.qbo.subscription.framework.helper.configuration.config;

import java.util.Properties;

import org.apache.log4j.Level;

import com.intuit.qbo.subscription.framework.helper.configuration.browser.BrowserType;
import com.intuit.qbo.subscription.framework.utills.ResourceHelper;


public class PropertyFileReader implements ConfigReader{

	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			prop.load(ResourceHelper.getResourcePathInputStream("/src/main/resources/configfile/testconfig.properties"));
			prop.load(ResourceHelper.getResourcePathInputStream("/src/main/resources/configfile/application.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	public Level getLoggerLevel() {

		switch (prop.getProperty("Logger.Level")) {

		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
	
	public String getCancelStatusText() {
		return prop.getProperty("Cancelstatus");
	}
	
	public String getResubscribeText() {
		return prop.getProperty("Resubscribe");
	}

	public String getResubscribestatusText() {
		return prop.getProperty("Resubscribestatus");
	}
	
	public String getCancelConfirmationText() {
		return prop.getProperty("Resubscribestatus");
	}
	
	public String getCompanyLabelText() {
		return prop.getProperty("companyLabel");
	}
	
	public String getTrialLabelText() {
		return prop.getProperty("trialLabel");
	}

	public String getTrialLengthText() {
		return prop.getProperty("trialLength");
	}

	public String getNextChargeLabelText() {
		return prop.getProperty("nextChargeLabel");
	}

	public String getpayrollCancellationStatusText() {
		return prop.getProperty("Resubscribestatus");
	}

	public String getSubscribeNowText() {
		return prop.getProperty("subscribeNow");
	}

	public String getSubsribedLabelForPayrollText() {
		return prop.getProperty("subsribedLabelForPayroll");
	}

	public String getPaymentMethodText() {
		return prop.getProperty("paymentMethod");
	}
	
	public String getPlanDetailsText() {
		return prop.getProperty("planDetails");
	}
	
	public String getPayrollNextChargeText() {
		return prop.getProperty("payrollNextCharge");
	}

	@Override
	public String getcancelConfirmationText() {
		return prop.getProperty("cancelConfirmation");
	}


}
