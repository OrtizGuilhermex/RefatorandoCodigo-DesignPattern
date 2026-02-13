package infra.transport;

import domain.strategy.FreteStrategy;

public class FreteAereoStrategy implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {return (peso * 15.0) + 500; }
}
