/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.humano;

import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
public class HumanAtack {
    
    @CsvField(pos = 1)
    HumanAtackType tipoGolpe;
}
