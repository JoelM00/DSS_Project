package Business;

import java.util.*;

public class GestArmazemFacade implements IGestArmazemFacade {
    private Robot robot;
    private Map<String,Palete> paletes;
    private Queue<String> paletesAtransportar;
    private Mapa mapa;
    private Leitor leitor;

    public GestArmazemFacade() {
        this.robot = new Robot("1",true,null,null,new CorredorSemArmazenamento(0));
        this.paletes = new HashMap<>();
        this.paletesAtransportar = new ArrayDeque<>();
        this.leitor = new Leitor();
        this.mapa = new Mapa();
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

    public void adicionaPaleteRobot(String palete) {
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

    public List<Posicao> consultaPrateleiras() {
        return this.mapa.listagem();
    }

    public Localizacao procuraPrateleiraDisponivel() {
        //return this.mapa.procuraPrateleiraDisponivel();
        return null;
    }

    public boolean existemPaletesAtransportar() {
        return this.paletesAtransportar.size() > 0;
    }


    public String iniciaTransportePalete() {
        if (this.robot.getDisponivel()) {
            String palete = this.paletesAtransportar.remove();
            Percurso p = this.mapa.calculaPercurso(this.robot.getLoc(), this.paletes.get(palete).getLoc());

            this.robot.setDisponivel(false);
            this.robot.setPalete(palete);
            this.robot.setPercuso(p);
            this.robot.movimenta();
            return palete;
        }
        return null;
    }


    public int transportaPalete(String palete) {
        if (this.robot.getPalete()==null) return -1;

        if (this.robot.getPalete().equals(palete)) {

            String codigo = this.mapa.procuraPrateleira();

            Localizacao dest = this.mapa.reservaPrateleiraPorCodigo(codigo);

            Percurso p = this.mapa.calculaPercurso(this.robot.getLoc(),dest);

            Palete pal = this.paletes.get(palete);

            if (pal!=null) {
                this.paletes.get(palete).setLoc(this.robot.getLoc());
                this.robot.setPercuso(p);
                this.robot.movimenta();
                return 1;
            }
        }
        return 0;
    }

    public int concluiTransportePalete(String palete) {
        if (this.robot.getPalete()==null) return -1;

        if (this.robot.getPalete().equals(palete)) {
            Palete p = this.paletes.get(palete);
            p.setLoc(this.robot.getLoc());
            p.setDisponivel(true);
            this.robot.setDisponivel(true);
            return 1;
        }
        return 0;
    }

    public void leitorRegisto(String codigo,float altura) {
        Palete p = this.leitor.regista(codigo,altura);
        this.paletes.put(p.getCodigo(),p);
        this.paletesAtransportar.add(p.getCodigo());
    }

    public void mostra() {
        for (Palete p : this.paletes.values()) {
            System.out.println(p.toString());
        }
    }

    public void mostraRobot() {
        System.out.println(this.robot.toString());
    }
}
