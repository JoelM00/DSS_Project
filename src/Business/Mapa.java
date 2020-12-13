package Business;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Mapa {
    private Map<Integer,Localizacao> posicoes;
    private Map<Integer,Integer> mapa;

    public Mapa() {
        this.mapa = new HashMap<>();
        this.mapa.put(1,0);
        this.mapa.put(2,0);
        this.mapa.put(3,0);
        this.mapa.put(4,1);
        this.mapa.put(5,2);
        this.mapa.put(6,3);
        this.mapa.put(9,4);
        this.mapa.put(8,5);
        this.mapa.put(7,6);
    }

    /*
    public Percurso voltaOrigem(Localizacao i) {
        Queue<Localizacao> res = new ArrayDeque<>();
        int o = i.getCorredor().getNumero();
        int corredorAnterior;

        while (o!=0) {
            corredorAnterior = this.mapa.get(o);

            if (o == 0) {
                res.add(new Localizacao(new Corredor(0,0)),0);
            }
            res.add(this.posicoes.get(corredorAnterior));

            o = corredorAnterior;
        }

    }*/


    public Percurso calculaPercusoArmazenamento(Localizacao i, Localizacao f) {
        Queue<Localizacao> res = new ArrayDeque<>();
        int o = i.getCorredor().getNumero();
        int d = f.getCorredor().getNumero();
        int corredorAnterior;

        while (o!=d) {
            corredorAnterior = this.mapa.get(d);

            res.add(this.posicoes.get(corredorAnterior));

            o = this.mapa.get(o);
        }

        Queue<Localizacao> inversa = new ArrayDeque<>();

        for (int k = res.size()-1; k>=0; k--) {
            inversa.add(res.remove());
        }

        return new Percurso(inversa);
    }



}
