/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.calculos;

import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.util.Random;
import java.util.List;

/**
 *
 * @author dferreira
 */
public class Math {

    public static Card GetRandomCardFromList(List<Card> PoolOfCards) {
        int indRand = -1;
        do {
            indRand = Random.RandomNumber(0, PoolOfCards.size());
            try {
                PoolOfCards.get(indRand);
            } catch (IndexOutOfBoundsException e) {
                indRand = -1;
            }
        } while (indRand == -1 && PoolOfCards.size() > 0 );

        Card cartaSelecionada = PoolOfCards.get(indRand);
        PoolOfCards.remove(indRand);

        return cartaSelecionada;
    }
}
