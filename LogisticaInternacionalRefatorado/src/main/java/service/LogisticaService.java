package service;

import domain.model.Frete;
import domain.strategy.FreteRepository;
import domain.strategy.FreteStrategy;
import domain.strategy.NotificacaoService;
import domain.strategy.TaxaStrategy;

public class LogisticaService {

    private final FreteRepository freteRepository;
    private final NotificacaoService notificacaoService;


    public LogisticaService(FreteRepository freteRepository, NotificacaoService notificacaoService) {
        this.freteRepository = freteRepository;
        this.notificacaoService = notificacaoService;
    }

    public void calcularEnvio(double peso, FreteStrategy estrategia, TaxaStrategy taxaStrategy){
        double valorBase = estrategia.calcularFrete(peso);
        double imposto = taxaStrategy.calcularImposto(valorBase);

        Frete frete = new Frete(peso,valorBase,imposto);

        freteRepository.salvar(frete);
        notificacaoService.enviarNotificacao("Frete internacional processado. Total: " + (valorBase + imposto));
    }
}
