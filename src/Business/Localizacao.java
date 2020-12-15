package Business;

public abstract class Localizacao {
    private int numero;

    protected Localizacao(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public abstract String toString();
}
