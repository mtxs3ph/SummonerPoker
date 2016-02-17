/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas.invoker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dferreira
 */
public class ListToMatrix {
    
    public static List<String> Convert(List<InvokerElementType> elementos)
    {
        List<String> retorno =  new ArrayList<>();
        
        for (int i = 0; i < elementos.size(); i++ )
        {
            String combinacao = elementos.get(i).name();
            for (int j = 0; j < elementos.size(); i++)
            {
                if (j != i)
                {
                    combinacao += elementos.get(j).name();
                }
            }
            retorno.add(combinacao);
        }
        return retorno;
    }
    
}
