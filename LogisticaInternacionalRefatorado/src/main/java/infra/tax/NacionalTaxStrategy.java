package infra.tax;

import domain.strategy.TaxaStrategy;

public class NacionalTaxStrategy implements TaxaStrategy {
    @Override
    public double calcularImposto(double valorBase) {
        return valorBase * 0.12;
    }
}
