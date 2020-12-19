package Business;

import java.util.List;

public interface IGestArmazemFacade {

    List<Posicao> consultaPrateleiras();

    boolean verificaExistenciaPalete(String codigo);

    void leitorRegisto(String codigo, float altura);

    public String iniciaTransporteRobot(String palete);

    public String iniciaTransportePalete();

    public int transportaPalete(String robot);

    public int concluiTransportePalete(String robot);

    public String verificaDisponibilidadeRobot();

    public boolean existeAlgumRobotIndisponivel();

    public boolean existeRobot(String codigo);

    public boolean existemPaletesAtransportar();

    void mostra();

    void mostraRobot();

}