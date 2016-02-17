/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Summoner.core.util;

/**
 *
 * @author dferreira
 */
public class StringUtil {

    public static String padRight(String content, int n, String s) {
        return String.format("%1$-" + n + "s", content);
//        return content;
    }

    public static String padRight(String content, int n, char s) {
     return String.format("%1$-" + n + "s", content);
//        return content;
    }

    public static String padLeft(String content, int n, char s) {
     return String.format("%1$" + n + "s", content);
//        return content;
    }

    public static String padLeft(long content, int n, char s) {
        return String.format("%" + n + "s", content);
//        return String.valueOf(content);
    }
}
