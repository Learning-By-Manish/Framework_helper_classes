package com.intuit.qbo.subscription.framework.helper.dbHelper;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 
 * @author bsingh5
 *
 */
@Entity
@Table(name = "qbo_automation_evidence")
public class QBOAutomationEvidence implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "testCaseId")
	private String testCaseId;

	@Column(name = "stepId")
	private int stepId;

	@Column(name = "testCaseDescription")
	private String testCaseDescription;

	@Column(name = "qboPayload")
	private String qboPayload;

	@Column(name = "websPayload")
	private String websPayload;

	@Column(name = "screenshot")
	private String screenshot;

	@Column(name = "testResult")
	private String testResult;
	
	@Column(name = "companyId")
	private String companyId;

	@Column(name = "websResponse")
	private String websResponse;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	public Date getCreated() {
		return createdDate;
	}

	public void setCreated(Date created) {
		this.createdDate = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestCaseDescription() {
		return testCaseDescription;
	}

	public void setTestCaseDescription(String testCaseDescription) {
		this.testCaseDescription = testCaseDescription;
	}

	public String getQboPayload() {
		return qboPayload;
	}

	public void setQboPayload(String qboPayload) {
		this.qboPayload = qboPayload;
	}

	public String getWebsPayload() {
		return websPayload;
	}

	public void setWebsPayload(String websPayload) {
		this.websPayload = websPayload;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getWebsResponse() {
		return websResponse;
	}

	public void setWebsResponse(String websResponse) {
		this.websResponse = websResponse;
	}

	public int getStepId() {
		return stepId;
	}

	public void setStepId(int stepId) {
		this.stepId = stepId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public QBOAutomationEvidence() {

	}



	@Override
	public String toString() {
		return "QBOAutomationEvidence [id=" + id + ", testCaseId=" + testCaseId + ", stepId=" + stepId + ", testCaseDescription=" + testCaseDescription + ", qboPayload=" + qboPayload + ", websPayload=" + websPayload + ", screenshot=" + screenshot + ", testResult=" + testResult + ", companyId="
				+ companyId + ", websResponse=" + websResponse + ", createdDate=" + createdDate + "]";
	}

	public QBOAutomationEvidence(String testCaseId, int stepId, String testCaseDescription, String qboPayload, String websPayload, String screenshot, String testResult, String companyId, String websResponse) {
		super();
		this.testCaseId = testCaseId;
		this.stepId = stepId;
		this.testCaseDescription = testCaseDescription;
		this.qboPayload = qboPayload;
		this.websPayload = websPayload;
		this.screenshot = screenshot;
		this.testResult = testResult;
		this.companyId = companyId;
		this.websResponse = websResponse;
	}



}
