/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.util;

import br.com.Summoner.core.base.tipos.TipoCarta;
import org.jsefa.common.converter.SimpleTypeConverter;

/**
 *
 * @author dferreira
 */
public class ConverterTipoCarta implements SimpleTypeConverter {
    
    private static final ConverterTipoCarta INSTANCE = new ConverterTipoCarta();
    public static ConverterTipoCarta create() {
        return INSTANCE;
    }
    
    private ConverterTipoCarta() {
 }
 
    @Override
    public Object fromString(String str) {
        return (TipoCarta) TipoCarta.valueOf(str);
    }

    @Override
    public String toString(Object obj) {
        return ((TipoCarta) obj).name();
    }
}