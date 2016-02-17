/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.Mesa;

import br.com.Summoner.core.base.tipos.*;

import br.com.Summoner.core.base.comportamentos.ComportamentoFactory;
import br.com.Summoner.core.base.interfaces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public final class Player implements Cloneable {

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public Player() throws Exception {
        this.Comportamento = ComportamentoFactory.ObtemComportamento();
    }

    public String Nome;
    public List<Card> Mao;
    public int OrdemMesa;
    //MonsterCard MonstroDescartado;

    public Card MonstroNaMao() {
        return (Card) Mao.stream().filter(item -> item.TipoCarta== TipoCarta.Criatura).findFirst().get();
    }

    public String MonstroNomeTipo() {
        return (Mao.stream().filter(item -> item.TipoCarta== TipoCarta.Criatura).findFirst().get().Nome + "-"+ Mao.stream().filter(item -> item.TipoCarta== TipoCarta.Criatura).findFirst().get().TipoMonstro.toString());
    }
    public void Descartar() {
        this.Comportamento.DescartarMonstrosExtras(Mao);
        this.Comportamento.DescartarItensExtras(Mao);
    }

    public Jogada RealizaJogada(Mesa mesa) throws CloneNotSupportedException {
        List<Card> cartasSelecionadas = mesa.ItensRevelados.stream().filter(item -> item.TipoMonstro == this.MonstroNaMao().TipoMonstro).collect(Collectors.toList());
        Jogada jogada = new Jogada(this, cartasSelecionadas);
        return jogada;
    }

    public void ResetarJogador() {
        this.Mao = new ArrayList<>();
    }

    IComportamento Comportamento;

}
