/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.comportamentos;

import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.interfaces.IComportamento;
import br.com.Summoner.core.base.tipos.*;
import br.com.Summoner.core.calculos.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public class MaisForte implements IComportamento{

    @Override
    public void DescartarMonstrosExtras(List<Card> mao)
        {
//            Comparator<ICard> byForca = (e1, e2) -> Integer.compare(
//            e1.getForcaBase(), e2.getForcaBase());
//            
////            Card cartaMaisFraca = mao.stream().filter(carta -> carta.getTipoCarta() == TipoCarta.Criatura).sorted(byForca).findFirst().get();
//            mao.remove(cartaMaisFraca);

        }

    @Override
    public void DescartarItensExtras(List<Card> mao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
