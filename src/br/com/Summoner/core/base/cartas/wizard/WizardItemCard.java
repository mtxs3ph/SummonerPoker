/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.wizard;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.util.BooleanConverter;
import java.util.List;
import java.util.stream.Stream;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
@CsvDataType()
public class WizardItemCard extends Card {

    @CsvField(pos = 6, converterType = BooleanConverter.class)
    public boolean Grimorio;

    @CsvField(pos = 7)
    public long CustoMagia;

    @CsvField(pos = 8)
    public long DanoMagia;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        return 0;
    }
    
    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
