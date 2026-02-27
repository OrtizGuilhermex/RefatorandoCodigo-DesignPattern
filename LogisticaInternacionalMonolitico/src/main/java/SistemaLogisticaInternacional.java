import java.util.Scanner;

// CLASSE MONOLÍTICA: Centraliza entrada de dados, regras de negócio,
// cálculo de impostos, persistência e comunicação.
public class SistemaLogisticaInternacional {

    // O metodo faz TUDO: lê do console, calcula, salva e envia e-mail.
    public void processarFrete(){
        Scanner scanner = new Scanner(System.in);

        // --- ACOPLAMENTO COM I/O ---
        // Se precisarmos mudar para uma interface web ou API, este código quebra.
        System.out.println("--- SISTEMA DE LOGÍSTICA (MONOLÍTICO) ---");
        System.out.println("Digite o peso da carga (kg): ");
        double peso = scanner.nextDouble();

        System.out.println("Digite o tipo de transporte (1- Marítimo, 2- Aéreo): ");
        int tipo = scanner.nextInt();

        double custoTotal = 0;

        // --- LÓGICA DE NEGÓCIO HARDCODED ---
        // Números mágicos (5.0, 15.0, 500) dificultam a manutenção.
        // Se surgir um transporte Terrestre, precisaremos de mais um "else if" (viola o Open/Closed Principle).
        if (tipo == 1){
            custoTotal = peso * 5.0;
            System.out.println("Processando via Marítimo...");
        } else if (tipo == 2){
            custoTotal = peso * 15.0;
            custoTotal += 500;
            System.out.println("Processando via Aéreo...");
        }

        // --- LÓGICA TRIBUTÁRIA MISTURADA ---
        // O cálculo do imposto está fixo e misturado ao fluxo principal.
        double imposto = custoTotal * 0.60;
        custoTotal += imposto;

        // --- INFRAESTRUTURA FANTASMA ---
        // Simulação de banco de dados e e-mail dentro do metodo de processamento.
        // Se o banco mudar para NoSQL ou o serviço de e-mail mudar, alteramos a regra de negócio.
        System.out.println("Gerando manifesto unificado de Porto/Aeroporto/Trem...");
        System.out.println("Salvando no Banco de Dados SQL Server: INSERT INTO FRETE...");
        System.out.println("Enviando E-mail para o cliente: Seu frete de R$" + custoTotal + " foi processado.");
        System.out.println("Finalizado com sucesso!");
    }

    public static void main(String[]args){
        SistemaLogisticaInternacional sistema = new SistemaLogisticaInternacional();
        sistema.processarFrete();
    }
}
