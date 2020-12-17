package Business;

public abstract class Localizacao implements Cloneable {

    private int numero;

    protected Localizacao(int numero) {

        this.numero = numero;
    }

    public int getNumero() {

        return numero;
    }

    public Localizacao clone(){
        try {
            return (Localizacao) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public abstract String toString();

    /* MÉTODOS NÃO USADOS MAS QUE PODEM (OU NÃO) FAZER FALTA */

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
