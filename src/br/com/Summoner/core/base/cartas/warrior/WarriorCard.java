/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.warrior;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class WarriorCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Equip"))
    List<WarriorEquipamento> Equipamentos;

    @CsvSubRecordList(pos = 7, records = @Record(prefix = "Bonus"))
    List<WarriorSetBonus> SetBonus;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        long forcaBonus = 0;
        List<WarriorEquipamento> equipamentosUtilizadosParaBonus = new ArrayList<>();
        List<WarriorEquipamento> equipamentosNosItens = new ArrayList<>();
        equipamentosNosItens.addAll(jogada.CartasUtilizadas.stream().filter(item -> item.TipoCarta == TipoCarta.Item).map(item -> ((WarriorItemCard) item).Equipamentos.stream()).flatMap(item -> item).collect(Collectors.toList()));

        for (WarriorEquipamento slot : this.Equipamentos) {
            long bonusSetAtual = 0;
            boolean encontrou = false;
            WarriorEquipamento referenciaEncontrada = equipamentosNosItens.stream().filter(equip -> equip.Equipamento == slot.Equipamento && !equipamentosUtilizadosParaBonus.contains(equip)).findFirst().orElse(null);
            if (referenciaEncontrada != null) {
                equipamentosUtilizadosParaBonus.add(referenciaEncontrada);
            }
        }

        if (equipamentosUtilizadosParaBonus.size() > 0) {
            for (WarriorSetBonus setBonus : this.SetBonus) {
                if (setBonus.Quantidade <= equipamentosUtilizadosParaBonus.size()) {
                    if (forcaBonus < setBonus.BonusForca) {
                        forcaBonus = setBonus.BonusForca;
                    }
                }
            }
        }

        return forcaBonus;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
