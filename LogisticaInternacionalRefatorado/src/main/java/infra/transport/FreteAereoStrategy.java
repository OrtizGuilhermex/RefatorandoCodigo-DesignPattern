package infra.transport;

import domain.strategy.FreteStrategy;

/**
 * LSP (Liskov Substitution Principle): Esta classe pode substituir qualquer
 * referência a 'FreteStrategy' sem quebrar o comportamento esperado do sistema.
 */
public class FreteAereoStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {return (peso * 35.0) + 500; }
}
