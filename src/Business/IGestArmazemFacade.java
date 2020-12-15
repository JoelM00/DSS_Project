package Business;

import java.util.List;

public interface IGestArmazemFacade {

    public List<Posicao> consultaPrateleiras();

    public boolean verificaExistenciaPalete(String codigo);

    public void leitorRegisto(String codigo, float altura);

    public String iniciaTransportePalete();

    public int transportaPalete(String palete);

    public int concluiTransportePalete(String palete);

    public boolean existemPaletesAtransportar();

    public void mostra();

    public void mostraRobot();

}