package com.wukong.t8.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wukong.t8.pojo.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Repository entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wukong.t8.pojo.Repository
 * @author MyEclipse Persistence Tools
 */

public class RepositoryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RepositoryDAO.class);
	// property constants
	public static final String REPO_TITLE = "repoTitle";
	public static final String REPO_LINK = "repoLink";
	public static final String REPO_CATEGORY = "repoCategory";
	public static final String REPO_OPERATOR = "repoOperator";
	public static final String REPO_STATUS = "repoStatus";

	private static RepositoryDAO instance;
	
	private RepositoryDAO (){
		
	}
	
	public synchronized static RepositoryDAO getInstance(){
		if(instance==null){
			instance=new RepositoryDAO();
		}
		return instance;
	}
//	public void save(Repository transientInstance) {
//		log.debug("saving Repository instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Repository persistentInstance) {
		log.debug("deleting Repository instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Repository findById(java.lang.Integer id) {
		log.debug("getting Repository instance with id: " + id);
		try {
			Repository instance = (Repository) getSession().get(
					"com.wukong.t8.pojo.Repository", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Repository instance) {
		log.debug("finding Repository instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Repository").add(
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
		log.debug("finding Repository instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Repository as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRepoTitle(Object repoTitle) {
		return findByProperty(REPO_TITLE, repoTitle);
	}

	public List findByRepoLink(Object repoLink) {
		return findByProperty(REPO_LINK, repoLink);
	}

	public List findByRepoCategory(Object repoCategory) {
		return findByProperty(REPO_CATEGORY, repoCategory);
	}

	public List findByRepoOperator(Object repoOperator) {
		return findByProperty(REPO_OPERATOR, repoOperator);
	}

	public List findByRepoStatus(Object repoStatus) {
		return findByProperty(REPO_STATUS, repoStatus);
	}

	public List findAll() {
		log.debug("finding all Repository instances");
		try {
			String queryString = "from Repository";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Repository merge(Repository detachedInstance) {
		log.debug("merging Repository instance");
		try {
			Repository result = (Repository) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Repository instance) {
		log.debug("attaching dirty Repository instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Repository instance) {
		log.debug("attaching clean Repository instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}