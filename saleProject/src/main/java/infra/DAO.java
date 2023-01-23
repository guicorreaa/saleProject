package infra;

import java.util.List;

import aplicacao.Cliente;
import aplicacao.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("saleProject");
		} catch (Exception e) {
		}
	}

	public DAO() {
		this(null);
	}

	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}

	public DAO<E> openT() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> closeT() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> include(E entidade) {
		em.persist(entidade);
		return this;
	}
	

	public DAO<E> atomic_Incluse(E entidade) {
		return this.openT().include(entidade).closeT();
	}

	public List<E> get_All() {
		return this.getAll(100, 0);

	}

	public List<E> getAll(int qtt, int displac) {
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula.");
		}
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtt);
		query.setFirstResult(displac);
		return query.getResultList();
	}

	public E consultarUm(String nomeConsulta, Object... params) {
		List<E> lista = consultar(nomeConsulta, params);
		return lista.isEmpty() ? null : lista.get(0);
	}

	public void close() {
		em.close();
	}

	public List<E> consultar(String nomeConsulta, Object... params) {
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);

		for (int i = 0; i < params.length; i += 2) {
			query.setParameter(params[i].toString(), params[i + 1]);
		}

		return query.getResultList();
	}

	public E getForId(Object id) {
		return em.find(classe, id);
	}

	public List<E> getNomesClientePedido() {
		String jpql = "select e from Pedido e";

		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public List<E> getCliente() {
		String jpql = "select e from Cliente e ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	public void removerBancoPedido(Long id) {
		Pedido p = em.find(Pedido.class, id);
		em.remove(p);
	}
	
	public void removerBancoCliente(Long id) {
		Cliente p = em.find(Cliente.class, id);
		em.remove(p);
	}

	public List<Pedido> pesquisarTablePedido(String nome) {
		String jpql = "SELECT e FROM Pedido e where e.nomeCliente = '" + nome + "' ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public List<Cliente> pesquisarTableCliente(String nome) {
		String jpql = "SELECT e FROM Cliente e where e.nomeC = '" + nome + "' ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}



}
