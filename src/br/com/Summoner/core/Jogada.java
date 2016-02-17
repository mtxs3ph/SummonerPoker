/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core;

import br.com.Summoner.core.base.Player;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoCarta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public class Jogada implements Serializable, Cloneable{

    public Player Jogador;
    public long ForcaTotal;
    public List<Card> ItensSelecioadosDaMesa;
    public List<Card> CartasUtilizadas;

    public Jogada(Player jogador, List<Card> itensUsados) throws CloneNotSupportedException {
        this.Jogador = jogador.clone();
        this.ItensSelecioadosDaMesa = itensUsados;
        CartasUtilizadas = new ArrayList<>();
        CartasUtilizadas.add(jogador.MonstroNaMao());
        CartasUtilizadas.addAll(Jogador.Mao.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item && carta.TipoMonstro == Jogador.MonstroNaMao().TipoMonstro).collect(Collectors.toList()));
        CartasUtilizadas.addAll(itensUsados);
    }
    
    public static List<Jogada> cloneList(List<Jogada> jogadaList) throws CloneNotSupportedException {
    List<Jogada> clonedList = new ArrayList<>(jogadaList.size());
    for (Jogada jogada : jogadaList) {
        clonedList.add((Jogada) jogada.clone());
    }
    return clonedList;
}
    
    public void SomaBonusExtra(List<Jogada> jogadasAdversarios)
    {
        
    }
}
