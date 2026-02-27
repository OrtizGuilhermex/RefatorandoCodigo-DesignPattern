import domain.strategy.FreteStrategy;
import domain.strategy.NotificacaoService;
import domain.strategy.TaxaStrategy;
import infra.notification.EmailService;
import infra.persistence.FreteRepository;
import infra.tax.InternacionalTaxStrategy;
import infra.tax.NacionalTaxStrategy;
import infra.transport.FreteAereoStrategy;
import infra.transport.FreteMaritimoStrategy;
import infra.transport.FreteTerrestreStrategy;
import service.LogisticaService;

import java.util.Scanner;

/**
 * Composição da aplicação.
 * Responsável por instanciar as dependências (Injeção) e gerenciar a entrada do usuário.
 */
public class LogisticaApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Montagem do grafo de objetos (Em frameworks como Spring, isso é automático).
        domain.strategy.FreteRepository freteRepository = new FreteRepository();
        NotificacaoService notificacaoService = new EmailService();
        LogisticaService service = new LogisticaService(freteRepository,notificacaoService);

        // Lógica de seleção de estratégias baseada no input do usuário
        System.out.println("--- INTERGLOBAL LOGISTICS ---");
        System.out.print("Peso da carga(Kg): ");
        double peso = scanner.nextDouble();

        System.out.println("O frete é: 1- Internacional | 2- Nacional");
        int tipoImposto = scanner.nextInt();

        TaxaStrategy taxaSelecionada = (tipoImposto == 1)
                ? new InternacionalTaxStrategy()
                : new NacionalTaxStrategy();

        System.out.println("Escolha a forma de envio: 1- Marítimo | 2- Terrestre | 3- Aéreo");
        int formaEnvio = scanner.nextInt();

        String descricaoTipoImposto = (tipoImposto == 1) ? "Internacional" : "Nacional";

        FreteStrategy estrategiaSelecionada;

        switch (formaEnvio){
            case 1: estrategiaSelecionada = new FreteMaritimoStrategy(); break;
            case 2: estrategiaSelecionada = new FreteTerrestreStrategy();break;
            case 3: estrategiaSelecionada = new FreteAereoStrategy();break;
            default: throw new IllegalArgumentException("Forma de envio Inválida");
        }

        // O comando final é limpo e focado no propósito do negócio.
        service.calcularEnvio(peso, estrategiaSelecionada, taxaSelecionada,descricaoTipoImposto);

    }
}
