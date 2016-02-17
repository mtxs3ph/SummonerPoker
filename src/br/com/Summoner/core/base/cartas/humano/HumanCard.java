/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.humano;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.jsefa.csv.annotation.CsvDataType;

import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class HumanCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Combo"))
    List<HumanMonsterCombo> Combos;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        List<Card> cartasUtilizadasCombo = jogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item && carta.TipoMonstro == TipoMonstro.Humano).collect(Collectors.toList());
        HumanComboResult comboResult = new HumanComboResult();
        long forcaBonus = 0;

        if (cartasUtilizadasCombo.size() > 0) {
//            String[] golpesCombo = cartasUtilizadasCombo.stream().map(carta -> ((HumanItemCard) carta).tipoGolpe.toString()).collect(Collectors.joining()).split("");
            String[] golpesCombo = {};
            Arrays.sort(golpesCombo);
            String comboRealizado = StringUtils.join(Arrays.asList(golpesCombo), "");
            comboResult.ComboRealizado = comboRealizado;
//            comboResult.CombosCarta.addAll(this.Combos);

            for (int i = 0; i < this.Combos.size(); i++) {
                HumanMonsterCombo combo = this.Combos.get(i);

                char[] criaturaCombo = combo.CombinacaoCombo.toCharArray();
                Arrays.sort(criaturaCombo);
                String comboDaCriatura = new String(criaturaCombo);

                boolean encontrou = comboRealizado.contains(comboDaCriatura);

                if (encontrou && forcaBonus < combo.DanoCombo) {

                    forcaBonus = combo.DanoCombo;
                    comboResult.ValorComboRealizado = forcaBonus;
                    comboResult.ComboCriatura = comboDaCriatura;

                }
            }

            CombosRealizados.add(comboResult);
        }

        return forcaBonus;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }

    public static List<HumanComboResult> CombosRealizados = new ArrayList<>();

    public static void GeraEstatisca(StringBuilder strbOut) {

        strbOut.append("\r\n");
        strbOut.append("\r\n");
        strbOut.append(" Estatísticas de Combo Humano ");
        strbOut.append("\r\n");
        strbOut.append("\r\n");

        Comparator<HumanComboResult> byCombo = (e1, e2) -> e2.ComboCriatura.compareTo(e1.ComboCriatura);
        CombosRealizados = CombosRealizados.stream().filter(c -> c.ComboCriatura != null && c.ComboCriatura.length() > 1).sorted(byCombo).collect(Collectors.toList());

        Map<String, List<HumanComboResult>> combosRealizados;
        combosRealizados = CombosRealizados.stream()
                .collect(Collectors.groupingBy(classifier -> classifier.ComboCriatura));

        for (Map.Entry<String, List<HumanComboResult>> entry : combosRealizados.entrySet()) {
            strbOut.append(" Combo Realizado:\t").append(entry.getKey()).append("\tQuantidade:\t").append(entry.getValue().size()).append("\r\n");
        }

//        for (HumanComboResult comboResult : CombosRealizados) {
//
//            if (comboResult.ComboRealizado.length() > 1) {
//                strbOut.append(" Combo Realizado - ").append(comboResult.ComboRealizado).append("\r\n");
//                strbOut.append(" Combo Utilizado - ").append(comboResult.ComboCartaUtilizado == null ? "" : comboResult.ComboCartaUtilizado).append("\r\n");
//                strbOut.append("\r\n");
//            }
//            for (HumanMonsterCombo combo : comboResult.CombosCarta)
//            {
//                strbOut.append(" Combo - ").append(combo.CombinacaoCombo);
//                strbOut.append("\r\n");
//            }
//            strbOut.append("\r\n");
//            strbOut.append("\r\n");
//        }
        strbOut.append("\r\n");
        strbOut.append("\r\n");

    }
}
