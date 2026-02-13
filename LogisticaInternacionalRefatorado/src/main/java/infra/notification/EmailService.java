package infra.notification;

import domain.strategy.NotificacaoService;

public class EmailService implements NotificacaoService {
    @Override
    public void enviarNotificacao(String mensagem) {System.out.println("Enviando E-mail: " + mensagem);}
}
