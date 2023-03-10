package com.romanredziuk.spring.online_store.persistence.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.romanredziuk.spring.online_store.persistence.dao.PurchaseStatusDao;
import com.romanredziuk.spring.online_store.persistence.dto.PurchaseStatusDto;

@Repository
public class JpaPurchaseStatusDao implements PurchaseStatusDao {

	@Override
	public PurchaseStatusDto getPurchaseStatusById(Integer id) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("persistence-unit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			PurchaseStatusDto purchaseStatus = em.find(PurchaseStatusDto.class, id);

			em.getTransaction().commit();

			return purchaseStatus;
		} finally {
			if (emf != null) {
				emf.close();
			}
			if (em != null) {
				em.close();
			}
		}
	}

}
