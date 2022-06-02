
package tp.cidadesdomundo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jdom2.Document;

/**
 *
 * @author maria
 */
public class TpCidadesDoMundo {


    public static void main(String[] args) throws IOException {
        new Interface().setVisible(true);
        //------WRAPPERS------
        /*
        Scanner myObj = new Scanner(System.in);
        String pesquisa = null;
        do{
            System.out.println("Introduza no formato: cidade, pais");
            pesquisa = myObj.nextLine(); 
            String[] output = pesquisa.split(", ");
            String cidade = output[0];
            String pais = output[1];
            System.out.println(cidade+", "+pais);

            String devolveLinkWiki = Wrappers.obtemLinkWikipedia(cidade);
            System.out.println("Link wikipedia: "+devolveLinkWiki);
            String devolveLinkdbCity = Wrappers.obtemLinkdbCity(cidade, pais);
            System.out.println("Link dbcity: "+devolveLinkdbCity);
            boolean capital = Wrappers.obtemCapital(cidade,pais);
            System.out.println("Capital: "+capital);
            String bandeiraPais = Wrappers.obtemBandeiraPais(cidade,pais);
            System.out.println("Bandeira pais: "+bandeiraPais);
            String bandeiraCidade = Wrappers.obtemBandeiraCidade(cidade);
            System.out.println("Bandeira cidade: "+bandeiraCidade);
            String presidente = Wrappers.obtemPresidente(cidade,pais);
            System.out.println("Presidente: "+presidente);
            String clima = Wrappers.obtemClima(cidade,pais);
            System.out.println("Clima: "+clima);
            String fuso = Wrappers.obtemFuso(cidade,pais);
            System.out.println("Fuso: "+fuso);
            String website = Wrappers.obtemWebsite(cidade,pais);
            System.out.println("Website: "+website);
            double area = Wrappers.obtemArea(cidade,pais);
            System.out.println("Area: "+area);      
            double densidade = Wrappers.obtemDensidade(cidade,pais);
            System.out.println("Densidade: "+densidade); 
            double latitude = Wrappers.obtemLatitude(cidade,pais);
            System.out.println("Latitude: "+latitude);      
            double longitude = Wrappers.obtemLongitude(cidade,pais);
            System.out.println("Longitude: "+longitude);
            int nHabitantes = Wrappers.obtemNHabitantes(cidade,pais);
            System.out.println("nHabitantes: "+nHabitantes); 
            int codigoPostal = Wrappers.obtemCodigoPostal(cidade,pais);
            System.out.println("codigoPostal: "+codigoPostal);     
            ArrayList<String> linguagens = new ArrayList();
            int altitude = Wrappers.obtemAltitude(cidade,pais);
            System.out.println("Altitude: "+altitude); 
            linguagens = Wrappers.obtemLinguagens(cidade,pais);
            System.out.print("Linguagens oficiais: ");
            for (String l : linguagens) 
                System.out.print(l+" ; "); 
            System.out.println("");
            ArrayList<String> monumentos = new ArrayList();
            monumentos = Wrappers.obtemMonumentos(cidade);
            System.out.print("Link img Monumentos: ");
            for (String m : monumentos) 
                System.out.print(m +" ; ");
            System.out.println("");
            ArrayList<String> geminadas = new ArrayList();
            geminadas = Wrappers.obtemCidadesGeminadas(cidade,pais);
            System.out.print("Cidades geminadas: ");
            for (String g : geminadas) 
                System.out.print(g+" ; ");
            System.out.println("");        
        }while(true);
        */

        /*
        //------OBJETO------    
        Cidades x=Cidades.criaCidade("Lisboa","Portugal");
        System.out.println("Cidade: "+x.getCidade());
        System.out.println("Pais: "+x.getPais());
        System.out.println("Bandeira pais: "+x.getBandeiraPais());
        System.out.println("Bandeira cidade: "+x.getBandeiraCidade()); 
        System.out.println("É Capital? "+x.isCapital());
        System.out.println("Presidente: "+x.getPresidente());
        System.out.println("Clima: "+x.getClima());
        System.out.println("Fuso: "+x.getFuso()); 
        System.out.println("Website: "+x.getWebsite());
        System.out.println("Area: "+x.getArea()+" km^2"); 
        System.out.println("Densidade populacional: "+x.getDensidade()+" hab_hm^2");
        System.out.println("Latitude: "+x.getLatitude());
        System.out.println("Longitude: "+x.getLongitude()); 
        System.out.println("Numero habitantes: "+x.getnHabitantes());
        System.out.println("Codigo postal: "+x.getCodigoPostal()); 
        System.out.println("Altitude: "+x.getAltitude());
        System.out.println("Linguagens oficiais: "+x.getLinguagens());
        System.out.println("Lista de img de Monumentos: "+x.getMonumentos()); 
        System.out.println("Cidades geminadas: "+x.getCidadesGeminadas()); 
        */
        //------XML-------
        //Inicializa Doc XML
        //Document doc = XMLJDomFunctions.lerDocumentoXML("cidades.xml");
        //Chama a função para adicionar o cidade ao XML
        //doc = ModeloXML.adicionaCidade(x, doc);
        //doc = ModeloXML.removeCidade("Porto", doc);
        //doc = ModeloXML.alteraCidade(" "," ", doc);
        
        //grava o ficheiro XML em disco

//        if(doc!=null){
//            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "cidades.xml");
//        }
    }   
   
}

