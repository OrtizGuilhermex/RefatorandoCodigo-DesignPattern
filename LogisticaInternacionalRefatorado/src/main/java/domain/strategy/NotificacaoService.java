package domain.strategy;

/**
 * DIP (Dependency Inversion Principle): O Service não dependerá de "EmailService",
 * mas sim desta interface. O módulo de alto nível não depende do de baixo nível.
 */
public interface NotificacaoService {
    void enviarNotificacao(String mensagem);
}
