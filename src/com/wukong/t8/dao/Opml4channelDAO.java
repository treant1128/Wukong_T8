package com.wukong.t8.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wukong.t8.pojo.Opml4channel;

/**
 * A data access object (DAO) providing persistence and search support for
 * Opml4channel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wukong.t8.pojo.Opml4channel
 * @author MyEclipse Persistence Tools
 */

public class Opml4channelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(Opml4channelDAO.class);
	// property constants
	public static final String OPML_HEAD_TITLE = "opmlHeadTitle";
	public static final String OPML_BODY_TITLE = "opmlBodyTitle";
	public static final String OPML_BODY_TEXT = "opmlBodyText";
	public static final String OPML_OUTLINE_TEXT = "opmlOutlineText";
	public static final String OPML_OUTLINE_TITLE = "opmlOutlineTitle";
	public static final String OPML_OUTLINE_TYPE = "opmlOutlineType";
	public static final String OPML_OUTLINE_XML_URL = "opmlOutlineXmlUrl";
	public static final String OPML_OUTLINE_HTML_URL = "opmlOutlineHtmlUrl";

	private static Opml4channelDAO instance;
	
	private Opml4channelDAO(){
		
	}
	
	public synchronized static Opml4channelDAO getInstance(){
		if(instance==null){
			instance=new Opml4channelDAO();
		}
		return instance;
	}
//	public void save(Opml4channel transientInstance) {
//		log.debug("saving Opml4channel instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Opml4channel persistentInstance) {
		log.debug("deleting Opml4channel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Opml4channel findById(java.lang.Integer id) {
		log.debug("getting Opml4channel instance with id: " + id);
		try {
			Opml4channel instance = (Opml4channel) getSession().get(
					"com.wukong.t8.pojo.Opml4channel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Opml4channel instance) {
		log.debug("finding Opml4channel instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Opml4channel").add(
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
		log.debug("finding Opml4channel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Opml4channel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOpmlHeadTitle(Object opmlHeadTitle) {
		return findByProperty(OPML_HEAD_TITLE, opmlHeadTitle);
	}

	public List findByOpmlBodyTitle(Object opmlBodyTitle) {
		return findByProperty(OPML_BODY_TITLE, opmlBodyTitle);
	}

	public List findByOpmlBodyText(Object opmlBodyText) {
		return findByProperty(OPML_BODY_TEXT, opmlBodyText);
	}

	public List findByOpmlOutlineText(Object opmlOutlineText) {
		return findByProperty(OPML_OUTLINE_TEXT, opmlOutlineText);
	}

	public List findByOpmlOutlineTitle(Object opmlOutlineTitle) {
		return findByProperty(OPML_OUTLINE_TITLE, opmlOutlineTitle);
	}

	public List findByOpmlOutlineType(Object opmlOutlineType) {
		return findByProperty(OPML_OUTLINE_TYPE, opmlOutlineType);
	}

	public List findByOpmlOutlineXmlUrl(Object opmlOutlineXmlUrl) {
		return findByProperty(OPML_OUTLINE_XML_URL, opmlOutlineXmlUrl);
	}

	public List findByOpmlOutlineHtmlUrl(Object opmlOutlineHtmlUrl) {
		return findByProperty(OPML_OUTLINE_HTML_URL, opmlOutlineHtmlUrl);
	}

	public List findAll() {
		log.debug("finding all Opml4channel instances");
		try {
			String queryString = "from Opml4channel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opml4channel merge(Opml4channel detachedInstance) {
		log.debug("merging Opml4channel instance");
		try {
			Opml4channel result = (Opml4channel) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opml4channel instance) {
		log.debug("attaching dirty Opml4channel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opml4channel instance) {
		log.debug("attaching clean Opml4channel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}