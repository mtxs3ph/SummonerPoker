/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.invoker;


import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Monst")
public class InvokerCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "ELEME"))
    List<InvokerMonsterSpell> Combinacoes;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        List<Card> cartasUtilizadasCombo = jogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item && carta.TipoMonstro == TipoMonstro.Humano).collect(Collectors.toList());
        
        long forcaBonus = 0;

//        if (cartasUtilizadasCombo.size() > 0) {
//            String[] matrixElementos = ListToMatrix.Convert(cartasUtilizadasCombo.stream().map(m -> ((InvokerItemCard) m).Elemento).toArray());
//            
//            for (int i = 0; i < this.Combinacoes.size(); i++)
//            {
//                InvokerMonsterSpell combo =  this.Combinacoes.get(i);
//                
//                char[] criaturaCombo = combo.CombinacaoCombo.toCharArray();
//                Arrays.sort(criaturaCombo);
//                String comboDaCriatura = new String(criaturaCombo);
//                
//                
//                String referenciaEncontrada = matrixElementos.stream().filter(combinacao -> combinacao.equals(combo.CombinacaoCombo)).findFirst().orElse("");
//                
//                boolean encontrou = Combinacoes.contains(comboDaCriatura);
//                
//                if (encontrou && forcaBonus < combo.DanoCombo)
//                    forcaBonus = combo.DanoCombo;
//            }
//        }

        return forcaBonus;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
