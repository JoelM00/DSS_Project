package Business;

import java.util.*;

public class CorredorComArmazenamento extends Localizacao {
    private final Map<Integer,String> posicaoDaPrateleira;

    public CorredorComArmazenamento(int numero,List<String> codigos) {
        super(numero);

        this.posicaoDaPrateleira = new HashMap<>();
        int cont = 0;

        for (String s : codigos)
            this.posicaoDaPrateleira.put(cont++,s);
    }

    public int posicao(String codigo) {

        for (Map.Entry<Integer,String> aux : this.posicaoDaPrateleira.entrySet()) {
            if (aux.getValue().equals(codigo)) {
                return aux.getKey();
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Número: " + this.getNumero()
                + "\nCorredorComArmazenamento { "
                + ", posicaoDaPrateleira = " + posicaoDaPrateleira +
                '}';
    }

}
