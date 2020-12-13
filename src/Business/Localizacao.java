package Business;

public class Localizacao {
    private Corredor corredor;
    private int seccao;

    public Localizacao(Corredor corredor,int seccao) {
        this.corredor = corredor;
        this.seccao = seccao;
    }

    public Corredor getCorredor() {
        return corredor;
    }

    public void setCorredor(Corredor corredor) {
        this.corredor = corredor;
    }

    public int getSeccao() {
        return seccao;
    }

    public void setSeccao(int seccao) {
        this.seccao = seccao;
    }

    public String toString() {
        return "Corredor numero: "+this.corredor.getNumero() + "Seccao numero: "+this.seccao;
    }
}
