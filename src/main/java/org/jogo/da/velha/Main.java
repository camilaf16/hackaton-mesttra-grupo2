import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.jogo.da.velha.tools.Utilities;

public class Main {
    // Estes caracteres são aceitos como caracteres para representarem
    // os jogadores. Utizado para evitar caracteres que não combinem com
    // o desenho do tabuleiro.
    final static String CARACTERES_IDENTIFICADORES_ACEITOS = "XOUC"; // U -> usuário, C -> Computador

    // Tamanho do tabuleiro 3x3. Para o primeiro nivel de dificuldade
    // considere que este valor não será alterado. 
    // Depois que você conseguir implementar o raciocionio para o tabuleiro 3x3
    // tente ajustar o código para funcionar para qualquer tamanho de tabuleiro
    final static int TAMANHO_TABULEIRO = 3;

    static char[][] tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        
        inicializarTabuleiro();

        // Definimos aqui qual é o caractere que cada jogador irá utilizar no jogo.
        //TODO 01: chame as funções obterCaractereUsuario() e obterCaractereComputador
        //para definir quais caracteres da lista de caracteres aceitos que o jogador
        //quer configurar para ele e para o computador.
        char caractereUsuario = ????;
        char caractereComputador = ????;

        // Esta variavel é utilizada para definir se o usuário começa a jogar ou não.
        // Valor true, usuario começa jogando, valor false computador começa.
        //TODO 02: obtenha o valor booleano sorteado
        boolean vezUsuarioJogar = ????;

        boolean jogoContinua;

        do {
            // controla se o jogo terminou
            jogoContinua = true;
            exibirTabuleiro();

            if (vezUsuarioJogar){
                //TODO 03: Execute a chamada processar vez do usuario
                processarVezUsuario(caractereUsuario);

                // Verifica se o usuario venceu
                //TODO 04: Este if deve executar apenas se teve ganhador 
                if ( /*TODO: esreva aqui a chamada para teveGanhador verificar se o usuário ganhou*/ ) {
                    
                    exibirTabuleiro();
                    exibirVitoriaUsuario();
                    jogoContinua = false;
                }

                // define que na proxima execucao do laco o jogador nao joga, ou seja, será a vez do computador
                vezUsuarioJogar = false;
            } else {

                //TODO 05: Execute a chamada processar vez do computador

                // Verifica se o computador venceu
                //TODO 06: Este if deve executar apenas se teve ganhador
                if ( /*esreva aqui a chamada para teve ganhador*/ ) {

                    //TODO 07: Exiba que o computador ganhou
                    jogoContinua = false;
                }

                //TODO 08: defina qual o vaor a variavel abaixo deve possuir para que a proxima execucao do laco seja a vez do usuário
                vezUsuarioJogar = ????;
            }
        
            //TODO 09: Este if deve executar apenas se o jogo continua E 
            //ocorreu tempate. Utilize o metodo teveEmpate()
            if ( /*escreva aqui a condicao conforme o TODO acima*/ ) {
                exibirTabuleiro();
                exibirEmpate();
                jogoContinua = false;
            }
        } while (jogoContinua);

