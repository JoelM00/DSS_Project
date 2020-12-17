package Business;

import java.util.*;

public class Mapa {
    private Map<Integer,Localizacao> corredores;
    private Map<Integer,Integer> mapaArmazenamento;
    private Map<Integer,Integer> mapaRecolha;
    private int totalPrateleiras;

    private void criaMapaArmazenamento() {
        this.mapaArmazenamento.put(1,0);
        this.mapaArmazenamento.put(2,0);
        this.mapaArmazenamento.put(3,0);
        this.mapaArmazenamento.put(4,1);
        this.mapaArmazenamento.put(5,2);
        this.mapaArmazenamento.put(6,3);
        this.mapaArmazenamento.put(9,4);
        this.mapaArmazenamento.put(8,5);
        this.mapaArmazenamento.put(7,6);
    }

    private void criaMapaRecolha() {
        this.mapaRecolha.put(9,10);
        this.mapaRecolha.put(8,10);
        this.mapaRecolha.put(7,10);
        this.mapaRecolha.put(6,7);
        this.mapaRecolha.put(5,8);
        this.mapaRecolha.put(4,9);
        this.mapaRecolha.put(1,4);
        this.mapaRecolha.put(2,5);
        this.mapaRecolha.put(3,6);
    }


    private Map<String,Prateleira> criaPrateleiras(int x) {

        Map<String,Prateleira> res = new HashMap<>();

        for (int i = 0; i<x; i++) {
            String s = "P"+totalPrateleiras++;
            res.put(s,new Prateleira(s,(i+1)*10,true));
        }
        return res;
    }

    private void criaCorredores() {
        this.corredores.put(0,new Posicao(0,0));
        this.corredores.put(1,new CorredorSemArmazenamento(1));
        this.corredores.put(2,new CorredorSemArmazenamento(2));
        this.corredores.put(3,new CorredorSemArmazenamento(3));
        this.corredores.put(4,new CorredorComArmazenamento(4,criaPrateleiras(5)));
        this.corredores.put(5,new CorredorComArmazenamento(5,criaPrateleiras(5)));
        this.corredores.put(6,new CorredorComArmazenamento(6,criaPrateleiras(5)));
        this.corredores.put(7,new CorredorSemArmazenamento(7));
        this.corredores.put(8,new CorredorSemArmazenamento(8));
        this.corredores.put(9,new CorredorSemArmazenamento(9));
        this.corredores.put(10,new Posicao(10,0));
    }

    public Mapa() {
        this.mapaArmazenamento = new HashMap<>();
        this.mapaRecolha = new HashMap<>();
        this.corredores = new HashMap<>();
        this.criaMapaArmazenamento();
        this.criaMapaRecolha();
        this.criaCorredores();
        this.totalPrateleiras = 0;
    }

    public List<Posicao> listagem() {

        List<Posicao> res = new ArrayList<>();

        for (Localizacao l : this.corredores.values())

            if (l instanceof CorredorComArmazenamento) {
                int numero = l.getNumero();
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                Set<Integer> aux = c.listagem();

                for (Integer x : aux)
                    res.add(new Posicao(numero,x));
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

                    if (seccao != -1 && seccao != -2) {
                        return new Posicao(m.getKey(), seccao);
                    }
                }
            }
        }
        return null;
    }

    public String procuraPrateleira(float altura) {

        for (Localizacao l : this.corredores.values())

            if (l instanceof CorredorComArmazenamento) {
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                String codigo = c.algumaDisponivel(altura);

                if (codigo!=null)
                    return codigo;
            }

        return null;
    }

    private Queue<Localizacao> calculaPercursoRecolha(Localizacao i,Localizacao f) {

        Queue<Localizacao> res = new ArrayDeque<>();
        int o = i.getNumero();
        int d = f.getNumero();
        int corredorAnterior;

        res.add(new Posicao(o,0));

        while (o!=d) {
            corredorAnterior = this.mapaArmazenamento.get(o);
            res.add(new Posicao(corredorAnterior,0));
            o = corredorAnterior;
        }

        System.out.println(res.toString());

        return res;
    }

    private Queue<Localizacao> calculaPercursoArmazenamento(Localizacao i, Localizacao f) {

        List<Localizacao> res = new ArrayList<>();
        Queue<Localizacao> inversa = new ArrayDeque<>();
        int o = i.getNumero();
        int d = f.getNumero();
        int corredorAnterior;

        res.add(f);

        while (o!=d) {
            corredorAnterior = this.mapaArmazenamento.get(d);
            res.add(new Posicao(corredorAnterior,0));
            d = corredorAnterior;
        }

        System.out.println(res.toString());

        for (int k = res.size(); k>0; k--)
            inversa.add(res.get(k-1));

        System.out.println(inversa.toString());
        return inversa;
    }

    public Percurso calculaPercurso(Localizacao i,Localizacao f) {

        Queue<Localizacao> res = new ArrayDeque<>();
        int o = i.getNumero();
        int d = f.getNumero();

        if (o > d)
            res = calculaPercursoRecolha(i,f);

        else if (o < d)
            res = calculaPercursoArmazenamento(i,f);

        return new Percurso(res);
    }
}
