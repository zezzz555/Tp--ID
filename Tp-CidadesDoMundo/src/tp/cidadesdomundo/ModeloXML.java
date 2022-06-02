
package tp.cidadesdomundo;

import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.List;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmValue;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 *
 * @author maria
 */
public class ModeloXML {

    public static Document adicionaCidade(Cidades cid, Document doc) throws SaxonApiException {
        Element raiz;
        if (doc == null) {
            raiz = new Element("catalogo"); //cria <catalogo>...</catalogo>
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
            String xp = "//cidade[contains(nome,'" + cid.getCidade() + "')]";
            //System.out.println(xp);
            XdmValue res = XPathFunctions.executaXpath(xp, "cidades.xml");
            if (res != null && res.size() > 0) {
                System.out.println("Cidade já existe");
                return null;
            } else {
                Element cidade = new Element("cidade");
                String nome = cid.getCidade();
                if (nome != null) {
                    Element folha = new Element("nome").addContent(nome);
                    cidade.addContent(folha);
                }
                String pais = cid.getPais();
                if (pais != null) {
                    Element folha = new Element("pais").addContent(pais);
                    cidade.addContent(folha);
                }
                String bandeiraPais = cid.getBandeiraPais();
                if (bandeiraPais != null) {
                    Element folha = new Element("bandeirapais").addContent(bandeiraPais);
                    cidade.addContent(folha);
                }
                String bandeiraCidade = cid.getBandeiraCidade();
                if (bandeiraCidade != null) {
                    Element folha = new Element("bandeiracidade").addContent(bandeiraCidade);
                    cidade.addContent(folha);
                }
                String capital = valueOf(cid.isCapital());
                Element folha1 = new Element("capital").addContent(capital);
                cidade.addContent(folha1);
                    
                String presidente = cid.getPresidente();
                if (presidente != null) {
                    Element folha = new Element("presidente").addContent(presidente);
                    cidade.addContent(folha);
                }
                String clima = valueOf(cid.getClima());
                if (clima != null) {
                    Element folha = new Element("clima").addContent(clima);
                    cidade.addContent(folha);
                }
                String fuso = valueOf(cid.getFuso());
                if (fuso != null) {
                    Element folha = new Element("fuso").addContent(fuso);
                    cidade.addContent(folha);
                }
                String website = cid.getWebsite();
                if (website != null) {
                    Element folha = new Element("website").addContent(website);
                    cidade.addContent(folha);
                }
                String area = Double.toString(cid.getArea());
                if (!area.equals("-1.0")) {
                    Element folha = new Element("area");
                    Attribute a = new Attribute("uni", "km2");
                    folha.setAttribute(a);
                    folha.addContent(area);
                    cidade.addContent(folha);
                }
                String densidade = Double.toString(cid.getDensidade());
                if (!densidade.equals("-1.0")) {
                    Element folha = new Element("densidade");
                    Attribute a = new Attribute("uni", "hab_km2");
                    folha.setAttribute(a);
                    folha.addContent(densidade);
                    cidade.addContent(folha);
                }

                Element linguas = new Element("linguagens");
                ArrayList<String> linguagens = cid.getLinguagens();
                if (linguagens.size() != 0) {
                    for (int i = 0; i < linguagens.size(); i++) {
                        Element folha = new Element("lingua").addContent(linguagens.get(i));
                        linguas.addContent(folha);
                    }
                }
                cidade.addContent(linguas);
                    
                Element monumentos2 = new Element("monumentos");
                ArrayList<String> monumentos = cid.getMonumentos();
                if (monumentos.size() != 0) {
                        for (int i = 0; i < monumentos.size(); i++) {
                            Element folha = new Element("monumento").addContent(monumentos.get(i));
                            monumentos2.addContent(folha);
                        }
                }
                cidade.addContent(monumentos2);
                
                Element cidadesgeminadas2 = new Element("cidadesgeminadas");
                ArrayList<String> cidadesgeminadas = cid.getCidadesGeminadas();
                if (cidadesgeminadas.size() != 0) {
                        for (int i = 0; i < cidadesgeminadas.size(); i++) {
                            Element folha = new Element("cidadegeminada").addContent(cidadesgeminadas.get(i));
                            cidadesgeminadas2.addContent(folha);
                        }
                }
                cidade.addContent(cidadesgeminadas2);
                
                String latitude = Double.toString(cid.getLatitude());
                if (!latitude.equals("-1.0")) {
                    Element folha = new Element("latitude").addContent(latitude);
                    cidade.addContent(folha);
                }   
                
                String longitude = Double.toString(cid.getLongitude());
                if (!longitude.equals("-1.0")) {
                        Element folha = new Element("longitude").addContent(longitude);
                        cidade.addContent(folha);
                    }
                
                String altitude = Integer.toString(cid.getAltitude());
                if (!altitude.equals("-1")) {
                        Element folha = new Element("altitude").addContent(altitude);
                        cidade.addContent(folha);
                }
                
                String nHabitantes = Integer.toString(cid.getnHabitantes());
                if (!nHabitantes.equals("-1")) {
                        Element folha = new Element("nhabitantes").addContent(nHabitantes);
                        cidade.addContent(folha);
                }
                    
                String codigoPostal = Integer.toString(cid.getCodigoPostal());
                if (!codigoPostal.equals("-1")) {
                        Element folha = new Element("codigopostal").addContent(codigoPostal);
                        cidade.addContent(folha);
                }
                    
                raiz.addContent(cidade);
                return doc;
                    
                }
        }
        return doc;
    }
    
