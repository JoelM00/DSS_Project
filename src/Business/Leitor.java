package Business;

public class Leitor {
    public Palete regista(String codigo,Float altura) {
        return new Palete(codigo,altura,new Localizacao(null,0),false);

    }
}
