/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.berserker;

import br.com.Summoner.core.base.cartas.berserker.*;
import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoMonstro;

import java.util.List;
import java.util.stream.Collectors;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class BerserkerCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Set"))
    List<BerserkerSet> BonusSet;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        return 0;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