    public static Document removeCidade(String nome, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Não existe nenhum ficheiro");
            return null;
        } else {
            raiz = doc.getRootElement();
            boolean found = false;
            List todasCidades = raiz.getChildren("cidade");
            for (int i = 0; i < todasCidades.size(); i++) {
                Element cidade = (Element) todasCidades.get(i);
                if (cidade.getChild("nome").getText().contains(nome)) {
                    cidade.getParent().removeContent(cidade);
                    found = true;
                }
            }
            if (!found) {               
                return null;
            }
        }
        return doc;
    }
        public static Document alteraArea(String nome, double novaArea, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro nao existe - nao dá para alterar informação");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todasCidades = raiz.getChildren("cidade");
        boolean found = false;
        for (int i = 0; i < todasCidades.size(); i++) {
            Element cidade = (Element) todasCidades.get(i); 
            if (cidade.getChild("nome").getText().contains(nome)) {                
                cidade.getChild("area").setText(String.valueOf(novaArea));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Cidade " + nome + " não encontrada");
            return null;
        }
        return doc;
    }
    
    public static Document alteraCodigoPostal(String nome, int codigoPostal, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro nao existe - nao dá para alterar informação");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todasCidades = raiz.getChildren("cidade");
        boolean found = false;
        for (int i = 0; i < todasCidades.size(); i++) {
            Element cidade = (Element) todasCidades.get(i); 
            if (cidade.getChild("nome").getText().contains(nome)) {                
                cidade.getChild("codigopostal").setText(String.valueOf(codigoPostal));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Cidade " + nome + " não encontrada");
            return null;
        }
        return doc;
    }
    
    public static Document alteraClima(String nome, String clima, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro nao existe - nao dá para alterar informação");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todasCidades = raiz.getChildren("cidade");
        boolean found = false;
        for (int i = 0; i < todasCidades.size(); i++) {
            Element cidade = (Element) todasCidades.get(i); 
            if (cidade.getChild("nome").getText().contains(nome)) {                
                cidade.getChild("clima").setText(String.valueOf(clima));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Cidade " + nome + " não encontrada");
            return null;
        }
        return doc;
    }
    public static Document alteraPresidente(String nome, String presidente, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro nao existe - nao dá para alterar informação");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todasCidades = raiz.getChildren("cidade");
        boolean found = false;
        for (int i = 0; i < todasCidades.size(); i++) {
            Element cidade = (Element) todasCidades.get(i); 
            if (cidade.getChild("nome").getText().contains(nome)) {                
                cidade.getChild("presidente").setText(String.valueOf(presidente));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Cidade " + nome + " não encontrada");
            return null;
        }
        return doc;
    }
    
    public static Document alteraUniArea(String nome, String uniNova, Document doc) {
        Element raiz;
        if (doc == null) {
            System.out.println("Ficheiro nao existe - nao dá para alterar informação");
            return null;
        } else {
            raiz = doc.getRootElement();
        }
        List todasCidades = raiz.getChildren("cidade");
        boolean found = false;
        for (int i = 0; i < todasCidades.size(); i++) {
            Element cidade = (Element) todasCidades.get(i); 
            if (cidade.getChild("nome").getText().contains(nome)) {                
                cidade.getChild("area").getAttribute("uni").setValue(uniNova);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Cidade " + nome + " não encontrada");
            return null;
        }
        return doc;
    }
}
