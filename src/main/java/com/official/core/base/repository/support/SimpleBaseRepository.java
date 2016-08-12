package com.official.core.base.repository.support;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.google.common.collect.Lists;
import com.official.core.base.entity.Entity;
import com.official.core.base.repository.BaseRepository;
import com.official.core.base.repository.RepositoryHelper;
import com.official.core.base.repository.annotation.QueryJoin;
import com.official.core.base.repository.callback.SearchCallback;
import com.official.core.base.search.support.Searchable;
import com.official.core.util.Commutil;

/**
 * <p>
 * 抽象基础Custom Repository 实现
 * </p>
 * <p>
 * User: pengxinxin
 * <p>
 * Date: 13-1-15 下午7:33
 * <p>
 * Version: 1.0
 */
public class SimpleBaseRepository<M extends Entity<ID>, ID extends java.io.Serializable>
		extends SimpleJpaRepository<M, ID>implements BaseRepository<M, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7358016148021220709L;

	public static final String LOGIC_DELETE_ALL_QUERY_STRING = "update %s x set x.deleted=true where x in (?1)";
	public static final String DELETE_ALL_QUERY_STRING = "delete from %s x where x in (?1)";
	public static final String FIND_QUERY_STRING = "select x from %s x ";
	public static final String COUNT_QUERY_STRING = "select count(x) from %s x ";

	private final EntityManager em;
	private final JpaEntityInformation<M, ID> entityInformation;

	private final RepositoryHelper repositoryHelper;

	private Class<M> entityClass;
	private String entityName;
	private String idName;

	private Logger logger = LoggerFactory.getLogger(SimpleBaseRepository.class);

	/**
	 * 查询所有的QL
	 */
	private String findAllQL;
	/**
	 * 统计QL
	 */
	private String countAllQL;

	private String deleteQl;

	private String logicallyQl;

	private QueryJoin[] joins;

	private SearchCallback searchCallback = SearchCallback.DEFAULT;

	public SimpleBaseRepository(JpaEntityInformation<M, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityClass = this.entityInformation.getJavaType();
		this.entityName = this.entityInformation.getEntityName();
		this.idName = this.entityInformation.getIdAttributeNames().iterator().next();
		this.em = entityManager;
		this.repositoryHelper = new RepositoryHelper(entityClass);
		this.findAllQL = String.format(FIND_QUERY_STRING, entityName);
		this.countAllQL = String.format(COUNT_QUERY_STRING, entityName);
		this.deleteQl = String.format(DELETE_ALL_QUERY_STRING, this.entityName);
		this.logicallyQl = String.format(LOGIC_DELETE_ALL_QUERY_STRING, this.entityName);
	}

	public Class<M> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<M> entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getFindAllQL() {
		return findAllQL;
	}

	public void setFindAllQL(String findAllQL) {
		this.findAllQL = findAllQL;
	}

	public String getCountAllQL() {
		return countAllQL;
	}

	public void setCountAllQL(String countAllQL) {
		this.countAllQL = countAllQL;
	}

	public String getDeleteQl() {
		return deleteQl;
	}

	public void setDeleteQl(String deleteQl) {
		this.deleteQl = deleteQl;
	}

	public String getLogicallyQl() {
		return logicallyQl;
	}

	public void setLogicallyQl(String logicallyQl) {
		this.logicallyQl = logicallyQl;
	}

	public QueryJoin[] getJoins() {
		return joins;
	}

	public void setJoins(QueryJoin[] joins) {
		this.joins = joins;
	}

	public SearchCallback getSearchCallback() {
		return searchCallback;
	}

	public void setSearchCallback(SearchCallback searchCallback) {
		this.searchCallback = searchCallback;
	}

	public EntityManager getEm() {
		return em;
	}

	public JpaEntityInformation<M, ID> getEntityInformation() {
		return entityInformation;
	}

	public RepositoryHelper getRepositoryHelper() {
		return repositoryHelper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M save(M entity) {
		if (entity.isNew()) {
			Long id = Commutil.generateId();
			Object idType = entity.getId();
			if (idType instanceof Long) {
				entity.setId((ID) id);
			} else {
				throw new IllegalArgumentException("Unsupported ID type,ID Type:[" + idType.getClass() + "]");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("new data,entity Generate id:[{}],entity:[{}]", id, entity);
			}
			this.em.persist(entity);
			return entity;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Modify data,entity:[{}]", entity);
		}
		return this.em.merge(entity);
	}

	@Override
	public List<M> save(Collection<M> entitys) {
		List<M> result = Lists.newArrayList();
		if (entitys != null) {
			for (M entity : entitys) {
				result.add(save(entity));
			}
		}
		return result;
	}

	@Override
	public void delete(ID[] ids) {
		for(ID id:ids){
			super.delete(id);
		}
	}

	@Override
	public void delete(Collection<M> entitys) {
		for(M entity:entitys){
			super.delete(entity);
		}
	}

	@Override
	public long count(Searchable searchable) {
		return this.repositoryHelper.count(this.countAllQL, searchable, searchCallback);
	}

	@Override
	public Page<M> findAll(Searchable searchable) {
		List<M> list = this.repositoryHelper.findAll(this.findAllQL, searchable, searchCallback);
		long total = searchable.hasPageable() ? count(searchable) : list.size();
		return new PageImpl<M>(list, searchable.getPage(), total);
	}

	@Override
	public List<M> findAllWithSort(Searchable searchable) {
		searchable.removePageable();
		return this.repositoryHelper.findAll(this.findAllQL, searchable, searchCallback);
	}

	@Override
	public Page<M> findAllWithPage(Searchable searchable) {
		searchable.removeSort();
		return findAll(searchable);
	}

	@Override
	public List<M> findAllWithNoPageNoSort(Searchable searchable) {
		searchable.removePageable();
		searchable.removeSort();
		return this.repositoryHelper.findAll(this.findAllQL, searchable, searchCallback);
	}

	@Override
	public List<M> findAll(String definedSql, Object... params) {
		return repositoryHelper.findAll(definedSql, params);
	}

	@Override
	public long count(String definedSql, Object... params) {
		return repositoryHelper.count(definedSql, params);
	}

}
