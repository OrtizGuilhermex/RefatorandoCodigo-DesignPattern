package infra.tax;

import domain.strategy.TaxaStrategy;

/**
 * OCP (Open/Closed Principle): Se amanhã surgir uma taxa para o Mercosul,
 * não alteramos esta classe; criamos uma nova implementação de TaxaStrategy.
 */
public class InternacionalTaxStrategy implements TaxaStrategy {
    @Override
    public double calcularImposto(double valorBase) {
        return valorBase * 0.60;
    }
}
