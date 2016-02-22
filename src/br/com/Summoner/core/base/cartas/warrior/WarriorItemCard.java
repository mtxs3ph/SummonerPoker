/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.warrior;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import java.util.List;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Items")
public class WarriorItemCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Equip"))
    List<WarriorEquipamento> Equipamentos; 
    
    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        return 0;
    }
    
    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogadasDosAdversarios) {
        return 0;
    }
}
