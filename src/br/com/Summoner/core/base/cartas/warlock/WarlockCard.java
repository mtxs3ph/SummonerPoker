/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.warlock;

import br.com.Summoner.UI.FXMLDocumentController;
import br.com.Summoner.core.Jogada;

import br.com.Summoner.core.base.interfaces.Card;
import java.util.Collections;

import java.util.Comparator;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class WarlockCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Drain"))
    public List<WarlockResourceGain> ListaGanhoMana;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        return 0;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogadasDosAdversarios) {
        List<Card> itens = minhaJogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).collect(Collectors.toList());
        long valorRecursoDark = 0;
        long valorRoubado = 0;
        boolean singleTarget = true;

        if (itens.size() > 0) {
            valorRecursoDark = itens.stream().collect(Collectors.summarizingLong(mapper -> ((WarlockItemCard) mapper).DarkEnergy)).getSum();
        }

        if (valorRecursoDark > 0) {
            long valorPrevisto = 0;
            for (int i = 0; i < this.ListaGanhoMana.size(); i++) {
                WarlockResourceGain recurso = this.ListaGanhoMana.get(i);
                if (valorRecursoDark >= recurso.MinGrimorio && valorRecursoDark <= recurso.MaxGrimorio) {

                    if (recurso.SingleTarget) {
                        if (valorPrevisto < recurso.DrainValue) {
                            valorPrevisto = recurso.DrainValue;
                            valorRoubado = recurso.DrainValue;
                        }
                    } else if (valorPrevisto < recurso.DrainValue * jogadasDosAdversarios.size()) {
                        singleTarget = false;
                        valorPrevisto = recurso.DrainValue * jogadasDosAdversarios.size();
                        valorRoubado = recurso.DrainValue;
                    }
                }
            }

            try {
                valorRoubado = RoubaDano(valorRoubado, jogadasDosAdversarios, singleTarget);
            } catch (CloneNotSupportedException ex) {
                return 0;
            }

        }

        return valorRoubado;

    }

    private long RoubaDano(long valorRoubo, List<Jogada> jogadaAdversarios, boolean singleTarget) throws CloneNotSupportedException {
        long valorTotalRoubado = 0;
        if (singleTarget) {
            List<Jogada> clone = Jogada.cloneList(jogadaAdversarios);

            Collections.sort(clone, new Comparator<Jogada>() {
                @Override
                public int compare(Jogada o1, Jogada o2) {
                    return Long.compare(o2.ForcaTotal, o1.ForcaTotal);
                }
            });
            
            Jogada jogadaClone = clone.get(0);
            Jogada subtraida = jogadaAdversarios.stream().filter(j -> j.Jogador.Nome.equals(jogadaClone.Jogador.Nome)).findFirst().orElse(null);
            subtraida.ForcaTotal -= valorRoubo;
            if (subtraida.ForcaTotal < 0) {
                subtraida.ForcaTotal = 0;
            }
            valorTotalRoubado = valorRoubo;
        } else {
            for (Jogada adversario : jogadaAdversarios) {
                adversario.ForcaTotal -= valorRoubo;
                if (adversario.ForcaTotal < 0) {
                    adversario.ForcaTotal = 0;
                }
                valorTotalRoubado += valorRoubo;
            }
        }
        return valorTotalRoubado;
    }
}
