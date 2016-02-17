/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.fera;

import br.com.Summoner.core.base.tipos.TipoMonstro;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix="Contr") 
public class FeraBonusContra {
    
    @CsvField(pos = 1)
    public TipoMonstro BonusContra;
    
    @CsvField(pos = 2)
    public long BonusContraForca;
}
