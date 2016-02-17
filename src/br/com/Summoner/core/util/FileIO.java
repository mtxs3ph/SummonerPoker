/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author dferreira
 */
public class FileIO {

    public static void WriteStingToFile(String streamData, String fullPath) throws IOException {

        File file = new File(fullPath);
        String content = streamData;

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            throw  e;
        }
    }
}
