/* CartitemDAO.java

	Purpose:
		
	Description:
		
	History:
		Jul 30, 2012, Created by Ian Tsai(Zanyking)

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under ZOL in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.task.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.task.springdemo.bean.CartItem;



/**
 * @author Ian Y.T Tsai(zanyking)
 *
 */
@Repository
public class CartitemDemoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<CartItem> findByUser(Long userId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CartItem> criteria = cb.createQuery(CartItem.class);

		Root<CartItem> r = criteria.from(CartItem.class);
		criteria.
			select(r).
			where(cb.equal(r.get( "userId"), userId)).
			orderBy(cb.asc(r.get("createDate")));
		
		TypedQuery<CartItem> q = em.createQuery(criteria); 
		return q.getResultList();
	}
	
	
	public CartItem findByProduct(Long userId, Long prodId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CartItem> criteria = cb.createQuery(CartItem.class);

		Root<CartItem> r = criteria.from(CartItem.class);
		criteria.
			select(r).
			where(cb.and(
					cb.equal(r.get( "userId"), userId), 
					cb.equal(r.get( "product"), prodId))).
			orderBy(cb.asc(r.get("createDate")));
		
		TypedQuery<CartItem> q = em.createQuery(criteria);
		try{
			return q.getSingleResult();	
		}catch(NoResultException e){
			return null;
		}
	}
	
	
	@Transactional(value="txManager", propagation=Propagation.REQUIRES_NEW)
	public void insertUpdate(final CartItem citem){
		 em.persist(citem);
	}
	
	@Transactional(value="txManager", propagation=Propagation.REQUIRES_NEW)
	public void delete(final CartItem citem){
		em.remove(citem);
	}
	
	@Transactional(value="txManager", propagation=Propagation.REQUIRES_NEW)
	public void clear(final Long userId){
		Query query = em.createQuery("DELETE FROM CartItem c WHERE c.userId = :userId");
		query.setParameter("userId", userId);
		int deleted = query.executeUpdate();
	}
	
}
