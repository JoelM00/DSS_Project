package Business;

public class Posicao extends Localizacao {
    int seccao;

    public Posicao() {
        super(0);
        this.seccao = 0;
    }

    public Posicao(int n,int s) {
        super(n);
        this.seccao = s;
    }

    public int getSeccao() {
        return seccao;
    }

    public void setSeccao(int seccao) {
        this.seccao = seccao;
    }

    @Override
    public String toString() {
        return "Numero: "+this.getNumero()+"Posicao{" +
                "seccao=" + seccao +
                '}';
    }
}
