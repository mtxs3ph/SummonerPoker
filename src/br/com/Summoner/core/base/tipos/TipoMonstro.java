/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.tipos;

import org.jsefa.common.converter.EnumConstant;

/**
 *
 * @author dferreira
 */
public enum TipoMonstro {
    @EnumConstant("Humano")
        Humano,
    @EnumConstant("Fera")
        Fera,
    @EnumConstant("Wizard")
        Wizard,
    @EnumConstant("Profano")
        Profano,
    @EnumConstant("Mago")
        Mago,
    @EnumConstant("Berserker")
        Berserker,
    @EnumConstant(" ")
        Nenhum
}
