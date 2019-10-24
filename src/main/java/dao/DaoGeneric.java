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
	
	//Salva ou atualiza os dados
	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entity = entityManager.merge(entidade);
		transaction.commit();//Salvar no banco de dados
		return entity;
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

	public void deletePorId(Long id, E entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		
		try {
			transaction.begin();
			entityManager.createNativeQuery("delete from " + entity.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate();
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
