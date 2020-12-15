package Business;

import java.util.Queue;

public class Robot {
    private String codigo;
    private boolean disponivel;
    private String palete;
    private Percurso percuso;
    private Localizacao loc;

    public Robot(String codigo, boolean disponivel,String palete,Percurso percurso,Localizacao loc) {
        this.codigo = codigo;
        this.disponivel = disponivel;
        this.palete = palete;
        this.percuso = percurso;
        this.loc = loc;
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

    public String getPalete() {
        return palete;
    }

    public void setPalete(String palete) {
        this.palete = palete;
    }

    public Percurso getPercuso() {
        return percuso;
    }

    public void setPercuso(Percurso percuso) {
        this.percuso = percuso;
    }

    public Localizacao getLoc() {
        return loc;
    }

    public void setLoc(Localizacao loc) {
        this.loc = loc;
    }

    public void movimenta() {
        Queue<Localizacao> p = this.percuso.getPercurso();

        for (int i = 0; i<p.size(); i++) {
            this.loc = p.remove();
        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "codigo='" + codigo + '\'' +
                ", disponivel=" + disponivel +
                ", palete='" + palete + '\'' +
                ", percuso=" + percuso +
                ", loc=" + loc +
                '}';
    }
}
