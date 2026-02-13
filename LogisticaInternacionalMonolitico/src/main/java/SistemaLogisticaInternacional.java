import java.util.Scanner;

public class SistemaLogisticaInternacional {

    public void processarFrete(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SISTEMA DE LOGÍSTICA (MONOLÍTICO) ---");
        System.out.println("Digite o peso da carga (kg): ");
        double peso = scanner.nextDouble();

        System.out.println("Digite o tipo de transporte (1- Marítimo, 2- Aéreo): ");
        int tipo = scanner.nextInt();

        double custoTotal = 0;

        if (tipo == 1){
            custoTotal = peso * 5.0;
            System.out.println("Processando via Marítimo...");
        } else if (tipo == 2){
            custoTotal = peso * 15.0;
            custoTotal += 500;
            System.out.println("Processando via Aéreo...");
        }

        double imposto = custoTotal * 0.60;
        custoTotal += imposto;

        System.out.println("Gerando manifesto unificado de Porto/Aeroporto/Trem...");
    }

    public static void main(String[]args){
        SistemaLogisticaInternacional sistema = new SistemaLogisticaInternacional();
        sistema.processarFrete();
    }
}