        teclado.close();
    }

    /*
     * Descrição: Utilizado para iniciar a matriz/tabuleiro com o caractere ' '
     * espaço, no início do jogo. Matrizes de char precisam ter um valor
     * diferente de '' vazio. A idéia é, se tiver ' ' espaço, a posição está
     * livre. Qualquer outro caractere presente na posição, representa o
     * caractere do jogador em questão: usuário ou computador. Um exemplo seria,
     * 'X' para usuário e 'O' para computador. Para o primeiro nível de
     * complexidade considere um tabuleiro apenas de tamanho 3x3, 3 linhas e 3
     * colunas. 
     * Nível de complexidade: 3 de 10
     */

    // Inicializa tabuleiro populando todas as posições como vazia.
    private static void inicializarTabuleiro() {
        for (int i=0; i<TAMANHO_TABULEIRO; i++) {
            for (int j=0; j<TAMANHO_TABULEIRO; j++) {
                tabuleiro [i] [j] = ' ';
            }
        }
    }

    /*
     * Descrição: Utilizado para obter no início do jogo qual o caractere que o
     * usuário quer utilizar para representar ele próprio. Este método recebe o
     * teclado para permitir que o usuário digite o caractere desejado. Faça a
     * leitura do caractere desejado pelo usuário, através do teclado, realize
     * as validações para não aceitar caracteres que não estejam definidos pela
     * constante CARACTERES_IDENTIFICADORES_ACEITOS, e retorne o caractere lido
     * através do return.
     * Nível de complexidade: 4 de 10
     */
    static char obterCaractereUsuario() {
        // TODO 11: Implementar método conforme explicação

    }

    /*
     * Descrição: Utilizado para obter no início do jogo qual o caractere que o
     * usuário quer utilizar para representar o computador. Este método recebe o
     * teclado e recebe o caractere que foi configurado para o usuário, pois o
     * usuário e o computador não podem jogar com o mesmo caractere. Por exemplo,
     * se o usuário configurou para ele o caractere X ele não pode escolher o X
     * como o caractere também para o computador. Neste método apenas os seguintes
     * caracteres definidos pela constante CARACTERES_IDENTIFICADORES_ACEITOS devem
     * ser aceitos. Lembre-se que o caractere armazenado em caractereUsuario não
     * pode ser aceito. Após realizar a leitura do caractere pelo teclado e
     * validá-lo, faça o return deste caractere.
     * Nível de complexidade: 4 de 10
     */
    static char obterCaractereComputador(char caractereUsuario) {
        // TODO 12: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para validar se a jogada do usuário é uma jogada válida.
     * Uma jogada é considerada válida quando ela está presente dentro da lista de
     * posicoesLivres. Desta forma, o método recebe a string com as posições livres,
     * além da linha e coluna jogada pelo usuário. O método verifica se a linha e
     * coluna está presente dentro da string de posições livres, se estiver retorna
     * true se não retorna false. Para descobrir se a linha e coluna esta presente
     * dentro da lista de posições livres pense em usar método contanis da string.
     * Nível de complexidade: 3 de 10
     */
    static boolean jogadaValida(String posicoesLivres, int linha, int coluna) {
        //TODO 13: Implementar método conforme explicação
        return posicoesLivres.contains(linha + "," + coluna);


    }

    /*
     * Descrição: Utilizado para obter do usuário a linha e a coluna que ele deseja
     * jogar. Para isto o método deve exibir um mensagem informando que o jogador
     * deve digitar a linha e a coluna separados por um espaço. O método deve
     * realizar as validações necessárias para os casos do usuário não digitar
     * dois valores e também para o caso do usuário não digitar números.
     * O método deve garantir que o usuário digite os valores conforme solicitado
     * e devolva os valores lidos somente quando estes atenderam as regras.
     * Após a leitura dos valores de linha e coluna, o método deve retornar os
     * valores já no formato de índice, ou seja, no tabuleiro exibimos para o
     * usuário linha 1, linha 2, linha 3, coluna 1, coluna 2 e coluna 3. O
     * usuário digita os valores neste formato, no entanto o método ao retonar
     * os valores deve ajustar a linha 1 para o índice 0, a linha 2 para o índice
     * 1 e assim sucessivamente, da mesma forma que as colunas.
     * Após a validação e ajuste dos índices, o método deve verificar se a jogada
     * do usuário está presente na lista de posicoesLivres que ele recebeu como
     * parametro. Para isto, o método faz a chamada ao método jogadaValida()
     * para determinar se a jogada é aceita. Se a jogada não for aceita, é exibido
     * uma mensagem informando que a jogada não é permitida e reinicia o processo de
     * leitura de uma nova jogada. Se a jogada for aceita deve devolver os
     * valores no formato de um vetor de inteiro de duas posições. No índice 0 deste
     * vetor, deve ser armazenado o valor da linha jogada pelo usuário e no índice 1
     * do vetor, deve ser armazenado a coluna jogada pelo usuário.
     * Nível de complexidade: 5 de 10
     */
    static int[] obterJogadaUsuario(String posicoesLivres, Scanner teclado) {
    String jogada;

    while (true) {
        System.out.println("Posições livres: " + posicoesLivres);
        System.out.print("Digite sua jogada (linha e coluna): ");
        jogada = teclado.nextLine();

        if (posicoesLivres.contains(jogada)) {
            break;
        }

        System.out.println("Jogada inválida! Tente novamente.");
    }

    return Utilities.converterJogada(jogada);
    }

    static int[] obterJogadaComputador(String posicoesLivres, Scanner teclado) {
    String[] jogadasLivres = posicoesLivres.split(";");

    Random random = new Random();

    int indiceSorteado = random.nextInt(jogadasLivres.length);

    String jogada = jogadasLivres[indiceSorteado];

    return Utilities.converterJogada(jogada);

    }

    /*
     * Descrição: Utilizado para converter uma jogada no formato xy (linha/coluna)
     * de string para um vetor de int. Para isto, este método recebe a jogada no
     * formato string e deve colocar o valor de x dentro do índice 0 do vetor de
     * inteiro e deve colocar o valor de y dentro do índice 1 do vetor de inteiro.
     * Após a construção do vetor de inteiro retorne este vetor com o comando
     * return.
     * Nível de complexidade: 4 de 10
     */
    static int[] converterJogadaStringParaVetorInt(String jogada) {
        String [] partes = jogada.split(" ");

        int[] resultado = new int[2];

        resultado [0] = Integer.parseInt(partes[0]);
        resultado [1] = Integer.parseInt(partes[1]);

        return resultado;
    }

    /*
     * Descrição: Utilizado para realizar as ações necessárias para processar a vez
     * do usuário jogar. Este método deve exibir uma mensagem que é a vez do usuário
     * jogar. Este método é encarregado de obter a jogada do usuário através do
     * método obterJogadaUsuario, depois realizar a atualização do tabuleiro através
     * do método atualizaTabuleiro. Lembre-se que para chamar o método
     * obterJogadaUsuario
     * é necessário saber quais posições estão livres
     * Nível de complexidade: 5 de 10
     */
    static void processarVezUsuario(char caractereUsuario) {
        //TODO 17: Implementar método conforme explicação
        System.out.println("VEZ DO USUÁRIO");
        System.out.println();

        String posicoesLivres = retornarPosicoesLivres();

        int[] jogada = obterJogadaUsuario(posicoesLivres, teclado);

        atualizaTabuleiro(jogada, caractereUsuario);
    }

    /*
     * Descrição: Utilizado para realizar as ações necessárias para processar a vez
     * do computador jogar. Este método é encarregado de obter a jogada do
     * computador através do método obterJogadaComputador, depois realizar a
     * atualização do tabuleiro através do método atualizaTabuleiro. 
     * Lembre-se que para chamar o método obterJogadaUsuario
     * é necessário saber quais posições estão livres 
     * Nível de complexidade: 5 de 10 se o computador for jogar aleatoriamente
     * Nível de complexidade: 8 de 10 se o computador for jogar sempre para se
     * defender
     * Nível de complexidade: 10 de 10 se o computador for jogar para ganhar
     */
    static void processarVezComputador(char caractereComputador) {
        // TO DO 18: Obter a lista de posições livres no tabuleiro

        // lembrando que: x = linha e y = coluna

        // declaração da variável tipo String das posições livres da matriz
        String listaPosicoesLivres = "";

        // montagem da lista de posições que estão vazias no formato xy
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {

                // caso a posição estiver livre
                if (tabuleiro[linha][coluna] == ' ') {
                    listaPosicoesLivres += linha + "" + coluna + ";";
                }
            }
        }

        System.out.println("Posições Livres: " + listaPosicoesLivres);

        // caso não ter mais posições livres o computador não joga
        if (listaPosicoesLivres.isEmpty()) {
            System.out.println("Computador não consegue jogar pois não há mais posições livres!");
            return; // Sai do método, pois não há jogada possível
        }

        // seleciona a primeira posição livre para jogar
        String jogadaComputador = listaPosicoesLivres.split(" ")[0];
        int linhaComputador = Character.getNumericValue(jogadaComputador.charAt(0));
        int colunaComputador = Character.getNumericValue(jogadaComputador.charAt(1));

        // atualiza o tabuleiro com a jogada do computador
        tabuleiro[linhaComputador][colunaComputador] = caractereComputador;
        System.out.println(
                "Computador jogou na posição: Linha: " + (linhaComputador + 1) + ", Coluna: " + (colunaComputador + 1));
    }

    static String retornarPosicoesLivres() {
        // TO DO: 19: Método responsável por identificar e retornar todas as posições livres do tabuleiro.

        // StringBuilder: permite montar a string final sem criar várias instâncias de String
        StringBuilder posicoesLivres = new StringBuilder();

        // variável que representa o número da posição lógica no tabuleiro (1 a 9)
        int posicao = 1;

        // percorre todas as linhas do tabuleiro
        for (int linha = 0; linha < tabuleiro.length; linha++) {

            // percorre todas as colunas da linha atual
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {

                // se o caractere na posição for um espaço (' '), significa que está livre
                if (tabuleiro[linha][coluna] == ' ') {
                    posicoesLivres.append(posicao).append(" ");
                }

                // incrementa a posição lógica independente de estar ocupada ou não
                posicao++;
            }
        }

        // retorna a String contendo todas as posições livres
        return posicoesLivres.toString();
    }

    /*
     * Descrição: Utilizado para verificar se o jogador identificado por
     * caractereJogador ganhou o jogo. No jogo da velha um usuário ganha
     * quando ele completa uma linha ou uma coluna ou uma diagonal. Assim
     * este método verifica todas as possibilidades. No entanto, este método
     * utiliza outros métodos para auxiliar nesta verificação. Para identificar
     * se o usuário em questão ganhou na linha, é invocado o método
     * teveGanhadorLinha(), para identificar na coluna é invocado o método
     * teveGanhadorColuna(), para identificar na diagonal principal é invocado
     * o método teveGanhadorDiagonalPrincipal() e para identificar na diagonal
     * secundária é utilizado o método teveGanhadorDiagonalSecundaria(). Se
     * o pelo menos um destes métodos retornar verdadeiro, o método teveGanhador
     * retorna true, caso contrário retorna false
     * Nível de complexidade: 4 de 10 se o tabuleiro for fixo 3x3
     * Nível de complexidade: 8 de 10 se o tabuleiro dinâmico 
     */
    static boolean teveGanhador(char caractereJogador) {
        //TODO 20: Implementar método conforme explicação

        return teveGanhadorLinha(caractereJogador) 
        || teveGanhadorColuna(caractereJogador)
        || teveGanhadorDiagonalPrincipal(caractereJogador) 
        || teveGanhadorDiagonalSecundaria(caractereJogador);

    }

    /*
     * Descrição: Todos os métodos abaixo, teveGanhador... funcionam da mesma forma.
     * Recebem como parametro o tabuleiro e o caractereJogador. Cada um dos métodos
     * verificam no tabuleiro se o caractere do jogador está presente em todas as
     * posições, ou seja, o método teveGanhadorLinha verifica em todas as posicoes
     * de uma determinada linha se elas estão preenchidas com o caractere informado
     * no caractereJogador. Se estiver presente retorna true, caso contrário retorna
     * false.
     * Nível de complexidade: 4 de 10 se o tabuleiro for fixo 3x3
     * Nível de complexidade: 8 de 10 se o tabuleiro dinâmico 
     */
    static boolean teveGanhadorLinha(char caractereJogador) {
        // TODO 21: Implementar método conforme explicação
    }

    static boolean teveGanhadorColuna(char caractereJogador) {
        // TODO 22: Implementar método conforme explicação

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

    /*
     * Descrição: Utilizado para limpar a console, para que seja exibido apenas o
     * conteúdo atual do jogo. Dica: Pesquisa na internet por "Como limpar console
     * no java ProcessBuilder"
     * Nível de complexidade: 3 de 10
     */
    static void limparTela() {
        //TODO 25: Implementar método conforme explicação  
        try {
            String os = System.getProperty("os.name").toLowerCase();

            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // Linux/Mac usa "clear"
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Não foi possível limpar o console: " + e.getMessage());
            Thread.currentThread().interrupt();
        }      
    }

    /*
     * Descrição: Utilizado para imprimir o tabuleiro o conteúdo do tabuleiro na
     * tela. Recebe o tabuleiro como parametro e imprime o conteúdo de cada posição
     * do tabuleiro na tela. Imprimi o conteúdo no formato de uma grade. Para o
     * primeiro nível de complexidade considere um tabuleiro apenas de tamanho 3x3,
     * 3 linhas e 3 colunas.
     * Nível de complexidade: 4 de 10
     */
    static void exibirTabuleiro() {
        // TODO 26: Implementar método conforme explicação
        // execute no início deste método a chamada ao método limparTela
        // para garantir que seja exibido o tabuleiro sem nenhum conteúdo antes dele.

        limparTela();

        for (int linha = 0; linha < 3; linha++) {

            for (int colunas = 0; colunas < 3; colunas++) {
                
                System.out.println(" " + tabuleiro[linha][colunas] + " ");

                if (colunas < 2) {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (linha < 2) {
                System.out.println("--------");
            }
        }
    }

    /*
     * Descrição: Utilizado para atualizar o tabuleiro com o caractere que
     * identifica o jogador. Este método recebe o tabuleiro, um vetor jogada com
     * duas posicoes. jogada[0] representa a linha escolhida pelo jogador. jogada[1]
     * representa a coluna escolhida pelo jogador. Os valores armazenados no vetor
     * já deve estar no formato de índice, ou seja, se jogada[0] contiver o valor
     * 1 e jogada[1] contiver o valor 2, significa que o índice/linha 1 e
     * índice/coluna 2 da matriz devem ser atualizados com o caractere informado na
     * variável caractereJogador. Depois de atualizar o tabuleiro, o mesmo deve ser
     * retornado através do comando return
     * Nível de complexidade: 3 de 10
     */

    // Atualiza tabuleiro com o caractere correspondente.
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

    /*
     * Descrição: Utilizado para exibir a frase: O computador venceu!, e uma ART
     * ASCII do computador feliz. Este método é utilizado quando é identificado que
     * o computador venceu a partida. Lembre-se que para imprimir uma contrabara \ é
     * necessário duas contra barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirVitoriaComputador() {
        // TODO 28: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para exibir a frase: O usuário venceu!, e uma ARTE ASCII
     * do usuário feliz. Este método é utilizado quando é identificado que o usuário
     * venceu a partida. Lembre-se que para imprimir uma contrabara \ é necessário
     * duas contra barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirVitoriaUsuario() {
        // TODO 29: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para exibir a frase: Ocorreu empate!, e uma ARTE ASCII
     * do placar 0 X 0. Este método é utilizado quando é identificado que ocorreu
     * empate. Lembre-se que para imprimir uma contrabara \ é necessário duas contra
     * barras \\
     * Nível Complexidade: 2 de 10
     */
    static void exibirEmpate() {
        // TODO 30: Implementar método conforme explicação
    }

    /*
     * Descrição: Utilizado para analisar se ocorreu empate no jogo. Para o primeiro
     * nível de deficuldade, basta verificar se todas as posições do tabuleiro não
     * estão preenchidas com o caractere ' '. Não se preocupe se teve ganhador, não
     * é responsabilidade deste método esta análise. Sugestão: pense em utilizar a
     * função retornarPosicoesLivres. Retorne true se teve empate ou false
     * Nível de complexidade: 3 de 10
     */

    // Verifica todas as casas do tabuleiro, caso estejam todas ocupadas, retorna verdadeiro, ou seja, empate!
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

    /*
     * Descrição: Utilizado para realizar o sorteio de um valor booleano. Este
     * método deve sortear um valor entre true ou false. Este valor será
     * utilizado para identificar quem começa a jogar. Dica: pesquise sobre
     * o método random.nextBoolean() na internet. Após ralizar o sorteio o 
     * método deve retornar o valor sorteado.
     * Nível de complexidade: 3 de 10
     */
    static boolean sortearValorBooleano() {
        java.util.Random random = new java.util.Random();
        boolean sorteio = random.nextBoolean();
        // comando para preparar a variavel para o sorteio
        return sorteio;
        //TODO 32: Implementar método conforme explicação
    }

}
