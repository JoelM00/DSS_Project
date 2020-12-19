package Business;

import Data.PaleteDAO;

import java.util.*;

public class GestArmazemFacade implements IGestArmazemFacade {
    private Map<String,Robot> robots;
    private Map<String,Palete> paletes;
    private Queue<String> paletesAtransportar;
    private final Mapa mapa;
    private final Leitor leitor;

    private void criaRobots() {
        for (int i = 0; i<3; i++) {
            String codigo = "R"+(i+1);
            this.robots.put(codigo,new Robot(codigo,true,null,null, new Posicao(0,0)));
        }
    }

    public GestArmazemFacade() {
        this.robots = new HashMap<>();
        this.paletes = new HashMap<>();
        this.paletesAtransportar = new ArrayDeque<>();
        this.leitor = new Leitor();
        this.mapa = new Mapa();
        this.criaRobots();
    }

    public boolean verificaExistenciaPalete(String codigo) {
        return !(this.paletes.get(codigo) == null);
    }

    public List<Posicao> consultaPrateleiras() {

        List<Posicao> aux = new ArrayList<>();

        for(Posicao p : this.mapa.listagem())
            aux.add(p.clone());

        return aux;
    }

    public String algumRobotDisponivel() {
        for (Robot r : this.robots.values()) {
            if (r.getDisponivel()) return r.getCodigo();
        }
        return null;
    }

    public boolean existeAlgumRobotIndisponivel() {
        for (Robot r : this.robots.values()) {
            if (!r.getDisponivel()) return true;
        }
        return false;
    }

    public boolean existemPaletesAtransportar() {
        return this.paletesAtransportar.size() > 0;
    }

    public String verificaDisponibilidadeRobot() {
        return this.algumRobotDisponivel();
    }

    public boolean existeRobot(String codigo) {
        return this.robots.get(codigo)!=null;
    }

    public String iniciaTransporteRobot(String palete) {
        String robot = this.verificaDisponibilidadeRobot();

        if (robot!=null) {

            Robot r = this.robots.get(robot);

            Percurso p = this.mapa.calculaPercurso(r.getLoc(), /* origem */
                    this.paletes.get(palete).getLoc()); /* destino */

            r.setDisponivel(false);
            r.setPalete(palete);
            r.setPercuso(p.clone());
            r.movimenta();

            return robot;
        }
        return null;
    }

    public String iniciaTransportePalete() {
        String robot = this.verificaDisponibilidadeRobot();

        if (robot!=null) {

            String palete = this.paletesAtransportar.remove();

            this.paletes.get(palete).setEmTransporte(true);

            return palete;
        }
        return null;
    }


    public int transportaPalete(String robot) {
        Robot robo = this.robots.get(robot);

        String palete = robo.getPalete();
        int r = 0;

        if (palete == null) r = -1;

        else {

            float altura = this.paletes.get(palete).getAltura();

            String codigo = this.mapa.procuraPrateleira(altura);

            if (codigo == null) {
                robo.setDisponivel(true);
                return -2;
            }

            Localizacao dest = this.mapa.reservaPrateleiraPorCodigo(codigo);

            Localizacao loc = robo.getLoc();

            Percurso p = this.mapa.calculaPercurso(loc,dest);

            Palete pal = this.paletes.get(palete);

            if (pal != null) {
                this.paletes.get(palete).setLoc(loc);
                robo.setPercuso(p);
                robo.movimenta();
                robo.setRecolheuPalete(true);
                r = 1;
            }
        }
        return r;
    }

    public int concluiTransportePalete(String robot) {
        Robot robo = this.robots.get(robot);

        String palete = robo.getPalete();

        if (palete == null || !robo.getRecolheuPalete()) return -1;

        else {
            this.paletes.get(palete).setTransporteConcluido(robo.getLoc());

            robo.setDisponivel(true);
            robo.setPalete(null);
            robo.setRecolheuPalete(false);

            return 1;
        }
    }

    public void leitorRegisto(String codigo,float altura) {

        Palete p = this.leitor.regista(codigo,altura);

        this.paletes.put(codigo,p.clone());
        this.paletesAtransportar.add(codigo);
    }

    public void mostra() {
        this.paletes.values().forEach(p-> System.out.println(p.toString()));
    }


    public void mostraRobot() {
        this.robots.values().forEach(r -> System.out.println(r.toString()));
    }
}
