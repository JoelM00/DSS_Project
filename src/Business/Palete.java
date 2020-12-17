package Business;

public class Palete implements Cloneable {
    private String codigo;
    private float altura;
    private Localizacao loc;
    private boolean disponivel;
    private boolean emTransporte;

    public Palete(String codigo, float altura, Localizacao loc, boolean disponivel,boolean emTransporte) {
        this.codigo = codigo;
        this.altura = altura;
        this.loc = loc;
        this.disponivel = disponivel;
        this.emTransporte = emTransporte;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Localizacao getLoc() {
        return loc;
    }

    public void setLoc(Localizacao loc) {
        this.loc = loc;
    }

    public boolean getEmTransporte() {
        return emTransporte;
    }

    public void setEmTransporte(boolean emTransporte) {
        this.emTransporte = emTransporte;
    }

    public void setTransporteConcluido(Localizacao loc) {
        this.loc = loc;
        this.disponivel = true;
        this.emTransporte = false;
    }

    @Override
    public Palete clone() {
        try {
            return (Palete) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Palete{" +
                "codigo='" + codigo + '\'' +
                ", altura=" + altura +
                ", loc=" + loc +
                ", disponivel=" + disponivel +
                ", emTransporte=" + emTransporte +
                '}';
    }

    /* MÉTODOS NÃO USADOS MAS QUE PODEM (OU NÃO) FAZER FALTA */

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
