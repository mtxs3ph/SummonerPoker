/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.mrrobot;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoCarta;
import br.com.Summoner.core.base.tipos.TipoMonstro;

import java.util.List;
import java.util.stream.Collectors;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.csv.annotation.CsvSubRecordList;
import org.jsefa.rbf.annotation.Record;

/**
 *
 * @author dferreira
 */
@CsvDataType(defaultPrefix = "ROBOT")
public class MrRobotCard extends Card{

    @CsvField(pos = 6)
    public long BonusCompleto;
    
    @CsvSubRecordList(pos = 7,records = @Record(prefix = "HAVE"))
    public List<TipoPeca> PecasNecessarias;
    
    @CsvSubRecordList(pos = 8,records = @Record(prefix = "MISS"))
    public List<TipoPeca> PecasEquipadas;
    
    
    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        long valorBonus = 0;
        
        for (Card item : jogada.CartasUtilizadas.stream().filter(x->x.TipoCarta == TipoCarta.Item).collect(Collectors.toList()))
        {
            MrRobotItemCard mrItem = (MrRobotItemCard) item;
            
            TipoPeca tipoEncontrado = this.PecasNecessarias.stream().filter(x -> x == mrItem.Peca).findFirst().orElse(null);
            
            if (tipoEncontrado != null){
                this.PecasNecessarias.remove(tipoEncontrado);
                this.PecasEquipadas.add(tipoEncontrado);
            }
        }
        
        if (this.PecasNecessarias.size() < 1)
            valorBonus = this.BonusCompleto;
        
        return valorBonus;
    }
    
    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
