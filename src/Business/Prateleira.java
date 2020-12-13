package Business;

public class Prateleira {
    private String codigo;
    private float altura;
    private Localizacao loc;
    private boolean disponivel;

    public Prateleira(String codigo,float altura, Localizacao loc,boolean disponivel) {
        this.codigo = codigo;
        this.altura = altura;
        this.loc = loc;
        this.disponivel = disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public Localizacao getLoc() {
        return loc;
    }

    public void setLoc(Localizacao loc) {
        this.loc = loc;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
