/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core;

import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoCarta;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public class Turno {

    public Mesa MesaNoTurno;
    public List<Jogada> Jogadas;
    public List<Jogada> JogadaVencedoraOuEmpate;
    public boolean usaBonus = true;

    public Turno() throws ClassNotFoundException, FileNotFoundException {
        MesaNoTurno = new Mesa();
        Jogadas = new ArrayList<>();
        JogadaVencedoraOuEmpate = new ArrayList<>();
    }

    public void DeterminaVencedor() {

        Jogadas.forEach(jogada
                -> {
            int forcaTotalDaJogada = 0;
            forcaTotalDaJogada += (int) jogada.CartasUtilizadas.stream().mapToLong(carta -> carta.ForcaBase).sum();
            List<Jogada> jogadasAdversarios = Jogadas.stream().filter(jogadaAdversario -> !jogadaAdversario.Jogador.Nome.equals(jogada.Jogador.Nome)).collect(Collectors.toList());
            List<List<Card>> listaCartasUtilizadas = jogadasAdversarios.stream().map(jogadaAdversario -> jogadaAdversario.CartasUtilizadas).collect(Collectors.toList());
            List<Card> listaMonstrosAdversarios = listaCartasUtilizadas.stream().map(listaCartas -> listaCartas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Criatura)).map(mapper -> mapper.findFirst().get()).collect(Collectors.toList());

            long forcaBonusJogada = 0;
            if (usaBonus) {
                forcaBonusJogada = jogada.CartasUtilizadas.stream().mapToLong(carta -> carta.CalculaBonus(jogada, listaMonstrosAdversarios)).sum();
            }
            forcaTotalDaJogada += forcaBonusJogada;
            jogada.ForcaTotal = forcaTotalDaJogada;
        });

        if (usaBonus) {
            Jogadas.forEach(jogada
                    -> {
                jogada.Jogador.Mao.forEach(carta -> {
                    jogada.ForcaTotal += carta.CalculaBonusPosJogadas(jogada, Jogadas.stream().filter(jogadaAdv -> jogadaAdv != jogada).collect(Collectors.toList()));
                });
            });
        }

        long forcaMaxima = Jogadas.stream().max(Comparator.comparing(i -> i.ForcaTotal)).get().ForcaTotal;
        JogadaVencedoraOuEmpate = Jogadas.stream().filter(jogada -> jogada.ForcaTotal == forcaMaxima).collect(Collectors.toList());

        Jogadas.sort(Comparator.comparing(c -> c.Jogador.OrdemMesa));
    }

}
