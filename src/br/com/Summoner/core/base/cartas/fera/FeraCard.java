/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.fera;

import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.base.interfaces.Card;
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
@CsvDataType(defaultPrefix = "Monst")
public class FeraCard extends Card {

    @CsvSubRecordList(pos = 6, records = @Record(prefix = "Contr"))
    List<FeraBonusContra> BonusContra;

    @Override
    public long CalculaBonus(Jogada jogada, List<Card> listaMonstrosAdversarios) {
        long bonusAcumulado = 0;
        List<Card> cartasUtilizadas = jogada.CartasUtilizadas.stream().filter(carta -> carta.TipoCarta == TipoCarta.Item && carta.TipoMonstro == TipoMonstro.Fera).collect(Collectors.toList());

        for (FeraBonusContra bonusContra : this.BonusContra) {
            for (Card item : cartasUtilizadas) {
                FeraItemCard itemFera = (FeraItemCard) item;
                if (itemFera.BonusContra == bonusContra.BonusContra) {
                    long contadorJogadasBonus = listaMonstrosAdversarios.stream().filter(monstro -> monstro.TipoMonstro == itemFera.BonusContra).map(monstro -> monstro.TipoMonstro).distinct().count();
                    if (contadorJogadasBonus > 0) {
                        bonusAcumulado += bonusContra.BonusContraForca;
                        break;
                    }
                }
            }
        }

        return bonusAcumulado;
    }

    @Override
    public long CalculaBonusPosJogadas(Jogada minhaJogada, List<Jogada> jogdasDosAdversarios) {
        return 0;
    }
}
