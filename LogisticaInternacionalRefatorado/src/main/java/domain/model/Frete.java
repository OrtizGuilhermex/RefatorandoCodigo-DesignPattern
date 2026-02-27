package domain.model;

/**
 * SRP (Single Responsibility Principle): Esta classe tem uma única razão para mudar:
 * a estrutura dos dados de um Frete.
 */
public class Frete {

    private double peso;
    private double valorFrete;
    private double imposto;
    private String tipoImposto;

    // Construtores, Getters e Setters para encapsulamento de dados.
    public Frete(double peso, double valorFrete, double imposto,String tipoImposto) {
        this.peso = peso;
        this.valorFrete = valorFrete;
        this.imposto = imposto;
        this.tipoImposto = tipoImposto;
    }

    public Frete() {}


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public String getTipoImposto() {return tipoImposto;}

    public void setTipoImposto(String tipoImposto) {this.tipoImposto = tipoImposto;}
}
