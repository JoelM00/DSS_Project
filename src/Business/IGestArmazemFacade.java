package Business;

import java.util.List;

public interface IGestArmazemFacade {

    public void atualizaLocalizacaoPalete(String codigo, Localizacao loc);

    public void adicionaPalete(Palete palete);

    public void removePaleteAtransportar(String codigo);

    public void adcicionaPaleteAtransportar(String palete);

    public void atualizaDisponibilidadePalete(String codigo, boolean b);

    public void removePalete(String codigo);

    public void transportaPalete(String codigo);

    public void adicionaPercurso(String codigo, Percurso percurso);

    public void removePercurso(String codigo);

    public void adicionaPaleteRobot(String codigo, Palete palete);

    public void removePaleteRobot(String codigo);

    public boolean verificaDisponibilidade(String codigo);

    public void atualizaDisponibilidade(String codigo, boolean b);

    public void leitorRegisto(String codigo, float altura);

    public String iniciaTransportePalete();

    public void concluiTransportePalete(String palete);

    public List<Localizacao> consultaPrateleiras();

    public boolean verificaExistenciaPalete(String codigo);
}