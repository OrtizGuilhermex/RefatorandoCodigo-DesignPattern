import java.util.Scanner;

public class SistemaLogisticaInternacional {

    public void processarFrete(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SISTEMA DE LOGÍSTICA (MONOLÍTICO) ---");
        System.out.println("Digite o peso da carga (kg): ");
        double peso = scanner.nextDouble();

        System.out.println("Digite o tipo de transporte (1- Maritimo, 2- Aereo): ");
        int tipo = scanner.nextInt();
    }

    public static void main(String[]args){
        SistemaLogisticaInternacional sistema = new SistemaLogisticaInternacional();
        sistema.processarFrete();
    }
}
