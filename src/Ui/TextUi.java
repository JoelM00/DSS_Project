package Ui;

import Business.GestArmazemFacade;
import Business.IGestArmazemFacade;
import Business.Posicao;
import java.util.List;
import java.util.Scanner;

public class TextUi {
    private IGestArmazemFacade modelo;
    private Menu menu;
    private Scanner scin;

    public TextUi() {
        String[] opcoes = {
                "Registar palete no Sistema.",
                "Preparar transporte de uma palete.",
                "Notificar recolha da palete.",
                "Notificar entrega da palete.",
                "Consultar listagem de localizacoes."};
        this.menu = new Menu(opcoes);
        this.modelo = new GestArmazemFacade();
        this.scin = new Scanner(System.in);
    }

    public void run() {
        do {
            menu.executa();
            switch (menu.getEscolha()) {
                case 1:
                    this.trataComunicacao();
                    break;
                case 2:
                    this.trataPreparacao();
                    break;
                case 3:
                    this.trataRecolha();
                    break;
                case 4:
                    this.trataEntrega();
                    break;
                case 5:
                    this.trataConsulta();
                    break;
            }
        } while (menu.getEscolha()!=0);
        System.out.println("Encerrando...");
    }

    public void trataComunicacao() {
        try {
            System.out.print("Codigo da palete: ");
            String codigo = this.scin.nextLine();

            if (!this.modelo.verificaExistenciaPalete(codigo)) {

                System.out.print("Altura da palete: ");
                float altura = this.scin.nextFloat();
                this.modelo.leitorRegisto(codigo,altura);

                System.out.println(" -> Palete registada no sistema.");
                System.out.println(" -> Palete adcicionada na lista de paletes a transportar.");
                this.scin.nextLine();

            } else {
                System.out.println(" -> Codigo ja existente!");
            }
            this.modelo.mostra();
            this.modelo.mostraRobot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trataPreparacao() {
        try {
            if (!this.modelo.existemPaletesAtransportar()) {
                System.out.println(" -> Nao existem paletes a serem transportadas!");
                return;
            }
            String pal = this.modelo.iniciaTransportePalete();
            if (pal==null) {
                System.out.println(" -> O robot esta indisponivel!");
            } else {
                System.out.println(" -> Robot notificado! ");
                System.out.println(" -> Palete selecionada: "+pal);
            }
            this.modelo.mostra();
            this.modelo.mostraRobot();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trataRecolha() {
        try {
            System.out.print("Codigo da palete: ");
            String cod = this.scin.nextLine();
            switch (this.modelo.transportaPalete(cod)) {
                case -1:
                    System.out.println(" -> O robot nao iniciou o transporte!");
                    break;
                case 0:
                    System.out.println(" -> Codigo introduzido nao referencia palete em transporte!");
                    break;
                case 1:
                    System.out.println(" -> Palete recolhida!");
                    break;
            }
            this.modelo.mostra();
            this.modelo.mostraRobot();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trataEntrega() {
        try {
            System.out.print("Codigo da palete: ");
            String cod = this.scin.nextLine();
            switch (this.modelo.concluiTransportePalete(cod)) {
                case -1:
                    System.out.println(" -> O robot nao iniciou o transporte!");
                    break;
                case 0:
                    System.out.println(" -> Codigo introduzido nao referencia palete em transporte!");
                    break;
                case 1:
                    System.out.println(" -> Palete entregue!");
                    break;
            }
            this.modelo.mostra();
            this.modelo.mostraRobot();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trataConsulta() {
        try {
            List<Posicao> res = this.modelo.consultaPrateleiras();
            if (res.size()>0) {
                for (Posicao p : res) {
                    System.out.println(" -> Corredor " + p.getNumero() + " prateleira " + p.getSeccao());
                }
            } else {
                System.out.println(" -> Nenhuma prateleira ocupada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
