/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.interfaces;

import java.util.List;

/**
 *
 * @author dferreira
 */
public interface IComportamento {
    void DescartarMonstrosExtras(List<Card> mao);
    void DescartarItensExtras(List<Card> mao);
}

