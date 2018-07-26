package com.intuit.qbo.subscription.framework.helper.configuration.config;

import com.intuit.qbo.subscription.framework.helper.configuration.browser.BrowserType;

public interface ConfigReader {
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public BrowserType getBrowser();
	
	public String getCancelStatusText();
	public String getResubscribeText();
	public String getResubscribestatusText();
	public String getcancelConfirmationText();
	public String getpayrollCancellationStatusText();
	public String getCompanyLabelText();
	public String getTrialLabelText();
	public String getTrialLengthText();
	public String getNextChargeLabelText();
	public String getSubscribeNowText();
	public String getSubsribedLabelForPayrollText();
	public String getPaymentMethodText();
	public String getPlanDetailsText();
	public String getPayrollNextChargeText();

}
