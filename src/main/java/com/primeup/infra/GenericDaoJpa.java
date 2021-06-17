package com.primeup.infra;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDaoJpa<T> {

	private Logger logger = Logger.getLogger(GenericDaoJpa.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public boolean incluir(T entidade) {
		try {
			entityManager.persist(entidade);
		} catch (Exception ex) {
			throw new DAOException("Erro na inclusao de objeto.", ex);
		}
		return true;
	}

	public boolean excluir(Class<T> c, Long id) {
		try {
			T entidade = entityManager.find(c, id);
			entityManager.remove(entidade);
		} catch (Exception ex) {
			throw new DAOException("Erro durante a exclusao.", ex);
		}
		return true;
	}

	public boolean alterar(T entidade) {
		try {
			entidade = entityManager.merge(entidade);
		} catch (Exception ex) {
			throw new DAOException("Erro durante a inclusao.", ex);
		}
		return true;
	}

	public T obterPorId(Class<T> c, Object id) throws DAOException {
		T entidade = null;

		try {
			logger.info("Resgatando entidade cujo id e " + id + " da classe: "
					+ c.getName());
			entidade = entityManager.find(c, id);
		} catch (RuntimeException ex) {
			throw new DAOException("Erro durante a pesquisa de objeto.", ex);
		}
		return entidade;
	}

	public List<T> obterTodos(Class<T> c) {
		logger.info("Resgatando todos as entidades da classe: " + c.getName());

		String entityName;

		entityName = c.getName().substring(c.getName().lastIndexOf('.') + 1);

		return obterEntidades("SELECT e FROM " + entityName + " e");
	}

	public List<T> obterEntidades(String queryString,
			final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		
		@SuppressWarnings("unchecked")
		List<T> l = query.getResultList();

		return l;
	}

	public T obterEntidade(String queryString, final Object... positionalParams) {
		Query query = entityManager.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		@SuppressWarnings("unchecked")
		T entidade = (T) query.getSingleResult();

		return entidade;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
