/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base;

import br.com.Summoner.core.base.cartas.warlock.WarlockCard;
import br.com.Summoner.core.base.cartas.warlock.WarlockItemCard;
import br.com.Summoner.core.base.cartas.monk.MonkItemCard;
import br.com.Summoner.core.base.cartas.monk.MonkCard;
import br.com.Summoner.core.base.cartas.warrior.WarriorItemCard;
import br.com.Summoner.core.base.cartas.warrior.WarriorCard;
import br.com.Summoner.core.Configuracoes;
import br.com.Summoner.core.base.cartas.CardLoader;
import br.com.Summoner.core.base.cartas.fera.*;
import br.com.Summoner.core.base.cartas.mage.MageCard;
import br.com.Summoner.core.base.cartas.mage.MageItemCard;
import br.com.Summoner.core.base.cartas.wizard.WizardCard;
import br.com.Summoner.core.base.cartas.wizard.WizardItemCard;
import br.com.Summoner.core.base.interfaces.IDeck;
import br.com.Summoner.core.base.interfaces.Card;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dferreira
 */
public final class Deck implements IDeck {

    public List<Card> Cartas;
    public List<String> BaralhosUsados = null;

    public Deck(TipoDeck tipoDeck) throws FileNotFoundException {
        this.BaralhosUsados = Configuracoes.BaralhosUsados;
        this.RemontarDeck(tipoDeck);
    }

    @Override
    public void RemontarDeck(TipoDeck tipoDeck) throws FileNotFoundException {
        Cartas = new ArrayList<>();

        for (String deck : this.BaralhosUsados) {
            switch (deck) {
                case "Fera":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(FeraItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(FeraCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Mage":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(MageItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(MageCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Monk":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(MonkItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(MonkCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Warlock":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(WarlockItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(WarlockCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Wizard":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(WizardItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(WizardCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Warrior":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(WarriorItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(WarriorCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;    
                default:
                    break;
            }
        }

    }

}
