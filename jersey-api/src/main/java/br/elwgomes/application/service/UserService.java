package br.elwgomes.application.service;

import br.elwgomes.core.exception.NotFoundException;
import br.elwgomes.core.model.User;
import br.elwgomes.infra.repository.UserRepository;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

/**
 * Serviço responsável pelas operações relacionadas a usuários.
 * Contém a lógica de negócios para salvar, buscar, atualizar e excluir usuários.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Construtor do serviço de usuários.
     *
     * @param userRepository O repositório de usuários utilizado pelo serviço.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário no sistema.
     *
     * @param entity O objeto do tipo {@code User} contendo os dados do usuário a ser salvo.
     * @return O objeto {@code User} salvo no banco de dados.
     */
    public User save(User entity) {
        return this.userRepository.save(entity);
    }

    /**
     * Recupera todos os usuários cadastrados no sistema.
     *
     * @return Uma lista contendo todos os objetos {@code User} registrados no banco de dados.
     */
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Exclui um usuário existente no sistema com base no ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @throws NotFoundException Se o usuário com o ID especificado não for encontrado.
     */
    public void deleteById(Long id) {
        var user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado: " + id);
        }
        this.userRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um usuário existente no sistema.
     *
     * @param id          O ID do usuário a ser atualizado.
     * @param updatedUser O objeto do tipo {@code User} contendo os novos dados do usuário.
     * @return O objeto {@code User} atualizado.
     * @throws NotFoundException Se o usuário com o ID especificado não for encontrado.
     */
    public User update(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            throw new NotFoundException("Usuário não encontrado: " + id);
        }

        existingUser.setFirst_name(updatedUser.getFirst_name());
        existingUser.setLast_name(updatedUser.getLast_name());
        existingUser.setEmail(updatedUser.getEmail());

        User updatedEntity = userRepository.update(existingUser);
        return updatedEntity;
    }
}