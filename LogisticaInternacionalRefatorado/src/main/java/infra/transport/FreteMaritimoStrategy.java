package infra.transport;

import domain.strategy.FreteStrategy;

public class FreteMaritimoStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {
        return peso * 5.0;
    }
}