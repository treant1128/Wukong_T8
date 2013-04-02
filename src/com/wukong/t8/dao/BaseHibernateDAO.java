package com.wukong.t8.dao;

import com.wukong.t8.HibernateSessionFactory;
import com.wukong.t8.abstractpojo.AbstractPojo;

import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	public void save(AbstractPojo transientInstance){
		Session session=getSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			session.save(transientInstance);
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}