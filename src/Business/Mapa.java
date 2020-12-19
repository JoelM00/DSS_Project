package Business;

import java.util.*;

public class Mapa {
    private final Map<Integer,Localizacao> localizacao;
    private final Map<Integer,Integer> mapaArmazenamento;
    private final Map<Integer,Integer> mapaRecolha;
    private final Map<String,Prateleira> prateleiras;
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


    private List<String> criaPrateleiras(int x) {
        List<String> codigos = new ArrayList<>();
        for (int i = 0; i<x; i++) {
            String s = "P"+totalPrateleiras++;
            codigos.add(s);
            this.prateleiras.put(s,new Prateleira(s,(i+1)*10,true));
        }
        return codigos;
    }

    private void criaCorredores() {
        List<String> c1 = criaPrateleiras(5);
        List<String> c2 = criaPrateleiras(5);
        List<String> c3 = criaPrateleiras(5);
        this.localizacao.put(0,new Posicao(0,0));
        this.localizacao.put(1,new CorredorSemArmazenamento(1));
        this.localizacao.put(2,new CorredorSemArmazenamento(2));
        this.localizacao.put(3,new CorredorSemArmazenamento(3));
        this.localizacao.put(4,new CorredorComArmazenamento(4,c1));
        this.localizacao.put(5,new CorredorComArmazenamento(5,c2));
        this.localizacao.put(6,new CorredorComArmazenamento(6,c3));
        this.localizacao.put(7,new CorredorSemArmazenamento(7));
        this.localizacao.put(8,new CorredorSemArmazenamento(8));
        this.localizacao.put(9,new CorredorSemArmazenamento(9));
        this.localizacao.put(10,new Posicao(10,0));
    }

    public Mapa() {
        this.mapaArmazenamento = new HashMap<>();
        this.mapaRecolha = new HashMap<>();
        this.localizacao = new HashMap<>();
        this.prateleiras = new HashMap<>();
        this.totalPrateleiras = 1;
        this.criaMapaArmazenamento();
        this.criaMapaRecolha();
        this.criaCorredores();
    }

    public Posicao procuraPorCodigo(String codigo) {
        for (Localizacao l : this.localizacao.values()) {
            if (l instanceof CorredorComArmazenamento) {
                CorredorComArmazenamento c = (CorredorComArmazenamento) l;
                int x = c.posicao(codigo);
                if (x != -1) {
                    return new Posicao(l.getNumero(),x);
                }
            }
        }
        return null;
    }

    public List<Posicao> listagem() {
        List<Posicao> res = new ArrayList<>();

        for (Prateleira p : this.prateleiras.values())
            if (!p.getDisponivel()) {
                res.add(this.procuraPorCodigo(p.getCodigo()));
            }

        return res;
    }

    public Localizacao reservaPrateleiraPorCodigo(String codigo) {
        this.prateleiras.get(codigo).setDisponivel(false);
        return this.procuraPorCodigo(codigo);
    }

    public String procuraPrateleira(float altura) {
        for (Prateleira p : this.prateleiras.values()) {
            if (p.getDisponivel() && (altura <= p.getAltura())) {
                return p.getCodigo();
            }
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
