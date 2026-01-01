package org.jogo.da.velha.tools;

public class Utilities {
  public static int[] converterJogada(String jogada) {
    int[] resultado = new int[2];

    resultado[0] = Character.getNumericValue(jogada.charAt(0));
    resultado[1] = Character.getNumericValue(jogada.charAt(1));

    return resultado;
}

}
