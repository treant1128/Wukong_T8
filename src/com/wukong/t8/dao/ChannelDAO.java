package com.wukong.t8.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wukong.t8.pojo.Channel;

/**
 * A data access object (DAO) providing persistence and search support for
 * Channel entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wukong.t8.pojo.Channel
 * @author MyEclipse Persistence Tools
 */

public class ChannelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ChannelDAO.class);
	// property constants
	public static final String CHANNEL_NAME = "channelName";
	public static final String CHANNEL_OPML_URL = "channelOpmlUrl";
	private static ChannelDAO instance;
	
	private ChannelDAO(){
		
	}
	
	public synchronized static ChannelDAO getInstance(){
		if(instance==null){
			instance=new ChannelDAO();
		}
		return instance;
	}
//	public void save(Channel transientInstance) {
//		log.debug("saving Channel instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Channel persistentInstance) {
		log.debug("deleting Channel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Channel findById(java.lang.Integer id) {
		log.debug("getting Channel instance with id: " + id);
		try {
			Channel instance = (Channel) getSession().get(
					"com.wukong.t8.pojo.Channel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Channel instance) {
		log.debug("finding Channel instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Channel").add(Example.create(instance))
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
		log.debug("finding Channel instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Channel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChannelName(Object channelName) {
		return findByProperty(CHANNEL_NAME, channelName);
	}

	public List findByChannelOpmlUrl(Object channelOpmlUrl) {
		return findByProperty(CHANNEL_OPML_URL, channelOpmlUrl);
	}

	public List findAll() {
		log.debug("finding all Channel instances");
		try {
			String queryString = "from Channel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Channel merge(Channel detachedInstance) {
		log.debug("merging Channel instance");
		try {
			Channel result = (Channel) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Channel instance) {
		log.debug("attaching dirty Channel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Channel instance) {
		log.debug("attaching clean Channel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}