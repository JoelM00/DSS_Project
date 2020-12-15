package Ui;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner is = new Scanner(System.in);
    private List<String> opcoes;
    private int escolha;

    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.escolha = 0;
    }

    public void executa() {
        do {
            showMenu();
            this.escolha = lerOpcao();
        } while (this.escolha == -1);
    }

    public void showMenu() {
        System.out.println("\n########### Menu ###########");
        for (int i = 0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        int opcao;
        System.out.print("Opção: ");
        try {
            opcao = is.nextInt();
        } catch (InputMismatchException e) {
            opcao = -1;
            System.out.println(e.toString());
        }
        if (opcao < 0 || opcao > this.opcoes.size()) {
            System.out.println("Opção invalida!!");
            opcao = -1;
        }
        return opcao;
    }

    public int getEscolha() {
        return this.escolha;
    }

}
