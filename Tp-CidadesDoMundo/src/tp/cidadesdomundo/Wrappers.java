
package tp.cidadesdomundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author maria
 */
public class Wrappers {
        
    
    public static String obtemLinkdbCity(String cidade,String pais) throws IOException {
            if(pais.contains("Fran"))
                pais = "Franca";
            HttpRequestFunctions.httpRequest1("https://pt.db-city.com/", pais, "pais.txt");
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            //exemplo: href="/Portugal--Lisboa--Lisboa" title="Lisboa">Lisboa</a>
            String er ="href=\"/([A-Za-z-ç]+--[A-Za-z-Ø]+[^\"]+)\" title=\""+cidade+"\">"+cidade+"</a>";
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

    public static boolean obtemCapital(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            //exemplo: >Lisboa</a> (Capital),
            String er =">"+cidade+"<\\/a> \\(Capital\\),";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    return true;
                }
            }
            ler.close();
            return false;
    } 
    
    public static String obtemBandeiraPais(String cidade, String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <img src="//dwpt1kkww6vki.cloudfront.net/img/drapeau/120/170.png" alt="Bandeira Portugal" />
            String er ="<img src=\"([^\"]+)\" alt=\"Bandeira [^\"]+\" />";
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
        //exemplo: <img alt="Bandeira de Lisboa" src="//upload.wikimedia.org/wikipedia/commons/thumb/1/16/Bandeira_municipal_de_Lisboa.png/90px-Bandeira_municipal_de_Lisboa.png/"           
        //<img alt="Bandeira de Lyon (Lião)" src="//upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Flag_of_Lyon.png/100px-Flag_of_Lyon.png"
        String er ="<img alt=\"Bandeira de "+cidade+"[^\"]*\" src=\"([^\"]+)\"";
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
     public static String obtemPresidente(String cidade, String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Presidente da Câmara Barcelona</th><td>Ada COLAU BALLANO</td>
            String er ="<th>Presidente da Câmara "+cidade+"</th><td>([^<]+)</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();
                    return m.group(1);
                }
            }
            ler.close();
            return null;
    } 
    public static String obtemClima(String cidade, String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Clima Lisboa</th><td>Clima mediterrânico (Classificação climática de Köppen-Geiger: Csa)</td>
            String er ="<th>Clima "+cidade+"</th><td>([^<]+)</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();
                    return m.group(1);
                }
            }
            ler.close();
            return null;
    }
    public static String obtemFuso(String cidade, String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Fuso horário Lisboa</th><td><abbr title="Tempo Universal Coordenado">UTC</abbr> +0:00 (Europe/Lisbon)<br
            String er ="<th>Fuso horário "+cidade+"</th><td><abbr title=\"[^\"]+\">([^<]+)</abbr>([^<]+)<br";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String fuso= m.group(1) + m.group(2);
                    //System.out.println(fuso);
                    ler.close();
                    return fuso;
                }
            }
            ler.close();
            return null;
    }
        public static String obtemWebsite(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Sítio Web Lisboa</th><td><a class="url" href="https://www.lisboa.pt"
            String er ="<th>Sítio Web "+cidade+"</th><td><a class=\"url\" href=\"([^\"]+)\"";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();
                    return m.group(1);
                }
            }
            ler.close();
            return null;
    }
        
    public static double obtemArea(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Superfície Lisboa</th><td>8.480 hectares<br />84,80 km²</td>
            String er ="<th>Superfície "+cidade+"</th><td>[^<]+<br />([0-9]*.?[0-9]+,?[0-9]*) km²</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //elimina o . dos milhares e troca o . por ,
                    String area = m.group(1).replace(".","").replace(",",".");
                    //System.out.println(area);
                    ler.close();   
                    return Double.parseDouble(area);
                }
            }
            ler.close();
            return -1;
    }
    public static double obtemDensidade(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Densidade populacional Rio de Janeiro</th><td>5.597,8 /km²</td>
            String er ="<th>Densidade populacional "+cidade+"</th><td>([0-9]*.?[0-9]+,?[0-9]*) /km²</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //elimina o . dos milhares e troca o . por ,
                    String densidade = m.group(1).replace(".","").replace(",",".");
                    //System.out.println(densidade);
                    ler.close();   
                    return Double.parseDouble(densidade);
                }
            }
            ler.close();
            return -1;
    }
    public static double obtemLatitude(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: Latitude: <b class="latitude">38.7071</b>
            String er ="Latitude: <b class=\"latitude\">(-?[0-9]+.[0-9]+)</b>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();   
                    return Double.parseDouble(m.group(1));
                }
            }
            ler.close();
            return -1;
    }
    public static double obtemLongitude(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: Longitude: <b class="longitude">-9.13549</b>
            String er ="Longitude: <b class=\"longitude\">(-?[0-9]+.[0-9]+)</b>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();   
                    return Double.parseDouble(m.group(1));
                }
            }
            ler.close();
            return -1;
    }
    public static int obtemNHabitantes(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Número de habitantes Lisboa</th><td>507.220 habitantes</td>
            String er ="<th>Número de habitantes "+cidade+"</th><td>([0-9]*.?[0-9]+.?[0-9]*) habitantes</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    String nHabitantes = m.group(1).replace(".","");
                    //System.out.println(nHabitantes);
                    ler.close();   
                    return Integer.parseInt(nHabitantes);
                }
            }
            ler.close();
            return -1;
    }
    public static int obtemCodigoPostal(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt");
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Código postal Lisboa</th><td>1100</td>
            String er ="<th>Código postal "+cidade+"</th><td>([0-9]+)</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();   
                    return Integer.parseInt(m.group(1));
                }
            }
            ler.close();
            return -1;
    }
    public static int obtemAltitude(String cidade,String pais) throws IOException {
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt"); 
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: <th>Altitude Lisboa</th><td>4 m</td>
            String er ="<th>Altitude "+cidade+"</th><td>([0-9]+) m</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    //System.out.println(m.group(1));
                    ler.close();   
                    return Integer.parseInt(m.group(1));
                }
            }
            ler.close();
            return -1;
    }
    public static ArrayList obtemLinguagens(String cidade,String pais) throws IOException {
            ArrayList<String> linguagens = new ArrayList();
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt"); 
            Scanner ler = new Scanner(new FileInputStream("pais.txt"));
            //exemplo: <th>Língua oficial</th><td>Português</td>
            //exemplo2: <th>Língua oficial</th><td>Português<br />Mirandese</td>
            //exemplo3: <th>Língua oficial</th><td>Norueguês bokmål<br />Novo norueguês<br />Norueguês</td>
            String er ="<th>Língua oficial</th><td>([^<]+<?[^<]*<?[^<]*)</td>";
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);
                if (m.find()) {
                    if(m.group(1).contains("<br />")){
                        String linguas[] = m.group(1).split("<br />");
                        for (String l : linguas) 
                             linguagens.add(l);                     
                    }
                    else
                        linguagens.add(m.group(1));                           
                }
            }
            ler.close();
            return linguagens;
    }
    public static ArrayList obtemMonumentos(String cidade) throws IOException {
            ArrayList<String> monumentos = new ArrayList();
            String link = obtemLinkWikipedia(cidade);
            Scanner ler = new Scanner(new FileInputStream("cidade.txt"));
            int i=0;            
            if(cidade.equals("Detroit") || cidade.equals("Porto") || cidade.equals("Munique") || cidade.equals("Lyon") || cidade.equals("Oslo")){
                //<meta property="og:image" content="https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Detroit_Montage.jpg/800px-Detroit_Montage.jpg"/>
                String er ="<meta property=\"og:image\" content=\"([^\"]+)\"/>";
                Pattern p = Pattern.compile(er);               
                while (ler.hasNextLine() && i < 1) {
                    String linha = ler.nextLine();
                    Matcher m = p.matcher(linha);
                    while (m.find() && i < 1) {                                  
                            i++;
                            String scr="https:"+m.group(1);
                            monumentos.add(scr);                       
                    }                   
                }
            }
            else{                
                //exemplo: <a href="/wiki/Ficheiro:Tiananmen_Gate.jpg" class="image"><img alt="" src="//upload.wikimedia.org/wikipedia/commons/thumb/9/91/Tiananmen_Gate.jpg/189px-Tiananmen_Gate.jpg" decoding="async" width="189" height="142" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/9/91/Tiananmen_Gate.jpg/284px-Tiananmen_Gate.jpg 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/9/91/Tiananmen_Gate.jpg/378px-Tiananmen_Gate.jpg 2x" data-file-width="2048" data-file-height="1536" /></a>
                //exemplo: <a href="/wiki/Ficheiro:Collage_de_la_ciudad_de_Sevilla,_capital_de_Andaluc%C3%ADa,_Espa%C3%B1a.png" class="image"><img alt="Collage de la ciudad de Sevilla, capital de Andalucía, España.png" src="//upload.wikimedia.org/wikipedia/commons/thumb/2/27/Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png/290px-Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png" decoding="async" width="290" height="578" srcset="//upload.wikimedia.org/wikipedia/commons/thumb/2/27/Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png/435px-Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png 1.5x, //upload.wikimedia.org/wikipedia/commons/thumb/2/27/Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png/580px-Collage_de_la_ciudad_de_Sevilla%2C_capital_de_Andaluc%C3%ADa%2C_Espa%C3%B1a.png 2x" data-file-width="1003" data-file-height="2000" /></a>
                String er ="<a href=\"[^\"]+\" class=\"image\"><img alt=\"[^\"]*\" src=\"([^\"]+)\" decoding=\"[^\"]+\" ?w?i?d?t?h?=?\"?[^\"]*\"? ?h?e?i?g?h?t?=?\"?[^\"]*\"? srcset=\"[^\"]+\" data-file-width=\"[^\"]+\" data-file-height=\"[^\"]+\" w?i?d?t?h?=?\"?[^\"]*\"? ?h?e?i?g?h?t?=?\"?[^\"]*\"?/></a>";          
                Pattern p = Pattern.compile(er);               
                while (ler.hasNextLine() && i < 6) {
                    String linha = ler.nextLine();
                    Matcher m = p.matcher(linha);
                    while (m.find() && i < 6) {                         
                        if(!m.group(1).contains(".svg.png")) {               
                            i++;
                            String scr="https:"+m.group(1);
                            monumentos.add(scr);                            
                        }
                    }
                }
            }
            ler.close();
            return monumentos;       
    }     
   
    public static ArrayList obtemCidadesGeminadas(String cidade,String pais) throws IOException {
            ArrayList<String> geminadas = new ArrayList();
            String link = obtemLinkdbCity(cidade,pais);
            HttpRequestFunctions.httpRequest1(link, "", "cidade2.txt"); 
            Scanner ler = new Scanner(new FileInputStream("cidade2.txt"));
            //exemplo: class=\"img_drp\" /></a> <a href="/Brasil--Rio-de-Janeiro--Rio-de-Janeiro" title="Rio de Janeiro">Rio de Janeiro</a>
            String er="class=\"img_drp\" /></a> <a href=\"[^\"]+\" title=\"([^\"]+)\">[^<]+</a>";   
            Pattern p = Pattern.compile(er);
            while (ler.hasNextLine()) {
                String linha = ler.nextLine();
                Matcher m = p.matcher(linha);                      
                    while(m.find())                       
                        geminadas.add(m.group(1));                                         
            }
            ler.close();
            return geminadas;
    }
    
}
