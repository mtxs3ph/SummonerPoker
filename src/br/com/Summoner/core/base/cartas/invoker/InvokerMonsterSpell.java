/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.invoker;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix="Combo") 
public class InvokerMonsterSpell {
    @CsvField(pos = 1)
    public String CombinacaoCombo;
    @CsvField(pos = 2)
    public long DanoCombo;
}
