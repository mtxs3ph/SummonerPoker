/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.wizard;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;

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
public class WizardCard extends Card{

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Spell"))
    public List<WizardManaGain> ListaGanhoMana;
    
    @Override
    public long CalculaBonus(Jogada minhaJogada, List<Card> listaMonstrosAdversarios) {
        List<Card> itens = minhaJogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item).collect(Collectors.toList());
        long valorMana = 0;
        long danoExtraMagia = 0;
        int quantidadeGrimorios = itens.size();
        if (quantidadeGrimorios > 0) {
            for (int i = 0; i < this.ListaGanhoMana.size(); i++) {
                WizardManaGain recurso = this.ListaGanhoMana.get(i);
                if (quantidadeGrimorios >= recurso.MinGrimorio && quantidadeGrimorios <= recurso.MaxGrimorio) {
                    valorMana = recurso.Mana;
                    break;
                }
            }
        }
        danoExtraMagia = RetornaDanoExtraMagia(valorMana, itens);
        return danoExtraMagia;
    }

    private long RetornaDanoExtraMagia(long mana, List<Card> itens)
    {
        long danoExtraMagia = 0;
        
        for (int i = 0; i < itens.size(); i++)
        {
            WizardItemCard item = (WizardItemCard) itens.get(i);
            if (mana >= item.CustoMagia && item.DanoMagia > danoExtraMagia){
                danoExtraMagia = item.DanoMagia;
            }
        }
        
        return danoExtraMagia;
    }
    
    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
