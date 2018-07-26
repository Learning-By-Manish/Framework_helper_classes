package com.intuit.qbo.subscription.framework.helper.dbHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joda.time.DateTime;

import com.intuit.qbo.subscription.framework.helper.Logger.LoggerHelper;

/**
 * 
 * @author bsingh5
 *
 */
public class ManageQBNData {

	private final Logger logger = LoggerHelper.getLogger(ManageQBNData.class);
	private static SessionFactory factory;

	/* Method to CREATE an QBN in the database */
	public Integer addRecord(String companyId, String clusterId, String companyType, String environment,String userName) {
		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer qbnID = null;
		try {
			transaction = session.beginTransaction();
			QBN qbn = new QBN();
			qbn.setClusterId(clusterId);
			qbn.setCompanyId(companyId);
			qbn.setCompanyType(companyType);
			qbn.setEnvironment(environment);
			qbn.setUserName(userName);
			
			qbnID = (Integer) session.save(qbn);
			logger.info("Added record to DB...");
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qbnID;
	}
	
	
	
	public Integer addEvidence(String testCaseId, int stepId, String testCaseDescription, String qboPayload, String websPayload, String screenshot, String testResult, String companyId,String websResponse) {
		factory = MySqlHibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer qbnID = null;
		try {
			transaction = session.beginTransaction();
			screenshot = "http://172.16.124.213:8088/"+screenshot;
			QBOAutomationEvidence qBOAutomationEvidence = new QBOAutomationEvidence(testCaseId, stepId, testCaseDescription, qboPayload, websPayload, screenshot, testResult, companyId,websResponse);
	
			session.save(qBOAutomationEvidence);
			
			session.getTransaction().commit();
			logger.info("Added record to DB...");
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qbnID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<?> employees = session.createQuery("FROM QBODATA").list();
			Iterator<?> iterator = employees.iterator();
			while ( iterator.hasNext()) {
				QBN qbn = (QBN) iterator.next();
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public QBN fetchTrialCompany(String clusterId,String environment) {
		
		logger.info("Fetching CompanyId from database...");
		Date date = new DateTime().minusDays(30).toDate();// minusMonths(1).toDate();
		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		QBN qbn = null;
		
		try {
			
			transaction = session.beginTransaction();
			Query<?> query = session.createQuery("from QBN where companyType='trial'and clusterId=:cluster and environment=:env and createDate <=:date");
			query.setParameter("cluster",clusterId);
			query.setParameter("env",environment);
			query.setParameter("date",date,TemporalType.DATE);
			
			List<?> qbnList = query.getResultList();
			if(qbnList != null && qbnList.size()>0 ) {
				logger.info("Got record !!");
				qbn = (QBN) qbnList.get(1);
				logger.info("Company Id from DB is : "+qbn.getCompanyId()+"   "+qbn.getUserName() + "  "+qbn.getCreateDate());
			}else {
				logger.info("No record !!");
			}
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return qbn;
	}

	/* Method to UPDATE salary for an qbn */
	
	public void updateData(Integer companyId, String companyType) {

		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			QBN qbn = (QBN) session.get(QBN.class, companyId);
			qbn.setCompanyType(companyType);
			session.update(qbn);
			transaction.commit();
			logger.info("Updated the companyType successfully...");
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an qbn from the records */
	public void deleteData(Integer qbnId) {
		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			QBN qbn = (QBN) session.get(QBN.class, qbnId);
			session.delete(qbn);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
    List<QBN> data = new ArrayList<QBN>();
	
	public List<QBN> retrieveData() {
		factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<?> employees = session.createQuery("FROM QBN").getResultList();
			Iterator<?> iterator = employees.iterator();
			while ( iterator.hasNext()) {
				QBN qbn = (QBN) iterator.next();
				data.add(qbn);
			}
			session.getTransaction().commit();
			return data;
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
	
	public static void main(String[] args) {
		ManageQBNData getDbData = new ManageQBNData();
		//getDbData.updateData(new Integer("123146732930204"),"subscribed");1231 4673 3096 909
		//getDbData.fetchTrialCompany("qbo15", "silver-develop");
		/*List<QBN> testData = getDbData.retrieveData();
		for(int i =0; i<testData.size(); i++){
			System.out.println(testData.get(i).getClusterId()+" "+testData.get(i).getCompanyType()+" "+testData.get(i).getCompanyId()+" "+testData.get(i).getUserName()+"  "+testData.get(i).getCreateDate());
			
		}
		System.out.println("Test is log");*/
	}

}
