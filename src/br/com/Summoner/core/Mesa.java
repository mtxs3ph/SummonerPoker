/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core;

import br.com.Summoner.core.base.Deck;
import br.com.Summoner.core.base.TipoDeck;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoMonstro;
import br.com.Summoner.core.calculos.Math;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dferreira
 */
public final class Mesa implements Cloneable {

    public Deck MonsterDeck = null;
    public Deck ItemDeck = null;
    public Deck TerrainDeck = null;
    public List<Card> ItensRevelados = null;

    public Mesa() throws ClassNotFoundException, FileNotFoundException {
            ResetarMesa();
    }

    public Card ComprarMonstro() {
        return Math.GetRandomCardFromList(MonsterDeck.Cartas);
    }

    public Card ComprarItem() {
        return Math.GetRandomCardFromList(ItemDeck.Cartas);
    }

    public void RevelarItens(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            ItensRevelados.add((Card) Math.GetRandomCardFromList(ItemDeck.Cartas));
        }
    }

    public void ResetarMesa() throws ClassNotFoundException, FileNotFoundException {
//TODO: Implementar init    
            MonsterDeck = new Deck(TipoDeck.Monstro);
            ItemDeck = new Deck(TipoDeck.Item);
            
          //TerrainDeck = new Deck() {{ Cartas = CardListFactory.GetCardList(MonsterCard.class, Configuracoes.CaminhoCSVMonstros); }};
            ItensRevelados = new ArrayList<>();
    }

    @Override
    public Mesa clone() throws CloneNotSupportedException {
        return (Mesa) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
}
