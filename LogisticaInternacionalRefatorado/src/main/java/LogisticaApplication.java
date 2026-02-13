import domain.strategy.FreteRepository;
import domain.strategy.FreteStrategy;
import domain.strategy.NotificacaoService;
import domain.strategy.TaxaStrategy;
import infra.notification.EmailService;
import infra.persistence.SqlServerRepository;
import infra.tax.InternacionalTaxStrategy;
import infra.tax.NacionalTaxStrategy;
import infra.transport.FreteAereoStrategy;
import infra.transport.FreteMaritimoStrategy;
import service.LogisticaService;

import java.util.Scanner;

public class LogisticaApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        FreteRepository freteRepository = new SqlServerRepository();
        NotificacaoService notificacaoService = new EmailService();
        LogisticaService service = new LogisticaService(freteRepository,notificacaoService);

        System.out.println("--- INTERGLOBAL LOGISTICS ---");
        System.out.print("Peso da carga: ");
        double peso = scanner.nextDouble();

        System.out.println("O frete é: 1- Internacional | 2- Nacional");
        int tipoImposto = scanner.nextInt();

        TaxaStrategy taxaSelecionada = (tipoImposto == 1)
                ? new InternacionalTaxStrategy()
                : new NacionalTaxStrategy();

        System.out.println("Escolha a forma de envio: 1- Marítimo | 3- Aéreo");
        int opcao = scanner.nextInt();

        FreteStrategy estrategiaSelecionada = (opcao == 1)
                ? new FreteMaritimoStrategy()
                : new FreteAereoStrategy();

        service.calcularEnvio(peso, estrategiaSelecionada, taxaSelecionada);

    }
}
