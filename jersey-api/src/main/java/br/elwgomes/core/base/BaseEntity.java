package br.elwgomes.core.base;


import jakarta.persistence.*;
import lombok.*;

/**
 * Classe base para entidades jakarta.persistence.
 *
 * Essa classe define um identificador único ({@code id}) para todas as entidades
 * que a estendem. O campo {@code id} é gerado automaticamente pelo banco de dados.
 *
 * <p>Funcionalidades:</p>
 * - Estratégia de geração de ID: {@code GenerationType.IDENTITY}.
 * - Inclui métodos {@code equals} e {@code hashCode} com base no campo {@code id}.
 * - Getter e Setter automáticos utilizando Lombok.
 */
@Setter
@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

    /**
     * Identificador único da entidade.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

}
