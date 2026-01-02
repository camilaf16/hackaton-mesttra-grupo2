package org.jogo.da.velha;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XOUC";
    final static int TAMANHO_TABULEIRO = 3;

    static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarTabuleiro();

        char caractereUsuario = obterCaractereUsuario();
        char caractereComputador = obterCaractereComputador(caractereUsuario);
        boolean vezUsuarioJogar = sortearValorBooleano();
        boolean jogoContinua = true;

        do {
            exibirTabuleiro();

            if (vezUsuarioJogar) {
                processarVezUsuario(caractereUsuario);

                if (teveGanhador(caractereUsuario)) {
                    exibirTabuleiro();
                    exibirVitoriaUsuario();
                    break;
                }

                vezUsuarioJogar = false;
            } else {
                processarVezComputador(caractereComputador);

                if (teveGanhador(caractereComputador)) {
                    exibirTabuleiro();
                    exibirVitoriaComputador();
                    break;
                }

                vezUsuarioJogar = true;
            }

            if (teveEmpate()) {
                exibirTabuleiro();
                exibirEmpate();
                break;
            }

        } while (jogoContinua);

        teclado.close();
    }

    private static void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    static char obterCaractereUsuario() {
        char valor = '\0';

        while (true) {
            System.out.print("Digite o caractere do usuario: ");
            String entrada = teclado.nextLine();

            if (!entrada.isEmpty()) {
                valor = Character.toUpperCase(entrada.charAt(0));
                if (CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(valor) != -1) {
                    return valor;
                }
            }

            System.out.println("Valor digitado é incorreto.");
        }
    }

    static char obterCaractereComputador(char caractereUsuario) {
        char valor = '\0';

        while (true) {
            System.out.print("Digite o caractere do computador: ");
            String entrada = teclado.nextLine();

            if (!entrada.isEmpty()) {
                valor = Character.toUpperCase(entrada.charAt(0));
                if (CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(valor) != -1 && valor != caractereUsuario) {
                    return valor;
                }
            }

            System.out.println("Valor digitado é incorreto.");
        }
    }

    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {
        String jogada;

        while (true) {
            System.out.println("Posições livres: " + posicoesLivres);
            System.out.print("Digite sua jogada (linha coluna): ");
            jogada = teclado.nextLine();

            String[] partes = jogada.split(" ");
            if (partes.length == 2) {
                int linha = Integer.parseInt(partes[0]);
                int coluna = Integer.parseInt(partes[1]);

                if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
                    return new int[]{linha, coluna};
                }
            }

            System.out.println("Jogada inválida! Tente novamente.");
        }
    }

    static void processarVezUsuario(char caractereUsuario) {
        System.out.println("VEZ DO USUÁRIO");
        System.out.println();

        String posicoesLivres = retornarPosicoesLivres();
        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);

        atualizaTabuleiro(jogada, caractereUsuario);
    }

    static void processarVezComputador(char caractereComputador) {

        StringBuilder listaPosicoesLivres = new StringBuilder();

        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                if (tabuleiro[linha][coluna] == ' ') {
                    listaPosicoesLivres.append(linha).append(coluna).append(";");
                }
            }
        }

        if (listaPosicoesLivres.isEmpty()) {
            return;
        }

        String[] jogadas = listaPosicoesLivres.toString().split(";");
        Random random = new Random();
        String jogada = jogadas[random.nextInt(jogadas.length)];

        int linha = Character.getNumericValue(jogada.charAt(0));
        int coluna = Character.getNumericValue(jogada.charAt(1));

        tabuleiro[linha][coluna] = caractereComputador;

        System.out.println("Computador jogou na posição: Linha: " + linha + ", Coluna: " + coluna);
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

    static boolean teveGanhadorLinha(char c) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == c && tabuleiro[i][1] == c && tabuleiro[i][2] == c) {
                return true;
            }
        }
        return false;
    }

    static boolean teveGanhadorColuna(char c) {
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == c && tabuleiro[1][j] == c && tabuleiro[2][j] == c) {
                return true;
            }
        }
        return false;
    }

    static boolean teveGanhadorDiagonalPrincipal(char c) {
        return tabuleiro[0][0] == c && tabuleiro[1][1] == c && tabuleiro[2][2] == c;
    }

    static boolean teveGanhadorDiagonalSecundaria(char c) {
        return tabuleiro[0][2] == c && tabuleiro[1][1] == c && tabuleiro[2][0] == c;
    }

    private static boolean teveEmpate() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    static void exibirTabuleiro() {
        limparTela();

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.print(" " + tabuleiro[linha][coluna] + " ");
                if (coluna < 2) System.out.print("|");
            }
            System.out.println();
            if (linha < 2) System.out.println("---------");
        }
    }

    static void limparTela() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void atualizaTabuleiro(int[] jogada, char caractereJogador) {
        tabuleiro[jogada[0]][jogada[1]] = caractereJogador;
    }

    static void exibirVitoriaComputador() {
        System.out.println("O computador venceu!");
    }

    static void exibirVitoriaUsuario() {
        System.out.println("O usuário venceu!");
    }

    static void exibirEmpate() {
        System.out.println("Ocorreu empate!");
    }

    static boolean sortearValorBooleano() {
        return new Random().nextBoolean();
    }
}
