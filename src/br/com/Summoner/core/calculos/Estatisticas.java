    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.calculos;

import br.com.Summoner.core.Configuracoes;
import br.com.Summoner.core.Jogada;
import br.com.Summoner.core.Partida;
import br.com.Summoner.core.Turno;
import br.com.Summoner.core.base.cartas.humano.HumanCard;
import br.com.Summoner.core.base.interfaces.Card;
import br.com.Summoner.core.base.tipos.TipoCarta;
import br.com.Summoner.core.base.tipos.TipoMonstro;
import br.com.Summoner.core.util.FileIO;
import br.com.Summoner.core.util.StringUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author dferreira
 */
public class Estatisticas {

    public static void GeraRelatorioPartidaAnalitico(Partida partida) throws IOException, FileNotFoundException {
        StringBuilder strbRelat = new StringBuilder();
        final String espacamento1 = "\t";
        final String espacamento2 = "\t\t";
        final String espacamento3 = "\t\t\t\t";
        final String espacamento4 = "\t\t\t\t\t\t";

        strbRelat.append(String.format("Dados Estatísticos - %1$s", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()))).append("\r\n");

        strbRelat.append(String.format("Número de Jogadores: %1$s", partida.Jogadores.size())).append("\r\n");
        strbRelat.append(String.format("Número de Turnos: %1$s", partida.Turnos.size())).append("\r\n");

        strbRelat.append("\r\n");
        
//        Comparator<Card> byForca = (e1, e2) -> Integer.compare(
//                e1.ForcaBase, e2.ForcaBase);
        Comparator<Long> byForcaTotal = (e1, e2) -> Long.compare(
                e1, e2);

        Comparator<Card> byNome = (e1, e2) -> String.valueOf(e1.Nome).compareTo(e2.Nome);
        Comparator<Jogada> byNomeMonstroJogada = (e1, e2) -> String.valueOf(e1.Jogador.MonstroNaMao().Nome).compareTo(e2.Jogador.MonstroNaMao().Nome);
        Comparator<Jogada> byLevelMonstroJogada = (e1, e2) -> String.valueOf(e1.Jogador.MonstroNaMao().Level).compareTo(String.valueOf(e2.Jogador.MonstroNaMao().Level));
        Comparator<Object> byString = (e1, e2) -> String.valueOf(e1.toString()).compareTo(e2.toString());
//        Comparator<Object> byInt = (e1, e2) -> String.valueOf(e1).compareTo(String.valueOf(e2));

        List<Jogada> jogadasVencedoras = partida.Turnos.stream().flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream()).collect(Collectors.toList());
        List<Jogada> jogadasVencedorasLimpas = partida.Turnos.stream().filter(t -> t.JogadaVencedoraOuEmpate.size() <= 1).flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream()).collect(Collectors.toList());
        List<Jogada> jogadasVencedorasEmpates = partida.Turnos.stream().filter(t -> t.JogadaVencedoraOuEmpate.size() > 1).flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream()).collect(Collectors.toList());
        List<String> listaMonstrosVencedoresName = partida.Turnos.stream().flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream().map(jogada -> jogada.Jogador.MonstroNaMao().Nome)).sorted(byString).collect(Collectors.toList());
        List<Card> listaMonstrosVencedores = partida.Turnos.stream().flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream().map(jogada -> jogada.Jogador.MonstroNaMao())).sorted(byNome).collect(Collectors.toList());
        List<String> listaMonstrosRevelados = partida.Turnos.stream().flatMap(turno -> turno.Jogadas.stream().map(jogada -> jogada.Jogador.MonstroNaMao().Nome)).sorted(byString).collect(Collectors.toList());
        List<Card> listaMonstrosReveladosCard = partida.Turnos.stream().flatMap(turno -> turno.Jogadas.stream().map(jogada -> jogada.Jogador.MonstroNaMao())).collect(Collectors.toList());
