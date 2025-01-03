package br.elwgomes.infra.repository.base;

import br.elwgomes.core.base.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Classe abstrata que implementa operações básicas de persistência para entidades JPA.
 *
 * <p>Essa classe fornece métodos genéricos para salvar, buscar, atualizar e excluir
 * entidades no banco de dados. Todas as entidades devem estender {@code BaseEntity}.</p>
 *
 * @param <E> O tipo da entidade que esta classe irá gerenciar. Deve estender {@code BaseEntity}.
 */
public abstract class BaseRepository<E extends BaseEntity> {

    /**
     * Fábrica de gerenciadores de entidade utilizada para criar instâncias do {@code EntityManager}.
     */
    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jersey-api-pu");

    /**
     * Determina a classe da entidade gerenciada pelo repositório.
     *
     * @return A classe da entidade.
     * @throws IllegalStateException Se a classe da entidade não puder ser determinada.
     */
    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            return (Class<E>) parameterizedType.getActualTypeArguments()[0];
        }

        throw new IllegalStateException("A classe da entidade não pôde ser determinada.");
    }

    /**
     * Salva uma entidade no banco de dados.
     *
     * @param entity A entidade a ser salva.
     * @return A entidade salva.
     */
    public E save(E entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    /**
     * Recupera todas as entidades do banco de dados.
     *
     * @return Uma lista contendo todas as entidades encontradas.
     */
    public List<E> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
        Root<E> root = cq.from(getEntityClass());
        cq.select(root);
        List<E> results = em.createQuery(cq).getResultList();
        em.close();
        return results;
    }

    /**
     * Busca uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade.
     * @return A entidade encontrada ou {@code null} se nenhuma entidade for encontrada.
     */
    public E findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
            Root<E> root = cq.from(getEntityClass());
            cq.select(root).where(cb.equal(root.get("id"), id));
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Exclui uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade a ser excluída.
     */
    public void deleteById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        E entity = em.find(getEntityClass(), id);

        if (entity == null) {
            return;
        }

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Atualiza uma entidade no banco de dados.
     *
     * <p>Deve ser implementado pelas classes que herdam este repositório.</p>
     *
     * @param entity A entidade com os dados atualizados.
     * @return A entidade atualizada.
     */
    public abstract E update(E entity);
}