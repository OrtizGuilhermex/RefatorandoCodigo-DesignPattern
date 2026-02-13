package infra.transport;

import domain.strategy.FreteStrategy;

public class FreteTerrestreStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {
        return peso * 10;
    }
}
