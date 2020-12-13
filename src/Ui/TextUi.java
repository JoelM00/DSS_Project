package Ui;

import Business.GestArmazemFacade;
import Business.IGestArmazemFacade;
import Business.Localizacao;

import java.util.List;
import java.util.Scanner;

public class TextUi {
    private IGestArmazemFacade modelo;
    private Menu menu;
    private Scanner scin;

    public TextUi() {
        String[] opcoes = {
                "Comunicar codigo QR",
                "Preparar transporte",
                "Notificar recolha de palete",
                "Notificar entrega de palete",
                "Consultar listagem de localizacoes"};
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
            System.out.println("Codigo da palete: ");
            String codigo = this.scin.nextLine();

            if (!this.modelo.verificaExistenciaPalete(codigo)) {

                System.out.println("Altura da palete: ");
                float altura = this.scin.nextFloat();
                this.modelo.leitorRegisto(codigo,altura);

            } else {
                System.out.println("Codigo existente!");
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trataPreparacao() {
        try {
            String pal = this.modelo.iniciaTransportePalete();
            System.out.println(" -> Palete selecionada!");
            System.out.println(" -> Robot notificado! ");
            System.out.println("Codigo da palete: "+pal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trataRecolha() {
        try {
            System.out.println("Codigo da palete: ");
            String cod = this.scin.nextLine();
            this.modelo.transportaPalete(cod);

        } catch (Exception e) {
            System.out.println("Codigo introduzido nao referencia palete em transporte!");
        }
    }

    public void trataEntrega() {
        try {
            System.out.println("Codigo da palete: ");
            String cod = this.scin.nextLine();
            this.modelo.concluiTransportePalete(cod);
        } catch (Exception e) {
            System.out.println("Codigo introduzido nao referencia palete em transporte!");
        }
    }

    public void trataConsulta() {
        try {
            List<Localizacao> res = this.modelo.consultaPrateleiras();
            if (res.size()==0) {
                System.out.println("Nao existem prateleiras ocupadas!");
                return;
            }
            for (Localizacao l : res) {
                System.out.println(l.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
