/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tester;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author maria
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        //String pais = procura_pais("Madrid");
        //System.out.println(pais);
        //String devolveLink = obtemLinkdbCity("Portugal","Almada");
        //obtemLinkWikipedia("Rio de Janeiro");
        
        Scanner myObj = new Scanner(System.in);
        String pesquisa = null;
        do{
            System.out.println("Introduza no formato: cidade, pais");
            pesquisa = myObj.nextLine(); 
            String[] output = pesquisa.split(", ");
            String cidade = output[0];
            String pais = output[1];
            //String capital = obtemCapital(cidade,pais);
            //System.out.println(capital);
            //String bandeiraPais = obtemBandeiraPais(cidade,pais);
            //System.out.println(bandeiraPais);
            String bandeiraCidade = obtemBandeiraCidade(cidade);
            System.out.println(bandeiraCidade);
        }while(true);


    }
        static String procura_pais(String nome) throws FileNotFoundException{
        Scanner ler = new Scanner(new FileInputStream("ficheiro_cidades.txt"));
        String er = nome+",\\s([A-Za-z\\s]+)";
        Pattern p = Pattern.compile(er);
        while (ler.hasNextLine()) {
            String linha = ler.nextLine();
            Matcher m = p.matcher(linha);
            if (m.find()) {
                ler.close();
                return m.group(1);
            }
        }
        ler.close();
        return null;
    }
       public static String obtemLinkdbCity(String cidade,String pais) throws IOException {
            HttpRequestFunctions.httpRequest1("https://pt.db-city.com/", pais, "pais.txt");
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            //exemplo: href="/Portugal--Lisboa--Lisboa" title="Lisboa">Lisboa</a>
            String er ="href=\"/([A-Za-z-]+--[A-Za-z-]+[^\"]+)\" title=\""+cidade+"\">"+cidade+"</a>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //concatena o link do db-city com o nome da cidade no formato do link
                    String link = "https://pt.db-city.com/"+m.group(1);
                    //System.out.println(link);
                    ler.close();
                    return link;
                }
            }
            ler.close();
            return null;
    }
        public static String obtemLinkWikipedia(String cidade) throws IOException {
            HttpRequestFunctions.httpRequest1("https://pt.wikipedia.org/wiki/", cidade, "cidade.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade.txt"));
            //exemplo: "wgPageName":"Rio_de_Janeiro"
            String er ="\"wgPageName\":\"([A-Za-z-_]+)\"";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //concatena o link da wikipedia com o nome da cidade no formato do link
                    String link = "https://pt.wikipedia.org/wiki/"+m.group(1);
                    //System.out.println(link);
                    ler.close();
                    return link;
                }
            }
            ler.close();
            return null;
    } 
    
    public static String obtemCapital(String cidade,String pais) throws IOException {
            //String link = obtemLinkdbCity(cidade,pais);
            //System.out.println(link);
            //HttpRequestFunctions.httpRequest2(link, "", "cidade2.txt");
            String link = obtemLinkdbCity(cidade,pais);
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            //exemplo: href="/Portugal--Lisboa--Lisboa" title="Lisboa">Lisboa</a> (Capital),
            String er =">"+cidade+"<\\/a>\\s\\(Capital\\),";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    return "true";
                }
            }
            ler.close();
            return "false";
    } 
        public static String obtemBandeiraPais(String cidade, String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest2(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <img src="//dwpt1kkww6vki.cloudfront.net/img/drapeau/120/170.png" alt="Bandeira Portugal" />
            String er ="<img src=\"([^\"]+)\" alt=\"Bandeira "+pais+"\" />";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String linkBandeira = "https:"+m.group(1);
                    //System.out.println(linkBandeira);
                    ler.close();
                    return linkBandeira;
                }
            }
            ler.close();
            return null;
    } 
        
    public static String obtemBandeiraCidade(String cidade) throws IOException {
            String link = obtemLinkWikipedia(cidade);
            Scanner ler = new Scanner(new FileInputStream("cidade.txt"));
            //exemplo: <img alt="Bandeira de Lisboa" src="//upload.wikimedia.org/wikipedia/commons/thumb/1/16/Bandeira_municipal_de_Lisboa.png/90px-Bandeira_municipal_de_Lisboa.png/           
            String er ="<img alt=\"Bandeira de "+cidade+"\" src=\"([^\"]+)\"";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String linkBandeira = "https:"+m.group(1);
                    //System.out.println(linkBandeira);
                    ler.close();
                    return linkBandeira;
                }
            }
            ler.close();
            return null;
    } 
        
        
}
