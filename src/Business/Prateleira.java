package Business;

public class Prateleira {
    private String codigo;
    private float altura;
    private boolean disponivel;

    public Prateleira(String codigo,float altura,boolean disponivel) {
        this.codigo = codigo;
        this.altura = altura;
        this.disponivel = disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Prateleira clone() {
        return new Prateleira(this.codigo,this.altura,this.disponivel);
    }

    /* MÉTODOS NÃO USADOS MAS QUE PODEM (OU NÃO) FAZER FALTA */

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
