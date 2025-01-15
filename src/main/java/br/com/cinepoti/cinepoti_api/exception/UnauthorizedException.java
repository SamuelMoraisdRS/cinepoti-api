package br.com.cinepoti.cinepoti_api.exception;

/**
 * Exceção personalizada lançada quando um usuário não está autorizado a realizar uma ação.
 */
public class UnauthorizedException extends RuntimeException {

    // Construtor sem detalhes adicionais
    public UnauthorizedException() {
        super("Unauthorized access.");
    }

    // Construtor que aceita uma mensagem personalizada
    public UnauthorizedException(String message) {
        super(message);
    }

    // Construtor que aceita uma mensagem personalizada e a causa da exceção
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor que aceita apenas a causa
    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
