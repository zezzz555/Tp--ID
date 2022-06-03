
package tp.cidadesdomundo;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Cidades {

    String cidade, pais,bandeiraPais, bandeiraCidade, presidente, clima, fuso, website;
    boolean capital;
    double area, densidade, latitude, longitude;
    int nHabitantes, codigoPostal, altitude;
    ArrayList<String> linguagens=new ArrayList();
    ArrayList<String> monumentos=new ArrayList();
    ArrayList<String> cidadesGeminadas=new ArrayList();
    
    public Cidades(String cidade, String pais, String bandeiraPais, String bandeiraCidade, String presidente, String clima, String fuso, String website, boolean capital, double area, double densidade, double latitude, double longitude, int nHabitantes, int codigoPostal, int altitude,ArrayList linguagens, ArrayList monumentos, ArrayList cidadesGeminadas) {
        this.cidade = cidade;
        this.pais = pais;
        this.bandeiraPais = bandeiraPais;
        this.bandeiraCidade = bandeiraCidade;
        this.presidente = presidente;
        this.clima = clima;
        this.fuso = fuso;
        this.website = website;
        this.capital = capital;
        this.area = area;
        this.densidade = densidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nHabitantes = nHabitantes;
        this.codigoPostal = codigoPostal;
        this.altitude = altitude;
        this.linguagens = linguagens;
        this.monumentos = monumentos;
        this.cidadesGeminadas = cidadesGeminadas;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getBandeiraPais() {
        return bandeiraPais;
    }

    public void setBandeiraPais(String bandeiraPais) {
        this.bandeiraPais = bandeiraPais;
    }

    public String getBandeiraCidade() {
        return bandeiraCidade;
    }

    public void setBandeiraCidade(String bandeiraCidade) {
        this.bandeiraCidade = bandeiraCidade;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getFuso() {
        return fuso;
    }

    public void setFuso(String fuso) {
        this.fuso = fuso;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDensidade() {
        return densidade;
    }

    public void setDensidade(double densidade) {
        this.densidade = densidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getnHabitantes() {
        return nHabitantes;
    }

    public void setnHabitantes(int nHabitantes) {
        this.nHabitantes = nHabitantes;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public ArrayList<String> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(ArrayList<String> linguagens) {
        this.linguagens = linguagens;
    }

    public ArrayList<String> getMonumentos() {
        return monumentos;
    }

    public void setMonumentos(ArrayList<String> monumentos) {
        this.monumentos = monumentos;
    }

    public ArrayList<String> getCidadesGeminadas() {
        return cidadesGeminadas;
    }

    public void setCidadesGeminadas(ArrayList<String> cidadesGeminadas) {
        this.cidadesGeminadas = cidadesGeminadas;
    }
    
    public static Cidades criaCidade(String cidade_, String pais_) throws IOException{    
        Cidades x;
        String cidade=cidade_;
        String pais=pais_;
        String bandeiraPais=Wrappers.obtemBandeiraPais(cidade,pais);
        String bandeiraCidade=Wrappers.obtemBandeiraCidade(cidade);
        String presidente=Wrappers.obtemPresidente(cidade,pais);
        String clima=Wrappers.obtemClima(cidade,pais);
        String fuso=Wrappers.obtemFuso(cidade,pais);
        String website=Wrappers.obtemWebsite(cidade,pais);
        boolean capital=Wrappers.obtemCapital(cidade,pais);
        double area=Wrappers.obtemArea(cidade,pais);
        double densidade=Wrappers.obtemDensidade(cidade,pais);
        double latitude=Wrappers.obtemLatitude(cidade,pais);
        double longitude=Wrappers.obtemLongitude(cidade,pais);
        int nHabitantes=Wrappers.obtemNHabitantes(cidade,pais);
        int codigoPostal=Wrappers.obtemCodigoPostal(cidade,pais);
        int altitude=Wrappers.obtemAltitude(cidade,pais);
        ArrayList <String> linguagens =Wrappers.obtemLinguagens(cidade,pais);
        ArrayList <String> monumentos =Wrappers.obtemMonumentos(cidade);
        ArrayList <String> cidadesGeminadas =Wrappers.obtemCidadesGeminadas(cidade,pais);
        

        x=new Cidades(cidade, pais, bandeiraPais, bandeiraCidade, presidente, clima, fuso, website, capital, area, densidade, latitude, longitude, nHabitantes, codigoPostal, altitude, linguagens,  monumentos, cidadesGeminadas);

        return x;
    }
   
    
}