//        List<String> listaMonstrosVencedoresLevel = partida.Turnos.stream().flatMap(turno -> turno.JogadaVencedoraOuEmpate.stream().map(jogada -> "LEVEL-" + String.valueOf(jogada.Jogador.MonstroNaMao().Level))).sorted(byString).collect(Collectors.toList());
//        List<String> listaMonstrosReveladosLevel = partida.Turnos.stream().flatMap(turno -> turno.Jogadas.stream().map(jogada -> "LEVEL-" + String.valueOf(jogada.Jogador.MonstroNaMao().Level))).sorted(byString).collect(Collectors.toList());
        

        int contadorTurnos = 0;

        for (Turno turno : partida.Turnos) {
            contadorTurnos++;
            strbRelat.append(String.format("%1$sTurno %2$s", espacamento1, contadorTurnos)).append("\r\n");
            strbRelat.append(String.format("%1$sMesa", espacamento2)).append("\r\n");
            turno.MesaNoTurno.ItensRevelados.forEach(itemrevelado
                    -> {
                strbRelat.append(String.format("%1$sItem: %2$s\t|\t%3$s\t|\t%4$s\t", espacamento3, StringUtil.padRight(itemrevelado.Nome, 18, ' '), itemrevelado.TipoMonstro.toString(), itemrevelado.ForcaBase)).append("\r\n");
            });
            strbRelat.append(String.format("%1$sJogadas", espacamento2)).append("\r\n");
            turno.Jogadas.forEach(jogada
                    -> {
                strbRelat.append(String.format("%1$sOrdem: %5$s - Player: %2$s ----- Criatura: %3$s ----- ForçaTotal: %4$s", espacamento3, jogada.Jogador.Nome, jogada.Jogador.MonstroNaMao().TipoMonstro.toString(), jogada.ForcaTotal, jogada.Jogador.OrdemMesa)).append("\r\n");

                jogada.CartasUtilizadas.forEach(itensUsadosDaMesa
                        -> {
                    strbRelat.append(String.format("%1$sCarta :%5$s\t|%2$s\t|\t%3$s\t|\t%4$s\t", espacamento4, StringUtil.padRight(itensUsadosDaMesa.Nome, 18, ' '), itensUsadosDaMesa.TipoMonstro.toString(), itensUsadosDaMesa.ForcaBase, itensUsadosDaMesa.TipoCarta.toString())).append("\r\n");
                });
            });
            strbRelat.append(String.format("%1$sVencedor(es)", espacamento2)).append("\r\n");
            turno.JogadaVencedoraOuEmpate.forEach(jogada
                    -> {
                strbRelat.append(String.format("%1$sPlayer: %2$s ----- Criatura: %3$s ----- ForçaTotal: %4$s", espacamento3, jogada.Jogador.Nome, jogada.Jogador.MonstroNaMao().TipoMonstro.toString(), jogada.ForcaTotal)).append("\r\n");

            });
        }

//
//            //partida.Turnos.ElementAt(14).
//      long vitoriasHumanos = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Humano))).count();
//        long empatesHumanos = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.size() > 1 && turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Humano))).count();
//        long vitoriasHumanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Humano)).count();
//        long empatesHumanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() > 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Humano)).count();
//
//        long vitoriasFeras = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Fera)).count();
//        long empatesFeras = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() > 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Fera)).count();
//
//        long vitoriasWizards = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Wizard)).count();
//        long empatesWizards = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() > 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Wizard)).count();
//
//        long vitoriasProfanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Profano)).count();
//        long empatesProfanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() > 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Profano)).count();
//
//         long vitoriasMagos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Mago)).count();
//        long empatesMagos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() > 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Mago)).count();

