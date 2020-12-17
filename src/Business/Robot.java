package Business;

import java.util.Queue;

public class Robot {
    private String codigo;
    private boolean disponivel;
    private String palete;
    private boolean recolheuPalete;
    private Percurso percuso;
    private Localizacao loc;

    public Robot(String codigo, boolean disponivel,String palete,Percurso percurso,Localizacao loc) {
        this.codigo = codigo;
        this.disponivel = disponivel;
        this.palete = palete;
        this.recolheuPalete = false;
        this.percuso = percurso;
        this.loc = loc;
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

    public void setRecolheuPalete(boolean state) {
        this.recolheuPalete = state;
    }

    public boolean getRecolheuPalete() {
        return this.recolheuPalete;
    }

    public void setPercuso(Percurso percuso) {
        this.percuso = percuso;
    }

    public Localizacao getLoc() {
        return loc;
    }

    public void movimenta() {

        Queue<Localizacao> p = this.percuso.getPercurso();

        while (p.size()>0) {
            this.loc = p.remove();
            System.out.println("A percorrer -> " + this.loc.toString());
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public String toString() {
        return "Robot { "
                + "código = '" + codigo + '\''
                + ", disponivel = " + disponivel
                + ", palete = '" + palete + '\''
                + ", percuso = " + percuso
                + ", localização = " + loc
                + '}';
    }

    /* MÉTODOS NÃO USADOS MAS QUE PODEM (OU NÃO) FAZER FALTA */

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Percurso getPercuso() {
        return percuso;
    }

    public void setLoc(Localizacao loc) {
        this.loc = loc;
    }
}
