/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core;

import br.com.Summoner.core.base.Player;
import br.com.Summoner.core.base.tipos.TipoMonstro;
import br.com.Summoner.core.calculos.Estatisticas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


/**
 *
 * @author dferreira
 */
public class Partida {

    public List<Turno> Turnos = null;
    public List<Player> Jogadores;
    public Player VencedorDoJogo;
    Mesa MesaDaPartida = null;

    public static List<String> RetornaDecksPossiveis()
    {
        List<String> DecksPossiveis = new ArrayList<>();
        DecksPossiveis.add(TipoMonstro.Warrior.name());
        DecksPossiveis.add(TipoMonstro.Monk.name());
        DecksPossiveis.add(TipoMonstro.Mage.name());
        DecksPossiveis.add(TipoMonstro.Warlock.name());
        DecksPossiveis.add(TipoMonstro.Wizard.name());
        DecksPossiveis.add(TipoMonstro.Fera.name());
        DecksPossiveis.add(TipoMonstro.Nenhum.name());
        return DecksPossiveis;
    }
    public Partida() throws Exception {
        this.Turnos = new ArrayList<>(Configuracoes.NumeroDeTurnos);
        MesaDaPartida = new Mesa();
        Jogadores = new ArrayList<>(Configuracoes.NumeroDeJogadores);
        
        for (int i = 0; i < Configuracoes.NumeroDeJogadores; i++) {
            Player a = new Player();
            a.Mao = new ArrayList<>();
            a.Nome = "Jogador 0" + (i + 1);
            a.OrdemMesa = i + 1;
            Jogadores.add(a);
        }
    }

    public void AvancaOrdemJogadores() {
        for (int i = 1; i <= this.Jogadores.size(); i++)
        {
            Player jogadorAtual = this.Jogadores.get(i -1);
            jogadorAtual.OrdemMesa++;
            if (jogadorAtual.OrdemMesa > this.Jogadores.size())
                jogadorAtual.OrdemMesa = 1;
        }
        
        Jogadores.sort(Comparator.comparing(i -> i.OrdemMesa));
    }
    
    

    public void Jogar() throws CloneNotSupportedException, ClassNotFoundException, IOException {
        while (Turnos.size() < Configuracoes.NumeroDeTurnos) {
            Turno turnoAtual = new Turno();
            AvancaOrdemJogadores();
            Turnos.add(turnoAtual);

            Jogadores.forEach(jogador -> jogador.Mao.add(this.MesaDaPartida.ComprarMonstro()));
            for (int i = 1; i <= Configuracoes.QuantidadeItensCompradosPorJogador; i++)
            {
                Jogadores.forEach(jogador -> jogador.Mao.add(this.MesaDaPartida.ComprarItem()));
            }
            
//            Jogadores.forEach(jogador -> jogador.Descartar());

            MesaDaPartida.RevelarItens(Configuracoes.QuantidadeItensReveladosPorTurno);

            Jogadores.forEach(jogador -> {{
                    try {
                        turnoAtual.Jogadas.add(jogador.RealizaJogada(MesaDaPartida));
                    } catch (CloneNotSupportedException ex) {
                        LogManager.getLogger(Partida.class).log(Level.FATAL, ex);
                    }
                }
            });

            turnoAtual.DeterminaVencedor();

            turnoAtual.MesaNoTurno = (Mesa) MesaDaPartida.clone();
            MesaDaPartida.ResetarMesa();
            Jogadores.forEach(jogador -> jogador.ResetarJogador());
        }

        Estatisticas.GeraRelatorioPartidaAnalitico(this);
//        Estatisticas.GeraRelatorioResumoTurnos(this);

    }
}
