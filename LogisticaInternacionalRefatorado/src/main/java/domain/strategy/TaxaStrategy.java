package domain.strategy;

/**
 * ISP (Interface Segregation Principle): Criamos interfaces específicas para cada
 * comportamento (Taxa, Frete, Notificação), evitando interfaces "gordas".
 */
public interface TaxaStrategy {
    double calcularImposto(double valorBase);
}
