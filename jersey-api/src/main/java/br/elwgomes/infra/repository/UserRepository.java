package br.elwgomes.infra.repository;

import br.elwgomes.core.model.User;
import br.elwgomes.infra.repository.base.BaseRepository;
import jakarta.persistence.EntityManager;

/**
 * Repositório específico para a entidade {@code User}.
 *
 * <p>Fornece implementação para atualizar um usuário no banco de dados.</p>
 */
public class UserRepository extends BaseRepository<User> {

    /**
     * Atualiza os dados de um usuário existente no banco de dados.
     *
     * @param entity O objeto {@code User} com os novos dados a serem atualizados.
     * @return O objeto {@code User} atualizado no banco de dados.
     */
    @Override
    public User update(User entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // mescla os dados atualizados do usuário na instância gerenciada pelo banco
        User updatedUser = em.merge(entity);

        em.getTransaction().commit();
        em.close();

        return updatedUser;
    }
}
