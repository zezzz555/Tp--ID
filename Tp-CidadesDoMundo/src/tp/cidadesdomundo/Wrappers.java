/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp.cidadesdomundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author maria
 */
public class Wrappers {
        
    
       public static String obtemLinkdbCity(String cidade,String pais) throws IOException {
            //String pais = obtemPais(cidade);
            HttpRequestFunctions.httpRequest1("https://pt.db-city.com/", pais, "pais.txt");
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            String er ="href=\"/([A-Za-z-]+--[A-Za-z-]+[^\"]+)\" title=\""+cidade+"\">"+cidade+"</a>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String link = "https://pt.db-city.com/"+m.group(1);
                    System.out.println(link);
                    return m.group(1);
                }
            }
            ler.close();
            return null;
    }
    public static String obtemLinkWikipedia(String cidade) throws IOException {
            HttpRequestFunctions.httpRequest1("https://pt.wikipedia.org/wiki/", cidade, "cidade.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade.txt"));
            String er ="\"wgPageName\":\"([A-Za-z-_]+)\"";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String link = "https://pt.wikipedia.org/wiki/"+m.group(1);
                    System.out.println(link);
                    return m.group(1);
                }
            }
            ler.close();
            return null;
    }
}
