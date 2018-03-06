package com.jun.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.Serializable;


public class MyRepoFactoryBean <R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {
    @SuppressWarnings("rawtypes")
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new CustomRepositoryFactory(em);
    }
    private static class CustomRepositoryFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {
        private final EntityManager em;
        public CustomRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }
        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new MyRepoImpl<T, I>(
                    (Class<T>) metadata.getDomainType(), em);
        }
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return MyRepoImpl.class;
        }
    }
}