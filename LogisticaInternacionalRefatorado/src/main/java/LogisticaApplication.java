import domain.strategy.FreteRepository;
import domain.strategy.FreteStrategy;
import domain.strategy.NotificacaoService;
import infra.notification.EmailService;
import infra.persistence.SqlServerRepository;
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

        System.out.println("Escolha o modal: 1- Marítimo | 2- Aéreo");
        int opcao = scanner.nextInt();

        FreteStrategy estrategiaSelecionada = (opcao == 1)
                ? new FreteMaritimoStrategy()
                : new FreteAereoStrategy();

        service.calcularEnvio(peso, estrategiaSelecionada);

    }
}
