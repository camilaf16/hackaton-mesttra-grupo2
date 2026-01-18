package org.jogo.da.velha;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class MainTest {
    
    @Test
    void deveAceitarEntradaStringValida(){
       
       //simula o usuário digitando "X"
       String entradaSimulada = "X\n";
       Main.teclado =  new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        char caractereUsuario = Main.obterCaractereUsuario();

        assertEquals('X', caractereUsuario);
    }

    @Test
    void deveAceitarEntradaStringMinusculo(){
        
        //simula o usuário digitando "x"
        String entradaSimulada = "x\n";
        Main.teclado =  new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        char caractereUsuario = Main.obterCaractereUsuario();

        assertEquals('X', caractereUsuario);
    }

    @Test
    void deveAceitarEntradaStringTamanhoMaiorQueZero(){
        //simula o usuário digitando "XIs"
        String entradaSimulada = "XIs\n";
        Main.teclado =  new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        char caractereUsuario = Main.obterCaractereUsuario();

        assertEquals('X', caractereUsuario);

    }

    @Test
    void deveRejeitarEntradaStringInvalidaEAceitarValida(){
        //simula o usuário digitando "S"
        String entradaSimulada = "S\nx\n";
        Main.teclado =  new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        char caractereUsuario = Main.obterCaractereUsuario();

        assertEquals('X', caractereUsuario);

    }

    @Test
    void deveRejeitarEntradaVaziaEAceitarValida(){
        //simula o usuário digitando ""
        String entradaSimulada = "\nx\n";
        Main.teclado =  new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        char caractereUsuario = Main.obterCaractereUsuario();

        assertEquals('X', caractereUsuario);

    }

    @Test
    void deveAceitarJogadaValida() {
        // simula o usuário digitando "3 3"
        String entradaSimulada = "3 3\n";
        Scanner teclado = new Scanner(
                new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        String posicoesLivres = "0 0 | 0 1 | 0 2 | 1 0 | 1 1 | 1 2 | 2 0 | 2 1 | 2 2";

        int[] jogada = Main.obterJogadaUsuario(posicoesLivres, teclado);

        assertArrayEquals(new int[]{2, 2}, jogada);
    }

    @Test
    void  deveRejeitarJogadaInvalidaEDepoisAceitarValida() {
        // o usuário inicia digitando os valores errados, pois não estão armazenados em posicoes livres
        // e depois digita os valores certos
        String entradaSimulada = "3 3\n1 1\n";
        Scanner scanner = new Scanner(
                new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        String posicoesLivres = "0 0 | 0 1 | 0 2 | 1 0 | 1 1 | 1 2 | 2 0 | 2 1 ";

        int[] jogada = Main.obterJogadaUsuario(posicoesLivres, scanner);

        assertArrayEquals(new int[]{0, 0}, jogada);
    }

    @Test
    void deveRejeitarFormato12EAceitarDepoisOFormatoValido() {
        // usuário erra e depois acerta
        String entradaSimulada = "12\n1 2\n";
        Scanner scanner = new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        String posicoesLivres = "0 1";

        int[] jogada = Main.obterJogadaUsuario(posicoesLivres, scanner);

        assertArrayEquals(new int[]{0, 1}, jogada);
    }

    @Test
    void deveRejeitarLetrasEAceitarDepoisNumeros() {
        // usuário erra e depois acerta
        String entradaSimulada = "a a\n1 2\n";
        Scanner scanner = new Scanner(
            new ByteArrayInputStream(entradaSimulada.getBytes())
        );

        String posicoesLivres = "0 1";

        int[] jogada = Main.obterJogadaUsuario(posicoesLivres, scanner);

        assertArrayEquals(new int[]{0, 1}, jogada);
    }




   @Test
   void deveDetectarVitoriaEmQualquerColuna() {
       
       char jogador = 'X';

       for (int col = 0; col < 3; col++) {
           Main.inicializarTabuleiro();

           for (int linha = 0; linha < 3; linha++) {
               Main.tabuleiro[linha][col] = jogador;
            }

            assertTrue(Main.teveGanhador(jogador));
        }
   }

   @Test
   void deveDetectarVitoriaEmQualquerLinha() {
       
       char jogador = 'X';

       for (int linha = 0; linha < 3; linha++) {
           Main.inicializarTabuleiro();

           for (int col = 0; col < 3; col++) {
                Main.tabuleiro[linha][col] = jogador;
            }

            assertTrue(Main.teveGanhador(jogador));
        }
    }

   @Test
   void deveDetectarVitoriaDiagonalPrincipal() {
       
       char jogador = 'X';

       Main.tabuleiro[0][0] = jogador;
       Main.tabuleiro[1][1] = jogador;
       Main.tabuleiro[2][2] = jogador;

       assertTrue(Main.teveGanhador(jogador));
    } 
  
  
    @Test
    void deveDetectarVitoriaDiagonalSecundaria() {
       char jogador = 'X';

       Main.tabuleiro[0][2] = jogador;
       Main.tabuleiro[1][1] = jogador;
       Main.tabuleiro[2][0] = jogador;

       assertTrue(Main.teveGanhador(jogador));
    }

}
