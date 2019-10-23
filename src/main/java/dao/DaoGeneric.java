package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import maven_hibernate_2.maven_hibernate_2.HibernateUtil;

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();//Salvar no banco de dados
	}
}
