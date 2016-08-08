package com.official.core.base.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.official.core.base.entity.Entity;
import com.official.core.base.repository.support.SimpleBaseRepository;

/**
 * 
 * @author ShawnXII
 * @Version 1.0
 * @param <R>
 * @param <M>
 * @param <ID>
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<M, ID>, M extends Entity<ID>, ID extends Serializable>
		extends JpaRepositoryFactoryBean<R, M, ID> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		return new MyRepositoryFactory<M, ID>(em);
	}

	private static class MyRepositoryFactory<M extends Entity<ID>, ID extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager em;

		public MyRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.em = entityManager;
		}

		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			@SuppressWarnings("unchecked")
			JpaEntityInformation<M, ID> entityInformation = getEntityInformation((Class<M>) metadata.getDomainType());
			return new SimpleBaseRepository<M, ID>(entityInformation, em);
		}
	}
}
