/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base;

import br.com.Summoner.core.Configuracoes;
import br.com.Summoner.core.base.cartas.CardLoader;
import br.com.Summoner.core.base.cartas.fera.*;
import br.com.Summoner.core.base.cartas.humano.*;
import br.com.Summoner.core.base.cartas.mago.MagoCard;
import br.com.Summoner.core.base.cartas.mago.MagoItemCard;
import br.com.Summoner.core.base.cartas.profano.*;
import br.com.Summoner.core.base.cartas.berserker.*;
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
                case "Mago":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(MagoItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(MagoCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Humano":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(HumanItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(HumanCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Profano":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(ProfanoItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(ProfanoCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Wizard":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(WizardItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(WizardCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;
                case "Berserker":
                    if (tipoDeck == TipoDeck.Item) {
                        Cartas.addAll((List) CardLoader.GetCardList(BerserkerItemCard.class, Configuracoes.CaminhoCSVItens));
                    } else {
                        Cartas.addAll((List) CardLoader.GetCardList(BerserkerCard.class, Configuracoes.CaminhoCSVMonstros));
                    }
                    break;    
                default:
                    break;
            }
        }

    }

}
