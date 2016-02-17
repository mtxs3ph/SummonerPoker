/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.base.cartas;


import br.com.Summoner.core.base.interfaces.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.jsefa.Deserializer;
import org.jsefa.csv.CsvIOFactory;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author dferreira
 */
public abstract class CardLoader {

    protected static Reader createFileReader(String pathArquivoCSV) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(pathArquivoCSV));
    }

    public static List<Card> GetCardList(Class<?> tipo, String pathArquivoCSV) throws FileNotFoundException {

        List<Card> listaCartas = new ArrayList<>();
        Deserializer deserializer = null;
        try {

//        Type myTType = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        T myTClass = (T) Class.forName(myTType.toString()).newInstance();
            deserializer = CsvIOFactory.createFactory(Class.forName(tipo.getCanonicalName())).createDeserializer();
            deserializer.open(createFileReader(pathArquivoCSV + "/" + tipo.getSimpleName() + ".csv"));
        } catch (ClassNotFoundException ex) {
            LogManager.getLogger(CardLoader.class).log(org.apache.logging.log4j.Level.FATAL, ex);
        }
        if (deserializer != null)
        {
            while (deserializer.hasNext()) {
                Card novoItem = deserializer.next();
                listaCartas.add(novoItem);
            }
            deserializer.close(true);
        }
        return listaCartas;
    }

}
