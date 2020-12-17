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

    public Posicao(Posicao p) {
        super(p.getNumero());
        this.seccao = p.getSeccao();
    }

    public int getSeccao() {
        return seccao;
    }
    public Posicao clone() {return new Posicao(this); }

    @Override
    public String toString() {
        return "Número: " + this.getNumero() + " Posição { "
                + "secção = " + seccao + '}';
    }

    /* MÉTODOS NÃO USADOS MAS QUE PODEM (OU NÃO) FAZER FALTA */

    public void setSeccao(int seccao) {
        this.seccao = seccao;
    }
}
