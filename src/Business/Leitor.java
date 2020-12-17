package Business;

public class Leitor {

    public Leitor() {}

    public Palete regista(String codigo,Float altura) {

        return new Palete(codigo,altura,new Posicao(),false,false);
    }
}
