package Business;

public class Palete {
    private String codigo;
    private float altura;
    private Localizacao loc;
    private boolean disponivel;

    public Palete(String codigo, float altura, Localizacao loc, boolean disponivel) {
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

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
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

    @Override
    public String toString() {
        return "Palete{" +
                "codigo='" + codigo + '\'' +
                ", altura=" + altura +
                ", loc=" + loc.toString() +
                ", disponivel=" + disponivel +
                '}';
    }
}
