/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.warrior;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoCarta;
import br.com.Summoner.core.base.tipos.TipoMonstro;

import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.AccessibleAction;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class WarriorCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Setco"))
    List<WarriorSet> BonusSet;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        long bonusDoSet = 0;
        List<WarriorEquipType> equipamentosNosItens = jogada.CartasUtilizadas.stream().filter(item -> item.TipoCarta == TipoCarta.Item).map(item -> ((WarriorItemCard) item).Equipamentos.stream().map(equip -> equip.Equipamento)).flatMap(item -> item).distinct().collect(Collectors.toList());
        
        for (WarriorSet set : this.BonusSet) {
            long bonusSetAtual = 0;
            
            List<WarriorEquipType> listaEquipamentosNoSet = set.Equipamentos.stream().map(equip -> equip.Equipamento).collect(Collectors.toList());
            
            for (WarriorEquipType equipeNoItem : equipamentosNosItens)
            {
               WarriorEquipType referenciaEncontrada = listaEquipamentosNoSet.stream().filter(equip -> equip == equipeNoItem).findFirst().orElse(WarriorEquipType.Desconhecido);
               if (referenciaEncontrada!= WarriorEquipType.Desconhecido)
                listaEquipamentosNoSet.remove(referenciaEncontrada);
            }
            
            if (listaEquipamentosNoSet.isEmpty())
               bonusSetAtual = set.BonusForca;
            
            if(bonusSetAtual > bonusDoSet)
                bonusDoSet = bonusSetAtual;
        }
        return bonusDoSet;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}