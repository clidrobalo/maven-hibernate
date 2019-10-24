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
	
	public E pesquisarPorObject(E entity) {
		Object idEntity = HibernateUtil.getPrimaryKey(entity);
		
		E entityGeneric = (E) entityManager.find(entity.getClass(), idEntity);
		
		return entityGeneric;
	}
	
	public E pesquisarPorId(Long id, E entity) {

		E entityGeneric = (E) entityManager.find(entity.getClass(), id);
		
		return entityGeneric;
	}
}
