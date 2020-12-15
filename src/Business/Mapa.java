package Business;

import java.util.*;

public class Mapa {
    private Map<Integer,Localizacao> corredores;
    private Map<Integer,Integer> mapa;

    private void criaMapa() {
        this.mapa.put(0,0);
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

    private Map<String,Prateleira> criaPrateleiras(int x) {
        Map<String,Prateleira> res = new HashMap<>();
        for (int i = 0; i<x; i++) {
            String s = "P"+i;
            res.put(s,new Prateleira(s,50,true));
        }
        return res;
    }

    private void criaCorredores() {
        this.corredores.put(1,new CorredorSemArmazenamento(1));
        this.corredores.put(2,new CorredorSemArmazenamento(2));
        this.corredores.put(3,new CorredorSemArmazenamento(3));
        this.corredores.put(4,new CorredorComArmazenamento(4,criaPrateleiras(5)));
        this.corredores.put(5,new CorredorComArmazenamento(5,criaPrateleiras(5)));
        this.corredores.put(6,new CorredorComArmazenamento(6,criaPrateleiras(5)));
        this.corredores.put(7,new CorredorSemArmazenamento(7));
        this.corredores.put(8,new CorredorSemArmazenamento(8));
        this.corredores.put(9,new CorredorSemArmazenamento(9));
    }


    public Mapa() {
        this.mapa = new HashMap<>();
        this.corredores = new HashMap<>();
        this.criaMapa();
        this.criaCorredores();
    }

    public List<Posicao> listagem() {
        List<Posicao> res = new ArrayList<>();
        for (Localizacao l : this.corredores.values()) {
            if (l instanceof CorredorComArmazenamento) {
                int numero = l.getNumero();
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                Set<Integer> aux = c.listagem();
                for (Integer x : aux) {
                    res.add(new Posicao(numero,x));
                }
            }
        }
        return res;
    }

    public Localizacao reservaPrateleiraPorCodigo(String codigo) {
        for (Map.Entry<Integer,Localizacao> m : this.corredores.entrySet()) {
            Localizacao l = m.getValue();
            if (l instanceof CorredorComArmazenamento) {
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                if (c.existePrateleira(codigo)) {
                    int seccao = c.reservaPorCodigo(codigo);
                    return new Posicao(m.getKey(),seccao);
                }
            }
        }
        return null;
    }

    public String procuraPrateleira() {
        for (Localizacao l : this.corredores.values()) {
            if (l instanceof CorredorComArmazenamento) {
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                String codigo = c.algumaDisponivel();
                if (codigo!=null) {
                    return codigo;
                }
            }
        }
        return null;
    }

    public Percurso calculaPercurso(Localizacao i, Localizacao f) {
        Queue<Localizacao> res = new ArrayDeque<>();
        int o = i.getNumero();
        int d = f.getNumero();
        int corredorAnterior;

        res.add(f);

        while (o!=d) {
            corredorAnterior = this.mapa.get(d);

            res.add(this.corredores.get(corredorAnterior));

            o = this.mapa.get(o);
        }

        Queue<Localizacao> inversa = new ArrayDeque<>();

        for (int k = res.size()-1; k>=0; k--) {
            inversa.add(res.remove());
        }

        res.add(f);
        return new Percurso(res);
    }

    public List<String> procurasPrateleirasDisponiveis() {
        List<String> res = new ArrayList<>();
        for (Localizacao l : this.corredores.values()) {
            if (l instanceof CorredorComArmazenamento) {
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                List<String> codigos = c.disponiveis();
                res.addAll(codigos);
            }
        }
        return res;
    }

}
