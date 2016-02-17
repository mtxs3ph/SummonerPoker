/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.comportamentos;

import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.interfaces.IComportamento;
import br.com.Summoner.core.base.tipos.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public class Combinador implements IComportamento{

    @Override
    public void DescartarMonstrosExtras(List<Card> mao) 
        {
            if (mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Criatura).count() < 2)
                return;
            
            if (mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Criatura).collect(Collectors.groupingBy(carta -> carta.TipoMonstro)).size() == 1)
            {
                new MaisForte().DescartarMonstrosExtras(mao);
                return;
            }

            Card cartaComItem = mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Criatura && mao.stream().filter(item -> item.TipoCarta == TipoCarta.Item && item.TipoMonstro == carta.TipoMonstro).count() > 0).findFirst().orElse(null);

            if (null == cartaComItem)
            {
                new MaisForte().DescartarMonstrosExtras(mao);
                return;
            }

            Card cartaItemCombinado = mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).findFirst().get();
            Card cartaDescartavel = mao.stream().filter(carta -> carta != cartaComItem && carta.TipoCarta == TipoCarta.Criatura).findFirst().get();

            int forcaComItem = cartaItemCombinado.ForcaBase + cartaComItem.ForcaBase;

            List<Card> cartasSelecionadas = new ArrayList<Card>() {{ add(cartaComItem); add(cartaItemCombinado); }};
            
//            forcaComItem += Bonus.Calcula(cartaComItem, cartasSelecionadas, new ArrayList<>());
//            forcaComItem += Bonus.Calcula(cartaItemCombinado, cartasSelecionadas, new ArrayList<>());

            //if (cartaDescartavel.ForcaBase > forcaComItem)
            //    mao.Remove(cartaComItem);
            //else
                mao.remove(cartaDescartavel);

        }

    @Override
    public void DescartarItensExtras(List<Card> mao) {
        Comparator<Card> byForca = (e1, e2) -> Integer.compare(
                e1.ForcaBase, e2.ForcaBase);
        
        if (mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).count() < 2)
                return;
        
        Card criatura = mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Criatura).findFirst().orElse(null);
        
        
        List<Card> cartasSelecionadas = mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item && criatura.TipoMonstro == carta.TipoMonstro).collect(Collectors.toList());
        
        if (cartasSelecionadas.size() > 0)
        {
            cartasSelecionadas = cartasSelecionadas.stream().sorted(byForca).collect(Collectors.toList());
            Card cartaSelectionada = cartasSelecionadas.get(cartasSelecionadas.size() - 1);
            cartasSelecionadas.removeIf(carta -> carta != cartaSelectionada);
            
            mao.removeAll(cartasSelecionadas);
        }
        else
        {
            cartasSelecionadas = mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).sorted(byForca).collect(Collectors.toList());
            Card cartaSelectionada = cartasSelecionadas.get(cartasSelecionadas.size() - 1);
            cartasSelecionadas.removeIf(carta -> carta != cartaSelectionada);
            
            mao.removeAll(cartasSelecionadas);
        }
    }
}
