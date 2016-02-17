/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.interfaces;

import br.com.Summoner.core.base.TipoDeck;
import java.io.FileNotFoundException;


/**
 *
 * @author dferreira
 */
public interface IDeck {
    void RemontarDeck(TipoDeck tipoDeck) throws FileNotFoundException ;
}
