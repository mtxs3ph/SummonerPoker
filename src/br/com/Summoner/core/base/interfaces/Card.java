/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.interfaces;

import br.com.Summoner.core.Jogada;
import java.util.List;

import br.com.Summoner.core.base.tipos.*;
import java.util.stream.Stream;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author dferreira
 */
@CsvDataType()
public abstract class Card {
        
        @CsvField(pos = 1)
        public String Nome;
        @CsvField(pos = 2)
        public TipoCarta TipoCarta;
        @CsvField(pos = 3)
        public TipoMonstro TipoMonstro;
        @CsvField(pos = 4)
        public int ForcaBase;
        
        @CsvField(pos = 5)
        public int Level;

        public abstract long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogadasDosAdversarios);
        public abstract long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios);
}
