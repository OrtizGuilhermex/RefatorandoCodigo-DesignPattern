package domain.strategy;

import domain.model.Frete;

public interface FreteStrategy {
    double calcularFrete (double peso);
}
