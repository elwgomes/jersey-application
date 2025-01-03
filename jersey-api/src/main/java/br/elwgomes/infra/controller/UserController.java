package br.elwgomes.infra.controller;

import br.elwgomes.application.service.UserService;
import br.elwgomes.core.model.User;
import br.elwgomes.infra.repository.UserRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas a usuários.
 * Oferece endpoints para criação, recuperação, atualização e exclusão de usuários.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private UserService userService;

    /**
     * Construtor padrão do controlador. Inicializa o serviço de usuários.
     */
    public UserController() {
        this.userService = new UserService(new UserRepository());
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * @return Uma lista contendo todos os usuários.
     */
    @GET
    public List<User> findAll() {
        var response = userService.findAll();
        return response;
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param user O objeto do tipo {@code User} contendo os dados do novo usuário.
     * @return Um objeto {@code Response} contendo o usuário criado e o status HTTP 201 (Created).
     */
    @POST
    public Response create(User user) {
        var savedUser = userService.save(user);
        return Response.status(Response.Status.CREATED).entity(savedUser).build();
    }

    /**
     * Atualiza um usuário existente no sistema.
     *
     * @param id          O ID do usuário a ser atualizado.
     * @param updatedUser O objeto do tipo {@code User} contendo os novos dados do usuário.
     * @return Um objeto {@code Response} contendo o usuário atualizado e o status HTTP 200 (OK).
     */
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, User updatedUser) {
        var updatedEntity = userService.update(id, updatedUser);
        return Response.ok(updatedEntity).build();
    }

    /**
     * Exclui um usuário existente no sistema.
     *
     * @param id O ID do usuário a ser excluído.
     * @return Um objeto {@code Response} com o status HTTP 204 (No Content) se a exclusão for bem-sucedida.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        userService.deleteById(id);
        return Response.noContent().build();
    }
}