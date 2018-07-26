package com.intuit.qbo.subscription.framework.helper.dbHelper;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "QBODATA")
public class QBN {
	

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "companyId")
	private String companyId;
	
	@Column(name = "clusterId")
	private String clusterId;
	
	@Column(name = "companyType")
	private String companyType;
	
	@Column(name = "environment")
	private String environment;
	
	@Column(name = "userName")
	private String userName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	
	@Column(name = "create_date")
	private Date createDate;
	
	public QBN() {
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}


}
