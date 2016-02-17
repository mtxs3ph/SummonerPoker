/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.wizard;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "Spell")
public class WizardManaGain {
    @CsvField(pos = 1)
    public long MinGrimorio;
    @CsvField(pos = 2)
    public long MaxGrimorio;
    @CsvField(pos = 3)
    public long Mana;
}
