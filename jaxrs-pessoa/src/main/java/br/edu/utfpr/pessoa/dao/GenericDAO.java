package br.edu.utfpr.pessoa.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class GenericDAO<PK, T> {
	protected EntityManager entityManager = null;
	
	public GenericDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
		
	public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }
	
	public long count() {
		TypedQuery<Long> query = entityManager.createQuery(
				 "SELECT COUNT(c) FROM " + getTypeClass().getSimpleName() + " c",  Long.class);
		 return query.getSingleResult();
	}
	
	public boolean contains(T entity) {
		return entityManager.contains(entity);
	}
 
    public void save(T entity) {
    	entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
 
    public void update(T entity) {
    	entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
 
    public void delete(T entity) {
    	entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
 
    public List<T> findAll() {
    	return entityManager.createQuery(("SELECT e FROM " + getTypeClass().getSimpleName() + " e")).getResultList();
    }
 
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}