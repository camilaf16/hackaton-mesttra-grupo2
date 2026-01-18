package org.jogo.da.velha;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main { // classe principal do jogo

    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XO"; // caracteres válidos para o jogo

    final static int TAMANHO_TABULEIRO = 3; // tamanho do tabuleiro 3x3

    static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO]; // matriz do tabuleiro

    static Scanner teclado = new Scanner(System.in); // objeto Scanner para entrada do usuário

    static Random random = new Random(); // objeto Random para gerar números aleatórios

    public static void main(String[] args) throws InterruptedException { // método principal

        inicializarTabuleiro(); // inicializa o tabuleiro vazio
        logo();
        // obter caractere do usuario, caractere do computador e sorteio de quem começa
        // jogando
        char caractereUsuario = obterCaractereUsuario();
        char caractereComputador = Character.toUpperCase(caractereUsuario) == 'X' ? 'O' : 'X';
        boolean vezUsuarioJogar = sortearValorBooleano();
        boolean jogoContinua;

        do { // loop principal do jogo
            jogoContinua = true;
            exibirTabuleiro();

            if (vezUsuarioJogar) { // vez do usuario jogar
                processarVezUsuario(caractereUsuario);

                if (teveGanhador(caractereUsuario)) { // verifica se o usuario ganhou

                    exibirTabuleiro(); // exibe o tabuleiro
                    exibirVitoriaUsuario(); // exibe vitoria do usuario
                    jogoContinua = false; // encerra o jogo
                }

                vezUsuarioJogar = false; // passa a vez para o computador
            } else { // vez do computador jogar
                processarVezComputador(caractereComputador);

                if (teveGanhador(caractereComputador)) { // verifica se o computador ganhou

                    exibirTabuleiro(); // exibe o tabuleiro
                    exibirVitoriaComputador(); // exibe vitoria do computador
                    jogoContinua = false; // encerra o jogo
                }

                vezUsuarioJogar = true; // passa a vez para o usuario
            }

            if (jogoContinua && teveEmpate()) { // verifica se houve empate
                exibirTabuleiro(); // exibe o tabuleiro
                exibirEmpate(); // exibe mensagem de empate
                jogoContinua = false; // encerra o jogo
            }
        } while (jogoContinua); // enquanto o jogo continuar

        teclado.close();
    }

    // Inicializa o Tabuleiro vazio
    static void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) { // Verifica linhas
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) { // Verifica colunas
                tabuleiro[i][j] = ' '; // Verifica posição vazia
            }
        }
    }

    // Pede o caractere ao usuario
    static char obterCaractereUsuario() {
        // loop até o usuário digitar um caractere válido
        char valor = '\0';
        boolean valorValido = false;
        // solicita entrada do usuário
        while (!valorValido) {
            try { // Pede valor de entrada
                System.out.print("Digite o caractere do usuario (X ou O): ");
                String entrada = teclado.nextLine();
                // valida entrada do usuario
                if (entrada.isEmpty()) {
                    throw new IllegalArgumentException("Entrada vazia."); // verifica se a entrada está vazia
                }
                // Pede o primeiro caractere da entrada
                if (entrada.length() > 0) {
                    valor = Character.toUpperCase(entrada.charAt(0)); // converte o caractere para maiúsculo
                    if (CARACTERES_IDENTIFICADORES_ACEITOS.indexOf(valor) != -1) { // verifica se o caractere é válido
                        valorValido = true;
                    } else { // se caractere for inválido aparece mensagem de erro
                        throw new IllegalArgumentException("Valor digitado é incorreto.");
                    }
                }
            } catch (IllegalArgumentException e) { // captura a exceção e exibe a mensagem de erro
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        return valor; // retorna o caractere válido
    }

    // Pede a jogada do usuario
    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {

        String jogada, entrada;
        int linha, coluna;
        // loop até o usuário digitar uma jogada válida
        while (true) {

            // solicita a entrada do usuário
            System.out.print("Digite linha e coluna (1 a 3), ex: 2 2: ");
            entrada = teclado.nextLine();

            // O usuário informa a jogada no formato "linha coluna" considerando posições de
            // 1 a 3.
            // Exemplo: 1 1 representa o canto superior esquerdo, 3 3 o canto inferior
            // direito,
            // porém, arrays em Java são indexados a partir de 0.
            // Por isso, subtraímos 1 de cada valor:
            // 1 1 -> 0 0
            // 3 3 -> 2 2
            String[] partes = entrada.split(" ");

            // valida formato pra caso o usuário digite 11 ao invés de 1 1
            if (entrada.matches("\\d{2}")) {
                System.out.println("Formato inválido! Use: linha coluna, ex: 1 1");
                continue;
            }

            try {

                // ajusta para índice iniciar em 0
                linha = Integer.parseInt(partes[0]) - 1;
                coluna = Integer.parseInt(partes[1]) - 1;

                // caso o usuário digite valores que não estão entre 1 e 3
                if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                    System.out.println("Valores devem estar entre 1 e 3.");
                    continue;
                }

                jogada = linha + " " + coluna;

                // verifica se a jogada é válida
                if (posicoesLivres.contains(jogada)) {
                    break;
                }

                System.out.println("Jogada inválida! Tente novamente.");
           
            } catch (NumberFormatException e) {// entrada não numérica, provavelmente letras ou símbolos
                System.out.println("Digite apenas números!");
            }
        }
        // retorna a jogada convertida para vetor de inteiros
        return converterJogadaStringParaVetorInt(jogada);
    }

    // pede a jogada do computador
    static int[] obterJogadaComputador(String posicoesLivres, Scanner teclado) {

        String jogada;
        int linha, coluna;
        // loop até o computador gerar uma jogada válida
        while (true) {

            // gera linha e coluna aleatórias
            linha = random.nextInt(3);
            coluna = random.nextInt(3); // coluna

            jogada = linha + " " + coluna;

            // verificar se a jogada é válida
            if (posicoesLivres.contains(jogada)) {
                break;
            }
        }
        // retornar a jogada convertida para vetor de inteiros
        return converterJogadaStringParaVetorInt(jogada);
    }

    // converte a jogada de string para vetor de inteiros
    static int[] converterJogadaStringParaVetorInt(String jogada) {
        String[] partes = jogada.split(" ");
        // cria um vetor de inteiros para armazenar a jogada
        int[] resultado = new int[2];
        // converte as partes da string para inteiros e armazena no vetor
        resultado[0] = Integer.parseInt(partes[0]);
        resultado[1] = Integer.parseInt(partes[1]);

        return resultado;
    }

    // processar a vez do usuario
    static void processarVezUsuario(char caractereUsuario) {
        System.out.println("SUA VEZ");
        System.out.println();
        // pega as posições livres
        String posicoesLivres = retornarPosicoesLivres();
        // pega a jogada do usuário
        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);
        // atualiza o tabuleiro com a jogada do usuário
        atualizaTabuleiro(jogada, caractereUsuario);
    }

    // processar a vez do computador
    static void processarVezComputador(char caractereComputador) {
        // indica que é a vez do computador
        String posicoesLivres = retornarPosicoesLivres();

        // pega a jogada do computador
        int[] jogada = obterJogadaComputador(posicoesLivres, teclado);

        // atualiza o tabuleiro com a jogada do computador
        jogada = obterJogadaComputador(posicoesLivres, teclado);
        // atualiza o tabuleiro com a jogada do computador
        atualizaTabuleiro(jogada, caractereComputador);
    }

    // retorna as posições livres
    static String retornarPosicoesLivres() {

        StringBuilder posicoesLivres = new StringBuilder();
        // percorre o tabuleiro em busca de posições livres
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            // percorre as colunas
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                // verifica se a posição está livre
                if (tabuleiro[linha][coluna] == ' ') { // posição livre
                    // adiciona a posição livre na string
                    posicoesLivres.append(linha).append(" ").append(coluna).append(" | ");
                }

            }
        }
        // retornar as posições livres como string
        return posicoesLivres.toString();
    }

    // verifica se teve ganhador
    static boolean teveGanhador(char caractereJogador) {
        // verificar linhas, colunas e diagonais
        return teveGanhadorLinha(caractereJogador)
                || teveGanhadorColuna(caractereJogador)
                || teveGanhadorDiagonalPrincipal(caractereJogador)
                || teveGanhadorDiagonalSecundaria(caractereJogador);

    }

    // verifica se teve ganhador na linha
    static boolean teveGanhadorLinha(char caractereJogador) {
        // percorre as linhas
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            boolean ganhouNestaLinha = true;
            // percorre as colunas
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                // verifica se o caractere na posição é igual ao caractere do jogador
                if (tabuleiro[i][j] != caractereJogador) {
                    ganhouNestaLinha = false;
                    break;
                }
            }
            // se ganhou nesta linha, retornar true
            if (ganhouNestaLinha == true) {
                return true;
            }
        }
        return false; // se não ganhou em nenhuma linha, retorna false
    }

    // verifica se teve ganhador na coluna
    static boolean teveGanhadorColuna(char caractereJogador) {
        // percorre as colunas
        for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
            boolean ganhouNestaColuna = true;
            // percorre linhas
            for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
                // verifica se o caractere na posição é igual ao caractere do jogador
                if (tabuleiro[i][j] != caractereJogador) {
                    ganhouNestaColuna = false;
                    break;
                }
            }
            // se ganhou nesta coluna, retornar true
            if (ganhouNestaColuna == true) {
                return true;
            }
        } // se não ganhou em nenhuma coluna, retorna false
        return false;
    }

    // verifica se teve ganhador na diagonal principal
    static boolean teveGanhadorDiagonalPrincipal(char caractereJogador) {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) { // linha e coluna iguais
            if (tabuleiro[i][i] != caractereJogador) { // verificar posição
                return false;
            }
        }
        return true;
    }

    // verifica se teve ganhador na diagonal secundaria
    static boolean teveGanhadorDiagonalSecundaria(char caractereJogador) { // linha + coluna = tamanho - 1
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) { // linha
            if (tabuleiro[i][TAMANHO_TABULEIRO - 1 - i] != caractereJogador) { // verifica posição
                return false;
            }
        }
        return true;
    }

    // limpa a tela do console
    static void limparTela() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            // verifica sistema operacional e executa o comando
            ProcessBuilder pb;
            if (os.contains("win")) { // Windows
                pb = new ProcessBuilder("cmd", "/c", "cls"); // comando para limpar o console no Windows
            } else {
                pb = new ProcessBuilder("clear"); // comando para limpar o console no Linux e MacOS
            }
            pb.inheritIO().start().waitFor(); // executa o comando

        } catch (IOException | InterruptedException e) { // captura exceção
            System.err.println("Não foi possível limpar o console: " + e.getMessage()); // exibe mensagem de erro
            Thread.currentThread().interrupt();
        }
    }

    // exibe o tabuleiro
    static void exibirTabuleiro() throws InterruptedException {

        Thread.sleep(500); // pausa de 0,5 segundos antes de limpar a tela

        limparTela();

        logo();
        // exibe cabeçalho do tabuleiro
        System.out.println("  1     2      3");
        for (int linha = 0; linha < 3; linha++) { // percorre as linhas

            // exibie as linhas
            System.out.print(linha + 1);
            // exibe as colunas
            for (int colunas = 0; colunas < 3; colunas++) { // percorre as colunas
                // exibe o valor da posição
                System.out.print(" " + tabuleiro[linha][colunas] + " ");
                // exibe separador de colunas
                if (colunas < 2) {
                    System.out.print(" │ ");
                }
            }

            System.out.println();
            // exibe separador de linhas
            if (linha < 2) {
                System.out.println(" ────┼─────┼────");
            }
        }
        System.out.println();
    }

    // atualiza o tabuleiro
    static void atualizaTabuleiro(int[] jogada, char caractereJogador) { // recebe a jogada e o caractere do jogador
        int linha = jogada[0]; // linha
        int coluna = jogada[1]; // coluna
        // verifica se a posição é válida
        if ((linha >= 0 && linha < TAMANHO_TABULEIRO) &&
                (coluna >= 0 && coluna < TAMANHO_TABULEIRO)) { // posição válida
            tabuleiro[linha][coluna] = caractereJogador; // atualiza a posição com o caractere do jogador
        } else { // posição inválida
            System.out.println("ERRO! Posição inválida."); // exibe mensagem de erro
        }
    }

    // exibe vitoria do computador
    static void exibirVitoriaComputador() {
        System.out.println("O computador venceu!");
        System.out.println();
        System.out.println("   (╯︵╰,)");
        System.out.println("     /|\\");
        System.out.println("     / \\");
    }

    // exibe vitoria do usuario
    static void exibirVitoriaUsuario() {
        System.out.println("O usuário venceu!");
        System.out.println();
        System.out.println("   \\(^_^)/");
        System.out.println("     | |");
        System.out.println("    /   \\");
    }

    // exibe se houve empate
    static void exibirEmpate() {
        System.out.println("Ocorreu empate!");
        System.out.println();
        System.out.println("   0  X  0");
        System.out.println("  --------");
        System.out.println("   EMPATE");
    }

    // verifica se houve empate
    private static boolean teveEmpate() { // verifica se todas as posições estão preenchidas
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) { // verifica as linhas
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) { // verifica as colunas
                if (tabuleiro[i][j] == ' ') { // verifica a posição vazia
                    return false; // se encontrar posição vazia, retorna false
                }
            }
        }
        return true; // se todas as posições estiverem preenchidas, retorna true
    }

    // sorteia valor booleano aleatório
    static boolean sortearValorBooleano() {
        Random random = new Random();
        boolean sorteio = random.nextBoolean();
        return sorteio;
    }

    // exibe o logo do jogo
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