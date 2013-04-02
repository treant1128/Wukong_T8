package com.wukong.t8.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wukong.t8.pojo.Netsource;

/**
 * A data access object (DAO) providing persistence and search support for
 * Netsource entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wukong.t8.pojo.Netsource
 * @author MyEclipse Persistence Tools
 */

public class NetsourceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NetsourceDAO.class);
	// property constants
	public static final String SOURCE_NAME = "sourceName";
	public static final String SOURCE_URL = "sourceUrl";

	private static NetsourceDAO instance;

	private NetsourceDAO(){
		
	}
	public synchronized static NetsourceDAO getInstance(){
		if(instance==null){
			instance=new NetsourceDAO();
		}
		return instance;
	}
//	public void save(Netsource transientInstance) {
//		log.debug("saving Netsource instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Netsource persistentInstance) {
		log.debug("deleting Netsource instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Netsource findById(java.lang.Integer id) {
		log.debug("getting Netsource instance with id: " + id);
		try {
			Netsource instance = (Netsource) getSession().get(
					"com.wukong.t8.pojo.Netsource", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Netsource instance) {
		log.debug("finding Netsource instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Netsource").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Netsource instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Netsource as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySourceName(Object sourceName) {
		return findByProperty(SOURCE_NAME, sourceName);
	}

	public List findBySourceUrl(Object sourceUrl) {
		return findByProperty(SOURCE_URL, sourceUrl);
	}

	public List findAll() {
		log.debug("finding all Netsource instances");
		try {
			String queryString = "from Netsource";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Netsource merge(Netsource detachedInstance) {
		log.debug("merging Netsource instance");
		try {
			Netsource result = (Netsource) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Netsource instance) {
		log.debug("attaching dirty Netsource instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Netsource instance) {
		log.debug("attaching clean Netsource instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}