package br.elwgomes.infra.exception.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe que representa a estrutura padrão de respostas de erro da API.
 *
 * <p>Essa classe encapsula as informações necessárias para detalhar erros
 * que ocorrem durante o processamento de requisições.</p>
 *
 **/
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    /**
     * Tipo do erro, geralmente usado para identificar o código de erro.
     * Exemplo: "NOT_FOUND", "BAD_REQUEST".
     */
    private String error;

    /**
     * Momento em que o erro ocorreu.
     * Usado para rastreamento e depuração.
     */
    private LocalDateTime timestamp;

    /**
     * Mensagem detalhada sobre o erro ocorrido.
     * Fornece informações úteis para o cliente entender o problema.
     */
    private String message;
}