//
//        long vitoriasWizards = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Wizard))).count();
//        long empatesWizards = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.size() > 1 && turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Wizard))).count();
//
//        long vitoriasProfanos = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Profano))).count();
//        long empatesProfanos = partida.Turnos.stream().filter(turno -> turno.JogadaVencedoraOuEmpate.size() > 1 && turno.JogadaVencedoraOuEmpate.stream().allMatch(jogada -> jogada.Jogador.Mao.stream().allMatch(carta -> carta.TipoCarta == TipoCarta.Criatura && carta.TipoMonstro == TipoMonstro.Profano))).count();
//
//        strbRelat.append("\r\n");
//        strbRelat.append("\r\n");
//        strbRelat.append("\r\n");
//
//        strbRelat.append(String.format("Vitórias de Humanos:  %1$s Empates: (%2$s)", vitoriasHumanos, empatesHumanos)).append("\r\n");
//        strbRelat.append(String.format("Vitórias de Feras:    %1$s Empates: (%2$s)", vitoriasFeras, empatesFeras)).append("\r\n");
//        strbRelat.append(String.format("Vitórias de Wizards:    %1$s Empates: (%2$s)", vitoriasWizards, empatesWizards)).append("\r\n");
//        strbRelat.append(String.format("Vitórias de Profanos: %1$s Empates: (%2$s)", vitoriasProfanos, empatesProfanos)).append("\r\n");
//        strbRelat.append(String.format("Vitórias de Magos: %1$s Empates: (%2$s)", vitoriasMagos, empatesMagos)).append("\r\n");
//
//        strbRelat.append("\r\n");
//        strbRelat.append("\r\n");
//        strbRelat.append("\r\n");
////
//        long vitoriasLimpasHumanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() == 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Humano)).count();
//        long vitoriasLimpasFeras = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() == 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Fera)).count();
//        long vitoriasLimpasWizards = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() == 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Wizard)).count();
//        long vitoriasLimpasProfanos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() == 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Profano)).count();
//        long vitoriasLimpasMagos = partida.Turnos.stream().filter(f -> f.JogadaVencedoraOuEmpate.size() == 1 && f.JogadaVencedoraOuEmpate.stream().anyMatch(j -> j.Jogador.MonstroNaMao().TipoMonstro == TipoMonstro.Mago)).count();
////
////   
        long forcaMin = 0;
        double forcaAvg = 0;
        long forcaMax = 0;
        long count = 0;

        Map<String, Map<String, List<Jogada>>> winsPorCriaturaLevel;
        winsPorCriaturaLevel = jogadasVencedoras.stream()
                .sorted(byLevelMonstroJogada)
                .collect(Collectors.groupingBy(x -> x.Jogador.MonstroNaMao().TipoMonstro.toString(), Collectors.groupingBy(y -> "LEVEL-" + y.Jogador.MonstroNaMao().Level)));
     

        Map<TipoMonstro, List<Jogada>> winsPorTipoCriatura;
        winsPorTipoCriatura = jogadasVencedoras.stream()
                .sorted(byLevelMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> (classifier.Jogador.MonstroNaMao().TipoMonstro)));
        
        Map<TipoMonstro, List<Jogada>> winsLimpasPorTipoCriatura;
        winsLimpasPorTipoCriatura = jogadasVencedorasLimpas.stream()
                .sorted(byLevelMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> (classifier.Jogador.MonstroNaMao().TipoMonstro)));
        
        
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        
        for (Map.Entry<TipoMonstro, List<Jogada>> entry : winsPorTipoCriatura.entrySet()) {
            strbRelat.append(String.format("Vitórias de %1$s : %2$s  Empates: (%3$s)", entry.getKey().toString(), entry.getValue().size(), jogadasVencedorasEmpates.stream().filter(j -> j.Jogador.MonstroNaMao().TipoMonstro == entry.getKey()).count()));
            strbRelat.append("\r\n");
        }

        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        

        for (Map.Entry<TipoMonstro, List<Jogada>> entry : winsLimpasPorTipoCriatura.entrySet()) {
            strbRelat.append(String.format("Vitórias limpas de %1$s: %2$s", entry.getKey().toString(), entry.getValue().size()));
            strbRelat.append("\r\n");
        }

        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
      
        for (Map.Entry<String, Map<String, List<Jogada>>> entryGroup : winsPorCriaturaLevel.entrySet()) {
            strbRelat.append("Monstro-").append(entryGroup.getKey()).append("\r\n");
            for (Map.Entry<String, List<Jogada>> entry : entryGroup.getValue().entrySet()) {
                
//            strbRelat.append(String.format("Força das Vitórias: %1$s\t  Min:\t%2$s\t  Avg:\t%3$.2f\t  Max:\t%4$s", StringUtil.padRight(entry.getKey(), 15, ' '), forcaMin, forcaAvg, forcaMax)).append("\r\n");
                strbRelat.append(String.format("Level Vencedor:\t%1$s \t revelado:\t%2$s \t vitórias:\t%3$s \t rev-vito:\t%4$s\t(\t%5$.2f%%\t)",
                        StringUtil.padRight(entry.getKey(), 20, ' '),
                        listaMonstrosReveladosCard.stream().filter(monstroCount -> ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count(),
                        listaMonstrosVencedores.stream().filter(monstroCount -> ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count(),
                        StringUtil.padLeft(
                                (listaMonstrosReveladosCard.stream().filter(monstroCount -> monstroCount.TipoMonstro.toString().equals(entryGroup.getKey()) && ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count()
                                - listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.TipoMonstro.toString().equals(entryGroup.getKey()) && ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count()), 5, '0'),
                        ((double) listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.TipoMonstro.toString().equals(entryGroup.getKey()) && ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count() * 100)
                        / (double) listaMonstrosReveladosCard.stream().filter(monstroCount -> monstroCount.TipoMonstro.toString().equals(entryGroup.getKey()) && ("LEVEL-" + monstroCount.Level).equals(entry.getKey())).count()
                )).append("\r\n");
            }
            strbRelat.append("\r\n");
        }

//        listaMonstrosVencedores.stream().distinct().forEach(monstro
//                -> {
////            try {
//            strbRelat.append(String.format("Level Vencedor:\t%1$s \t revelado:\t%2$s \t vitórias:\t%3$s \t rev-vito:\t%4$s\t(\t%5$.2f%%\t)",
//                    StringUtil.padRight("LEVEL-"+monstro.Level, 20, ' '),
//                    listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
//                    listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
//                    StringUtil.padLeft(
//                            (listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count()
//                            - listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count()), 5, '0'),
//                    ((double) listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count() * 100)
//                    / (double) listaMonstrosVencedores.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
//                    listaMonstrosVencedores.stream().filter(mons -> monstro.equals("LEVEL-" + String.valueOf(mons.Level))).findFirst().get().TipoMonstro.toString()
//            )).append("\r\n");
////            } catch (FileNotFoundException ex) {
////                Logger.getLogger(Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
////            }
//        });
//
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");

        listaMonstrosVencedoresName.stream().distinct().forEach(monstro
                -> {
//            try {
            strbRelat.append(String.format("Criatura Vencedora:\t%6$s    \t-\t%1$s \t revelado:\t%2$s \t vitórias:\t%3$s \t rev-vito:\t%4$s\t(\t%5$.2f%%\t)",
                    StringUtil.padRight(monstro, 20, ' '),
                    listaMonstrosRevelados.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
                    listaMonstrosVencedoresName.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
                    StringUtil.padLeft(
                            (listaMonstrosRevelados.stream().filter(monstroCount -> monstroCount.equals(monstro)).count()
                            - listaMonstrosVencedoresName.stream().filter(monstroCount -> monstroCount.equals(monstro)).count()), 5, '0'),
                    ((double) listaMonstrosVencedoresName.stream().filter(monstroCount -> monstroCount.equals(monstro)).count() * 100)
                    / (double) listaMonstrosRevelados.stream().filter(monstroCount -> monstroCount.equals(monstro)).count(),
                    listaMonstrosVencedores.stream().filter(mons -> mons.Nome.equals(monstro)).findFirst().get().TipoMonstro.toString()
            )).append("\r\n");
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });

//
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");

//
//
        Map<String, List<Jogada>> jogadasPorLevel;
        jogadasPorLevel = jogadasVencedoras.stream()
                .sorted(byNomeMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> ("LEVEL-" + classifier.Jogador.MonstroNaMao().Level)));

        for (Map.Entry<String, List<Jogada>> entry : jogadasPorLevel.entrySet()) {
            List<Long> forcasPorJogada = entry.getValue().stream().map(forca -> forca.ForcaTotal).collect(Collectors.toList());

            forcaMin = forcasPorJogada.stream().min(byForcaTotal).get();
            forcaAvg = forcasPorJogada.stream().mapToLong(f -> f).average().getAsDouble();
            forcaMax = forcasPorJogada.stream().max(byForcaTotal).get();

            strbRelat.append(String.format("Força das Vitórias: %1$s\t  Min:\t%2$s\t  Avg:\t%3$.2f\t  Max:\t%4$s", StringUtil.padRight(entry.getKey(), 15, ' '), forcaMin, forcaAvg, forcaMax)).append("\r\n");
        }

        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");

        Map<String, List<Jogada>> jogadasPorMonstro;
        jogadasPorMonstro = jogadasVencedoras.stream()
                .sorted(byNomeMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> classifier.Jogador.MonstroNaMao().Nome));

        for (Map.Entry<String, List<Jogada>> entry : jogadasPorMonstro.entrySet()) {
            List<Long> forcasPorJogada = entry.getValue().stream().map(forca -> forca.ForcaTotal).collect(Collectors.toList());

            forcaMin = forcasPorJogada.stream().min(byForcaTotal).get();
            forcaAvg = forcasPorJogada.stream().mapToLong(f -> f).average().getAsDouble();
            forcaMax = forcasPorJogada.stream().max(byForcaTotal).get();

            strbRelat.append(String.format("Força das Vitórias: %1$s\t  Min:\t%2$s\t  Avg:\t%3$.2f\t  Max:\t%4$s", StringUtil.padRight(entry.getKey(), 15, ' '), forcaMin, forcaAvg, forcaMax)).append("\r\n");
        }

        strbRelat.append("\r\n");
        strbRelat.append("\r\n");
        strbRelat.append("\r\n");

        Map<TipoMonstro, List<Jogada>> jogadasPorTipoMonstro;
        jogadasPorTipoMonstro = jogadasVencedoras.stream()
                .sorted(byNomeMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> classifier.Jogador.MonstroNaMao().TipoMonstro));

        for (Map.Entry<TipoMonstro, List<Jogada>> entry : jogadasPorTipoMonstro.entrySet()) {
            List<Long> forcasPorJogada = entry.getValue().stream().map(forca -> forca.ForcaTotal).collect(Collectors.toList());

            forcaMin = forcasPorJogada.stream().min(byForcaTotal).get();
            forcaAvg = forcasPorJogada.stream().mapToLong(f -> f).average().getAsDouble();
            forcaMax = forcasPorJogada.stream().max(byForcaTotal).get();

            strbRelat.append(String.format("Força das Vitórias: %1$s\t  Min:\t%2$s\t  Avg:\t%3$.2f\t  Max:\t%4$s", StringUtil.padRight(entry.getKey().name(), 15, ' '), forcaMin, forcaAvg, forcaMax)).append("\r\n");

        }

        strbRelat.append("\r\n");

        Map<TipoMonstro, Map<Long, List<Jogada>>> jogadasPorTipoItensUsados;
        jogadasPorTipoItensUsados = jogadasVencedoras.stream()
                .sorted(byNomeMonstroJogada)
                .collect(Collectors.groupingBy(classifier -> classifier.Jogador.MonstroNaMao().TipoMonstro, Collectors.groupingBy(x -> x.CartasUtilizadas.stream().filter(y -> y.TipoCarta == TipoCarta.Item).count())));

        for (Map.Entry<TipoMonstro, Map<Long, List<Jogada>>> entryGroup : jogadasPorTipoItensUsados.entrySet()) {

            for (Map.Entry<Long, List<Jogada>> entry : entryGroup.getValue().entrySet()) {

                List<Long> forcasPorJogada = entry.getValue().stream().map(forca -> forca.ForcaTotal).collect(Collectors.toList());

                forcaMin = forcasPorJogada.stream().min(byForcaTotal).get();
                forcaAvg = forcasPorJogada.stream().mapToLong(f -> f).average().getAsDouble();
                forcaMax = forcasPorJogada.stream().max(byForcaTotal).get();
                count = forcasPorJogada.stream().count();

                strbRelat.append(String.format("Força das Vitórias: %1$s\t QntItensUsados:%5$s\t Min:\t%2$s\t  Avg:\t%3$.2f\t  Max:\t%4$s Count:%6$s", StringUtil.padRight(entryGroup.getKey().name(), 15, ' '), forcaMin, forcaAvg, forcaMax, entry.getKey(), count)).append("\r\n");

            }
            strbRelat.append("\r\n");
            
        }

        HumanCard.GeraEstatisca(strbRelat);
        
        FileIO.WriteStingToFile(strbRelat.toString(), Configuracoes.CaminhoDestinoEstatisticas + String.format("RelatorioAnalitico_%1$s.txt", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
    }
}
