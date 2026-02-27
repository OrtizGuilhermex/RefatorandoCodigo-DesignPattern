package service;

import domain.model.Frete;
import domain.strategy.FreteRepository;
import domain.strategy.FreteStrategy;
import domain.strategy.NotificacaoService;
import domain.strategy.TaxaStrategy;

/**
 * Classe Orquestradora (Casos de Uso).
 * Não conhece detalhes de SQL ou UI. Apenas coordena as interfaces para realizar o cálculo.
 */
public class LogisticaService {

    // DIP: O serviço depende de abstrações (Interfaces), não de classes concretas.
    private final FreteRepository freteRepository;
    private final NotificacaoService notificacaoService;

    /**
     * Injeção de Dependência: As dependências são passadas no construtor.
     * Isso permite que o sistema seja flexível e altamente testável.
     */
    public LogisticaService(FreteRepository freteRepository, NotificacaoService notificacaoService) {
        this.freteRepository = freteRepository;
        this.notificacaoService = notificacaoService;
    }

    public void calcularEnvio(double peso, FreteStrategy estrategia, TaxaStrategy taxaStrategy, String tipoImposto){
        // OCP: O método calcularEnvio está fechado para modificação, mas aberto para
        // expansão através de novas estratégias passadas via parâmetro.
        double valorBase = estrategia.calcularFrete(peso);
        double imposto = taxaStrategy.calcularImposto(valorBase);

        Frete frete = new Frete(peso,valorBase,imposto,tipoImposto);

        freteRepository.salvar(frete);
        notificacaoService.enviarNotificacao("Frete processado. Total: " + (valorBase + imposto));
    }
}
