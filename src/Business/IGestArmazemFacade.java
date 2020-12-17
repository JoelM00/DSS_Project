package Business;

import java.util.List;

public interface IGestArmazemFacade {

    List<Posicao> consultaPrateleiras();

    boolean verificaExistenciaPalete(String codigo);

    void leitorRegisto(String codigo, float altura);

    String iniciaTransportePalete();

    public String iniciaTransporteRobot(String palete);

    int transportaPalete(String codigo);

    int concluiTransportePalete(String codigo);

    boolean existemPaletesAtransportar();

    String verificaDisponibilidadeRobot();

    void mostra();

    void mostraRobot();

    public boolean existeAlgumRobotIndisponivel();

    public boolean existeRobot(String codigo);

    public boolean existemPaletesATransportar();
}