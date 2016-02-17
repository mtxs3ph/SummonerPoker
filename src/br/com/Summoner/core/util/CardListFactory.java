///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.com.Summoner.core.util;
//
//import br.com.Summoner.core.Configuracoes;
//import br.com.Summoner.core.base.bonus.SetBonus;
//import br.com.Summoner.core.base.cartas.ItemCard;
//import br.com.Summoner.core.base.cartas.MonsterCard;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;
//import org.jsefa.Deserializer;
//import org.jsefa.csv.CsvIOFactory;
//
///**
// *
// * @author dferreira
// */
//public class CardListFactory {
//    public static List<MonsterCard> GetMonsterCardList(String pathArquivoCSV) throws FileNotFoundException {
//        List<SetBonus> listaBonus = GetSetBonusList(Configuracoes.CaminhoCSVSetBonus);
//            Deserializer deserializer = CsvIOFactory.createFactory(MonsterCard.class).createDeserializer();
//        deserializer.open(createFileReader(pathArquivoCSV));
//        List<MonsterCard> listaCartas = new ArrayList<>();
//        while (deserializer.hasNext()) {
//            MonsterCard novoItem = deserializer.next();
////            novoItem.setSetBonus(listaBonus);
//            listaCartas.add(novoItem);
//        }
//        deserializer.close(true);
//        return listaCartas; 
//   }
//    
//    public static List<ItemCard> GetItemCardList(String pathArquivoCSV) throws FileNotFoundException {
//        
//        List<SetBonus> listaBonus = GetSetBonusList(Configuracoes.CaminhoCSVSetBonus);
//            Deserializer deserializer = CsvIOFactory.createFactory(ItemCard.class).createDeserializer();
//        deserializer.open(createFileReader(pathArquivoCSV));
//        List<ItemCard> listaCartas = new ArrayList<>();
//        while (deserializer.hasNext()) {
//            ItemCard novoItem = deserializer.next();
////            novoItem.setSetBonus(listaBonus);
//            listaCartas.add(novoItem);
//        }
//        deserializer.close(true);
//        return listaCartas; 
//   }
//    
//    public static List<SetBonus> GetSetBonusList(String pathArquivoCSV) throws FileNotFoundException {
//            Deserializer deserializer = CsvIOFactory.createFactory(SetBonus.class).createDeserializer();
//        deserializer.open(createFileReader(pathArquivoCSV));
//        List<SetBonus> lista = new ArrayList<>();
//        while (deserializer.hasNext()) {
//            SetBonus novoItem = deserializer.next();
//            lista.add(novoItem);
//        }
//        deserializer.close(true);
//        return lista; 
//   }
//    
//    
//    
//    private static Reader createFileReader(String pathArquivoCSV) throws FileNotFoundException {
//        return new InputStreamReader(new FileInputStream(pathArquivoCSV));
//    }
//            
//}
