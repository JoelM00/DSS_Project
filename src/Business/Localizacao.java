package Business;

public abstract class Localizacao implements Cloneable {
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

    public Localizacao clone(){
        try {
            return (Localizacao) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public abstract String toString();



}
