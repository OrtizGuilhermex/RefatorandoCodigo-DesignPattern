package domain.model;

public class Frete {

    private double peso;
    private double valorFrete;
    private double imposto;


    public Frete(double peso, double valorFrete, double imposto) {
        this.peso = peso;
        this.valorFrete = valorFrete;
        this.imposto = imposto;
    }

    public Frete() {
    }


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
}
