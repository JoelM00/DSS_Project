package Business;

import java.util.*;

public class CorredorComArmazenamento extends Localizacao {
    private Map<String,Prateleira> prateleira;
    private Map<Integer,String> posicaoDaPrateleira;

    public CorredorComArmazenamento(int numero,Map<String,Prateleira> prateleira) {
        super(numero);
        this.prateleira = prateleira;
        this.posicaoDaPrateleira = new HashMap<>();
        int cont = 0;
        for (String s : this.prateleira.keySet()) {
            this.posicaoDaPrateleira.put(cont++,s);
        }
    }

    public Set<Integer> listagem() {
        Set<Integer> res = new HashSet<>();
        for (Map.Entry<Integer,String> p : this.posicaoDaPrateleira.entrySet()) {
            if (!this.prateleira.get(p.getValue()).getDisponivel()) {
                res.add(p.getKey());
            }
        }
        return res;
    }

    public List<String> disponiveis() {
        List<String> res = new ArrayList<>();
        for (Prateleira p : this.prateleira.values()) {
            if (p.getDisponivel())
                res.add(p.getCodigo());
        }
        return res;
    }

    public String algumaDisponivel() {
        for (Map.Entry<String,Prateleira> c : this.prateleira.entrySet()) {
            if (c.getValue().getDisponivel())
                return c.getKey();
        }
        return null;
    }

    public boolean existePrateleira(String codigo) {
        return this.prateleira.get(codigo) != null;
    }

    public int reservaPorCodigo(String codigo) {
        int x = -1;
        this.prateleira.get(codigo).setDisponivel(false);
        for (Map.Entry<Integer,String> m : this.posicaoDaPrateleira.entrySet()) {
            if (m.getValue().equals(codigo)) {
                x = m.getKey();
                break;
            }
        }
        return x;
    }

    public void reservaPorNumero(int numero) {
        String codigo = this.posicaoDaPrateleira.get(numero);
        this.prateleira.get(codigo).setDisponivel(false);
    }

    public void libertaPorCodigo(String codigo) {
        this.prateleira.get(codigo).setDisponivel(true);
    }

    public void libertaPorNumero(int numero) {
        String codigo = this.posicaoDaPrateleira.get(numero);
        this.prateleira.get(codigo).setDisponivel(true);
    }

    @Override
    public String toString() {
        return "Numero: "+this.getNumero()+"\nCorredorComArmazenamento{" +
                "prateleira=" + prateleira +
                ", posicaoDaPrateleira=" + posicaoDaPrateleira +
                '}';
    }
}