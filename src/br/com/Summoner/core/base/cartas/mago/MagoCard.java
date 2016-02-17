/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.mago;

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
@CsvDataType( defaultPrefix = "Monst")
public class MagoCard extends Card{

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Eleme"))
    public List<MagoElement> Elementos;
    
    @Override
    public long CalculaBonus(Jogada minhaJogada, List<Card> listaMonstrosAdversarios) {
        List<Card> itens = minhaJogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).collect(Collectors.toList());
        long danoExtraMagia = 0;
         
        for (MagoElement magoElemento : this.Elementos )
        {
            long danoExtraPorElemento = 0;
            
            ElementType elemento = magoElemento.Elemento;
            
            danoExtraPorElemento = itens.stream().map((item) -> (MagoItemCard)item).filter((itemMago) -> ( itemMago.Elemento == elemento )).map((itemMago) -> itemMago.DanoMagia).reduce(danoExtraPorElemento, (accumulator, _item) -> accumulator + _item);
            
            if (danoExtraPorElemento > danoExtraMagia){
                danoExtraMagia = danoExtraPorElemento;
            }   
        }     
        
        return danoExtraMagia;
    }
    
    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
