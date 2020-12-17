package Business;

import java.util.ArrayDeque;
import java.util.Queue;

public class Percurso {
    private Queue<Localizacao> percurso;

    public Percurso(Queue<Localizacao> percurso) {
        this.percurso = percurso;
    }

    public Percurso(Percurso p) {
        setPercurso(p.getPercurso());
    }

    public Queue<Localizacao> getPercurso() {
        Queue<Localizacao> aux = new ArrayDeque<>();

        for(Localizacao l : this.percurso)
            aux.add(l.clone());

        return aux;
    }

    public void setPercurso(Queue<Localizacao> percurso) {
        this.percurso = new ArrayDeque<>();

        for(Localizacao l : percurso)
            this.percurso.add(l.clone());
    }

    @Override
    public Percurso clone() {
        return new Percurso(this);
    }
    @Override
    public String toString() {
        return "Percurso { " + percurso + " }";
    }
}
