/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core;

import br.com.Summoner.core.base.tipos.TipoMonstro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dferreira
 */
public class Configuracoes {

    public static int NumeroDeTurnos = 10;
    public static int NumeroDeJogadores = 2;
    public static int QuantidadeItensReveladosPorTurno = 5;
    public static String CaminhoCSVMonstros = "";
    public static String CaminhoCSVItens = "";
//    public static String CaminhoCSVSetBonus = "";
    public static String CaminhoDestinoEstatisticas = "";
    public static String DelimitadorCSV = ";";
    public static Boolean ComportamentoFixo = true;
    public static List<String> BaralhosUsados = new ArrayList<>();
}
