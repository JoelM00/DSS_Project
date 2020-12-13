package Business;

import java.util.List;
import java.util.Queue;

public class Robot {
    private String codigo;
    private boolean disponivel;
    private Palete palete;
    private Percurso percuso;
    private Localizacao loc;

    public Robot(String codigo, boolean disponivel, Palete palete,Percurso percurso,Localizacao loc) {
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

    public Palete getPalete() {
        return palete;
    }

    public void setPalete(Palete palete) {
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

        int size = p.size();

        for (int i = 0; i<size-1; i++) {
            p.remove();
        }

        this.loc = p.remove();
    }

}
