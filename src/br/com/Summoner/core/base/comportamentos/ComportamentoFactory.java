/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.comportamentos;

import br.com.Summoner.core.base.interfaces.*;
import br.com.Summoner.core.util.Random;
import br.com.Summoner.core.*;
/**
 *
 * @author dferreira
 */
 public class ComportamentoFactory
    {
        public static IComportamento ObtemComportamento() throws Exception
        {

            if (Configuracoes.ComportamentoFixo)
                return new Combinador();

            int comportamento = Random.RandomNumber(1, 5);

            switch (comportamento)
            {
//                case 1:
//                    return new MaisForte();
//                case 2:
//                    return new Aleatorio();
//                case 3:
//                    return new MaisFraca();
                case 4:
                    return new Combinador();
            }

            throw new Exception("Não obteve comportamento!");
        }
    }
