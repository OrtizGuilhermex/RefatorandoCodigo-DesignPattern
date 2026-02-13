package infra.tax;

import domain.strategy.TaxaStrategy;

public class InternacionalTaxStrategy implements TaxaStrategy {
    @Override
    public double calcularImposto(double valorBase) {
        return valorBase * 0.60;
    }
}
