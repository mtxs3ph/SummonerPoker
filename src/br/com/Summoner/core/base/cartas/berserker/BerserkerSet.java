/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.berserker;

import java.util.List;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix="Setco") 
public class BerserkerSet {
    
    @CsvSubRecordList(pos = 1, records = @Record(prefix = "Equip"))
    List<BerserkerEquipamentos> Equipamentos;
    
    @CsvField(pos = 2)
    public long BonusForca;
}
