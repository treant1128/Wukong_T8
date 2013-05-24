package com.wukong.t8.dao;

import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Opml4channel;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Entry
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.wukong.t8.pojo.Entry
 * @author MyEclipse Persistence Tools
 */

public class EntryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(EntryDAO.class);
	// property constants
	public static final String ENTRY_TITLE = "entryTitle";
	public static final String ENTRY_LINK = "entryLink";
	public static final String ENTRY_AUTHOR = "entryAuthor";
	public static final String ENTRY_GUID = "entryGuid";
	public static final String ENTRY_CATEGORY = "entryCategory";
	public static final String ENTRY_COMMENTS = "entryComments";
	public static final String ENTRY_DESCRIPTION = "entryDescription";
	public static final String ENTRY_ABSTRACT = "entryAbstract";
	public static final String ENTRY_IMAGE_URL = "entryImageUrl";
	public static final String ENTRY_PRIORITY = "entryPriority";
	public static final String ENTRY_RESPOND_RATIO = "entryRespondRatio";
	
	private static EntryDAO instance;
	
	private EntryDAO(){
		
	}
	
	public synchronized static EntryDAO getInstance(){
		if(instance==null){
			instance=new EntryDAO();
		}
		return instance;
	}

//	public void save(Entry transientInstance) {
//		log.debug("saving Entry instance");
//		try {
//			getSession().save(transientInstance);
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//			log.error("save failed", re);
//			throw re;
//		}
//	}

	public void delete(Entry persistentInstance) {
		log.debug("deleting Entry instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Entry findById(java.lang.Integer id) {
		log.debug("getting Entry instance with id: " + id);
		try {
			Entry instance = (Entry) getSession().get(
					"com.wukong.t8.pojo.Entry", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Entry instance) {
		log.debug("finding Entry instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wukong.t8.pojo.Entry").add(Example.create(instance))
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
		log.debug("finding Entry instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Entry as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEntryTitle(Object entryTitle) {
		return findByProperty(ENTRY_TITLE, entryTitle);
	}

	public List findByEntryLink(Object entryLink) {
		return findByProperty(ENTRY_LINK, entryLink);
	}

	public List findByEntryAuthor(Object entryAuthor) {
		return findByProperty(ENTRY_AUTHOR, entryAuthor);
	}

	public List findByEntryGuid(Object entryGuid) {
		return findByProperty(ENTRY_GUID, entryGuid);
	}

	public List findByEntryCategory(Object entryCategory) {
		return findByProperty(ENTRY_CATEGORY, entryCategory);
	}

	public List findByEntryComments(Object entryComments) {
		return findByProperty(ENTRY_COMMENTS, entryComments);
	}

	public List findByEntryDescription(Object entryDescription) {
		return findByProperty(ENTRY_DESCRIPTION, entryDescription);
	}

	public List findByEntryAbstract(Object entryAbstract) {
		return findByProperty(ENTRY_ABSTRACT, entryAbstract);
	}

	public List findByEntryImageUrl(Object entryImageUrl) {
		return findByProperty(ENTRY_IMAGE_URL, entryImageUrl);
	}

	public List findByEntryPriority(Object entryPriority) {
		return findByProperty(ENTRY_PRIORITY, entryPriority);
	}

	public List findByEntryRespondRatio(Object entryRespondRatio) {
		return findByProperty(ENTRY_RESPOND_RATIO, entryRespondRatio);
	}

	public List findAll() {
		log.debug("finding all Entry instances");
		try {
			String queryString = "from Entry";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Entry merge(Entry detachedInstance) {
		log.debug("merging Entry instance");
		try {
			Entry result = (Entry) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Entry instance) {
		log.debug("attaching dirty Entry instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Entry instance) {
		log.debug("attaching clean Entry instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Entry> getEntryListByProperty(String property,String keyWords, String startTime, String endTime){
		String hqlStr="";
		if(startTime==null||endTime==null){
			hqlStr="from Entry as entry where entry."+property+" like ?";
		}else{
			hqlStr="from Entry as entry where entry."+property+" like ?"
			+" and entry.entryPubDate between '"+startTime+"' and '"+endTime+"'";
		}
		hqlStr+="order by entry.entryPubDate desc";
		return getEntryListByKeyWordsIncludePubDate(hqlStr, keyWords);
	}
	
	@SuppressWarnings("unchecked")
	public List<Entry> getEntryListByKeyWordsIncludePubDate(String hqlStr, String keyWords){
		
		String [] str=keyWords.split(" ");
		StringBuilder sb=new StringBuilder();
		for(String s:str){
			if(!s.equals("")){
				sb.append("%"+s+"%");
			}
		}
		Session session=getSession();
		Query query=session.createQuery(hqlStr);
		query.setString(0, sb.toString());
		query.setFirstResult(0);
		query.setMaxResults(1024);
//		if(startTime==null||endTime==null){
//			query.setString(1, startTime);
//			query.setString(2, endTime);
//		}
		List<Entry> all=query.list();
		for(Entry entry:all){
		//		entry.getOpml().getOpmlHeadTitle();
			entry.getEntryLink();
		}
		session.close();
		return all;
	}
	
	/**
	 * 返回每页的Entry
	 * @param opmlOutlineXmlUrl
	 * @param page
	 * @param rowsPerPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Entry> getReferencedEntriesByOpml4channel(String opmlOutlineXmlUrl, int page, int rowsPerPage){
		Session session=getSession();
		String hql="from Entry as entry where entry.opml4channel.opmlOutlineXmlUrl = '"+opmlOutlineXmlUrl+"' order by entry.entryId desc";
		Query query = session.createQuery(hql);
		query.setFirstResult((page-1)*rowsPerPage); 
		query.setMaxResults(rowsPerPage);
		List<Entry> all = query.list();
		session.close();
		return all;
	}
	
	/**
	 * 总页数
	 * @param rowsPerPage
	 * @return
	 */
	public Map<String, Integer> getEntryNumInfo(String opmlOutlineXmlUrl, int rowsPerPage){
		Map<String, Integer> map=new HashMap<String, Integer>();
		int rows = getEntryNum(opmlOutlineXmlUrl);
		map.put("totalRows", rows);
		if(rows%rowsPerPage==0){
			map.put("totalPages", rows/rowsPerPage);
		}else{
			map.put("totalPages", rows/rowsPerPage+1);
		}
		return map;
	}
	
	private int getEntryNum(String opmlOutlineXmlUrl){
		int rows=0;
		Session session=getSession();
		String hql="select count(*) from Entry as entry where entry.opml4channel.opmlOutlineXmlUrl = '"+opmlOutlineXmlUrl+"'";
		Query query=session.createQuery(hql);
//		rows=(int) ((Long)query.iterate().next()).longValue();  //two ways to obtain the Integer counts
		rows=((Long)query.uniqueResult()).intValue();
		session.close();
		return rows;
	}
	
	public void updateByGuid(String title, String imgUrl, int priority, String guid){
		Session session = getSession();
		String hql = "update Entry entry set entry.entryTitle=?, entry.entryImageUrl=?, entry.entryPriority=? where entry.entryGuid=?";
		
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setString(0, title);
			query.setString(1, imgUrl);
			query.setInteger(2, priority);
			query.setString(3, guid);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Entry> getIntervalEntries(String startTime, String endTime){
		String hql="from Entry as entry where entry.entryPubDate between '"+startTime+"' and '"+endTime+"'";
		Session session=getSession();
		Query query=session.createQuery(hql);
		List<Entry> all=query.list();
		session.close();
		return all;
	}
	
}