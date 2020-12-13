package Business;

import Data.PaleteDAO;
import Data.PrateleiraDAO;

import java.util.*;

public class GestArmazemFacade implements IGestArmazemFacade {
    private Robot robot;
    private Map<String,Prateleira> prateleiras;
    private Map<String,Palete> paletes;
    private Queue<String> paletesAtransportar;
    private Mapa mapa;
    private Leitor leitor;

    public GestArmazemFacade() {
        this.robot = new Robot("1",true,null,null,new Localizacao(null,0));
        this.paletes = new HashMap<>();
        this.prateleiras = new HashMap<>();
        this.paletesAtransportar = new ArrayDeque<>();
        this.leitor = new Leitor();
    }

    public void atualizaLocalizacaoPalete(String codigo,Localizacao loc) {
        this.paletes.get(codigo).setLoc(loc);
    }

    public void adicionaPalete(Palete palete) {
        this.paletes.put(palete.getCodigo(),palete);
    }

    public void removePaleteAtransportar(String codigo) {
        this.paletesAtransportar.remove(codigo);
    }

    public void adcicionaPaleteAtransportar(String palete) {
        this.paletesAtransportar.add(palete);
    }

    public void atualizaDisponibilidadePalete(String codigo,boolean b) {
        this.paletes.get(codigo).setDisponivel(b);
    }

    public void removePalete(String codigo) {
        this.paletesAtransportar.remove(codigo);
        this.paletes.remove(codigo);
    }

    public void adicionaPercurso(String codigo, Percurso percurso) {
        this.robot.setPercuso(percurso);
    }

    public void removePercurso(String codigo) {
        this.robot.setPercuso(null);
    }

    public void adicionaPaleteRobot(String codigo,Palete palete) {
        this.robot.setPalete(palete);
    }

    public void removePaleteRobot(String codigo) {
        this.robot.setPalete(null);
    }

    public boolean verificaDisponibilidade(String codigo) {
        return this.robot.getDisponivel();
    }

    public boolean verificaExistenciaPalete(String codigo) {
        return !(this.paletes.get(codigo) == null);
    }

    public void atualizaDisponibilidade(String codigo,boolean b) {
        this.robot.setDisponivel(b);
    }

        public List<Localizacao> consultaPrateleiras() {
        List<Localizacao> res = new ArrayList<>();
        for (Prateleira p : this.prateleiras.values()) {
            if (p.getDisponivel()) {
                res.add(p.getLoc());
            }
        }
        return res;
    }

    public Localizacao procuraPrateleiraDisponivel() {
        for (Prateleira p : this.prateleiras.values()) {
            if (p.getDisponivel()) {
                p.setDisponivel(false);
                return p.getLoc();
            }
        }
        return null;
    }

    public String iniciaTransportePalete() {
        String palete = this.paletesAtransportar.remove();
        Percurso p = this.mapa.calculaPercusoArmazenamento(this.robot.getLoc(),this.paletes.get(palete).getLoc());

        this.robot.setDisponivel(false);
        this.robot.setPercuso(p);
        this.robot.movimenta();
        return palete;
    }


    public void transportaPalete(String palete) {
        Localizacao dest = this.procuraPrateleiraDisponivel();

        Percurso p = this.mapa.calculaPercusoArmazenamento(this.robot.getLoc(),dest);

        this.robot.setPercuso(p);
        this.robot.movimenta();
    }

    public void concluiTransportePalete(String palete) {
        this.paletes.get(palete).setDisponivel(true);
        this.robot.setDisponivel(true);
    }

    public void leitorRegisto(String codigo,float altura) {
        Palete p = this.leitor.regista(codigo,altura);
        this.paletes.put(p.getCodigo(),p);
        this.paletesAtransportar.add(p.getCodigo());

    }

}
