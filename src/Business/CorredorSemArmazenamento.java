package Business;

public class CorredorSemArmazenamento extends Localizacao {

    public CorredorSemArmazenamento(int numero) {
        super(numero);
    }

    @Override
    public String toString() {
        return "Número: " + this.getNumero();
    }


}
