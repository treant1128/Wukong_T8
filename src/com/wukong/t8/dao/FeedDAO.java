package com.wukong.t8.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wukong.t8.pojo.Feed;

/**
 * A data access object (DAO) providing persistence and search support for Feed
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.wukong.t8.pojo.Feed
 * @author MyEclipse Persistence Tools
 */

public class FeedDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FeedDAO.class);
	// property constants
	public static final String FEED_TITLE = "feedTitle";
	public static final String FEED_IMAGE_TITLE = "feedImageTitle";
	public static final String FEED_IMAGE_LINK = "feedImageLink";
	public static final String FEED_IMAGE_URL = "feedImageUrl";
	public static final String FEED_DESCRIPTION = "feedDescription";
	public static final String FEED_LINK = "feedLink";
	public static final String FEED_LAUGUAGE = "feedLauguage";
	public static final String FEED_COPYRIGHT = "feedCopyright";
	public static final String FEED_TYPE = "feedType";

	private static FeedDAO instance;
	
	private FeedDAO(){
		
	}
	
	public synchronized static FeedDAO getInstance(){
		if(instance==null){
			instance=new FeedDAO();
		}
		return instance;
	}
//	public void save(Feed transientInstance) {
//		log.debug("saving Feed instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Feed persistentInstance) {
		log.debug("deleting Feed instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feed findById(java.lang.Integer id) {
		log.debug("getting Feed instance with id: " + id);
		try {
			Feed instance = (Feed) getSession().get("com.wukong.t8.pojo.Feed",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Feed instance) {
		log.debug("finding Feed instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Feed").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Feed instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feed as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFeedTitle(Object feedTitle) {
		return findByProperty(FEED_TITLE, feedTitle);
	}

	public List findByFeedImageTitle(Object feedImageTitle) {
		return findByProperty(FEED_IMAGE_TITLE, feedImageTitle);
	}

	public List findByFeedImageLink(Object feedImageLink) {
		return findByProperty(FEED_IMAGE_LINK, feedImageLink);
	}

	public List findByFeedImageUrl(Object feedImageUrl) {
		return findByProperty(FEED_IMAGE_URL, feedImageUrl);
	}

	public List findByFeedDescription(Object feedDescription) {
		return findByProperty(FEED_DESCRIPTION, feedDescription);
	}

	public List findByFeedLink(Object feedLink) {
		return findByProperty(FEED_LINK, feedLink);
	}

	public List findByFeedLauguage(Object feedLauguage) {
		return findByProperty(FEED_LAUGUAGE, feedLauguage);
	}

	public List findByFeedCopyright(Object feedCopyright) {
		return findByProperty(FEED_COPYRIGHT, feedCopyright);
	}

	public List findByFeedType(Object feedType) {
		return findByProperty(FEED_TYPE, feedType);
	}

	public List findAll() {
		log.debug("finding all Feed instances");
		try {
			String queryString = "from Feed";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Feed merge(Feed detachedInstance) {
		log.debug("merging Feed instance");
		try {
			Feed result = (Feed) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Feed instance) {
		log.debug("attaching dirty Feed instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Feed instance) {
		log.debug("attaching clean Feed instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}