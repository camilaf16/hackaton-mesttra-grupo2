package org.jogo.da.velha;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XOUC";

    final static int TAMANHO_TABULEIRO = 3;

    static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        inicializarTabuleiro();
        logo();

        char caractereUsuario = obterCaractereUsuario();
        char caractereComputador = Character.toLowerCase(caractereUsuario) == 'x' ? 'o' : 'x';
        boolean vezUsuarioJogar = sortearValorBooleano(); 
        boolean jogoContinua;

        do {
            jogoContinua = true;
            exibirTabuleiro();

            if (vezUsuarioJogar){
                processarVezUsuario(caractereUsuario);

                if ( teveGanhador(caractereUsuario) ) {

                    exibirTabuleiro();
                    exibirVitoriaUsuario();
                    jogoContinua = false;
                }

                vezUsuarioJogar = false;
            } else {
                processarVezComputador(caractereComputador);

                if ( teveGanhador(caractereComputador) ) {

                    exibirTabuleiro();
                    exibirVitoriaComputador();
                    jogoContinua = false;
                }

                vezUsuarioJogar = true;
            }

            if (jogoContinua && teveEmpate()) {
                exibirTabuleiro();
                exibirEmpate();
                jogoContinua = false;
            }
        } while (jogoContinua);

        teclado.close();
    }

    private static void inicializarTabuleiro() {
        for (int i=0; i<TAMANHO_TABULEIRO; i++) {
            for (int j=0; j<TAMANHO_TABULEIRO; j++) {
                tabuleiro [i] [j] = ' ';
            }
        }
    }

    static char obterCaractereUsuario() {
        
        char valor = '\0';
        boolean valorValido = false;

        while(!valorValido){
            try {
                System.out.print("Digite o caractere do usuario: ");
                String entrada = teclado.nextLine();

                if (entrada.isEmpty()) {
                    throw new IllegalArgumentException("Entrada vazia.");
                }

                if (entrada.length() > 0) {
                valor = Character.toUpperCase(entrada.charAt(0));
                    if(CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(valor) != -1){
                        valorValido = true;
                    } else {
                        throw new IllegalArgumentException("Valor digitado é incorreto.");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        return valor;
    }

    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {
        String jogada;

        while (true) {
            System.out.print("Digite sua jogada (linha e coluna): ");
            jogada = teclado.nextLine();

            if (posicoesLivres.contains(jogada)) {
                break;
            }

            System.out.println("Jogada inválida! Tente novamente.");
        }

    return converterJogadaStringParaVetorInt(jogada);
    }

    static int[] converterJogadaStringParaVetorInt(String jogada) {
        String [] partes = jogada.split(" ");

        int[] resultado = new int[2];

        resultado [0] = Integer.parseInt(partes[0]);
        resultado [1] = Integer.parseInt(partes[1]);

        return resultado;
    }

    static void processarVezUsuario(char caractereUsuario) {
        System.out.println("SUA VEZ");
        System.out.println();

        String posicoesLivres = retornarPosicoesLivres();

        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);

        atualizaTabuleiro(jogada, caractereUsuario);
    }

    static void processarVezComputador(char caractereComputador) {

        String listaPosicoesLivres = "";

        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {

                if (tabuleiro[linha][coluna] == ' ') {
                    listaPosicoesLivres += linha + "" + coluna + ";";
                }
            }
        }

        if (listaPosicoesLivres.isEmpty()) {
            System.out.println("Computador não consegue jogar pois não há mais posições livres!");
            return;
        }

        String jogadaComputador = listaPosicoesLivres.split(" ")[0];
        int linhaComputador = Character.getNumericValue(jogadaComputador.charAt(0));
        int colunaComputador = Character.getNumericValue(jogadaComputador.charAt(1));

        tabuleiro[linhaComputador][colunaComputador] = caractereComputador;
    }

    static String retornarPosicoesLivres() {

        StringBuilder posicoesLivres = new StringBuilder();

        for (int linha = 0; linha < tabuleiro.length; linha++) {

            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {

                if (tabuleiro[linha][coluna] == ' ') {
                    posicoesLivres.append(linha).append(" ").append(coluna).append(" | ");
                }

            }
        }

        return posicoesLivres.toString();
    }

    static boolean teveGanhador(char caractereJogador) {

        return teveGanhadorLinha(caractereJogador) 
        || teveGanhadorColuna(caractereJogador)
        || teveGanhadorDiagonalPrincipal(caractereJogador) 
        || teveGanhadorDiagonalSecundaria(caractereJogador);

    }

    static boolean teveGanhadorLinha(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            boolean ganhouNestaLinha = true;

            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {

                if (tabuleiro[i][j] != caractereJogador) {
                    ganhouNestaLinha = false;
                    break;
                }
            }
            if (ganhouNestaLinha == true) {
                return true;
            }
        }
        return false;
    }

    static boolean teveGanhadorColuna(char caractereJogador) {

        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            boolean ganhouNestaColuna = true;

            for (int i = 0; i < TAMANHO_TABULEIRO; i++) {

                if (tabuleiro[i][j] != caractereJogador) {
                    ganhouNestaColuna = false;
                    break;
                }
            }
            if (ganhouNestaColuna == true) {
                return true;
            }
        }
        return false;
    }

    static boolean teveGanhadorDiagonalPrincipal(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            if (tabuleiro[i][i] != caractereJogador) {
                return false;
            }
        }
        return true;
    }

    static boolean teveGanhadorDiagonalSecundaria(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            if (tabuleiro[i][TAMANHO_TABULEIRO - 1 - i] != caractereJogador) {
                return false;
            }
        }
        return true;
    }

    static void limparTela() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Não foi possível limpar o console: " + e.getMessage());
            Thread.currentThread().interrupt();
        }      
    }

    static void exibirTabuleiro() throws InterruptedException {

        Thread.sleep(500);

        limparTela();

        logo();

        for (int linha = 0; linha < 3; linha++) {

            for (int colunas = 0; colunas < 3; colunas++) {
                
                System.out.print(" " + tabuleiro[linha][colunas] + " ");

                if (colunas < 2) {
                    System.out.print(" │ ");
                }
            }

            System.out.println();

            if (linha < 2) {
                System.out.println("────┼─────┼────");
            }
        }
        System.out.println();
    }

    static void atualizaTabuleiro(int[] jogada, char caractereJogador) { 
        int linha = jogada[0];
        int coluna = jogada [1];
        if ((linha >= 0 && linha < TAMANHO_TABULEIRO) && 
            (coluna >= 0 && coluna < TAMANHO_TABULEIRO)) {
                tabuleiro[linha][coluna] = caractereJogador;
        } else {
            System.out.println("ERRO! Posição inválida.");
        }         
    }

    static void exibirVitoriaComputador() {
      System.out.println("O computador venceu!");
      System.out.println();
      System.out.println("   (╯︵╰,)");
      System.out.println("     /|\\");
      System.out.println("     / \\");
    }

    static void exibirVitoriaUsuario() {
      System.out.println("O usuário venceu!");
      System.out.println();
      System.out.println("   \\(^_^)/");
      System.out.println("     | |");
      System.out.println("    /   \\");
    }

    static void exibirEmpate() {
      System.out.println("Ocorreu empate!");
      System.out.println();
      System.out.println("   0  X  0");
      System.out.println("  --------");
      System.out.println("   EMPATE");
    }

    private static boolean teveEmpate() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j =0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean sortearValorBooleano() {
        Random random = new Random();
        boolean sorteio = random.nextBoolean();
        return sorteio;
    }

    static void logo() {
        System.out.println("""
                      
                      ██╗ ██████╗  ██████╗  ██████╗\s
                      ██║██╔═══██╗██╔════╝ ██╔═══██╗
                      ██║██║   ██║██║  ███╗██║   ██║
                 ██   ██║██║   ██║██║   ██║██║   ██║
                 ╚█████╔╝╚██████╔╝╚██████╔╝╚██████╔╝
                  ╚════╝  ╚═════╝  ╚═════╝  ╚═════╝\s
                                    D A   V E L H A
                
                """);
    }
}
