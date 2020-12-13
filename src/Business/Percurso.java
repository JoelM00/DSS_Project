package Business;

import java.util.Queue;

public class Percurso {
    private Queue<Localizacao> percurso;

    public Percurso(Queue<Localizacao> percurso) {
        this.percurso = percurso;
    }

    public Queue<Localizacao> getPercurso() {
        return percurso;
    }

    public void setPercurso(Queue<Localizacao> percurso) {
        this.percurso = percurso;
    }
}
