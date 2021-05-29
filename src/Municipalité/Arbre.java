package Municipalité;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

public class Arbre{

    private int idbase;
    private String typeemplacement;
    private String domanialite;
    private String arrondissement;
    private String complementadresse;
    private String numero;
    private String adresse;
    private String idemplacement;
    private String libellefrancais;
    private String genre;
    private String espece;
    private String varieteoucultivar;
    private String circonferenceencm;
    private String hauteurenm;
    private String stadedeveloppement;
    private String remarquable;
    private Point2D geo_point_2d;

    public static ArrayList<String[]> getArbres(String path){

        String line = "";
        String[] arbre = {""};
        ArrayList<String[] > arbres = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while((line = bufferedReader.readLine()) != null){
                arbre = line.split(";");
                arbres.add(arbre);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return arbres;
    }


    public static ArrayList<String[]> getRemarquable(ArrayList<String[]> arbres){

        ArrayList<String[] > arbresRemarquables = new ArrayList<>();
        for(String[] line: arbres){
            if(line[15] == ""){
                arbresRemarquables.add(line);
            }
        }
        return arbresRemarquables;
    }

    public static ArrayList<String[]> getNonRemarquable(ArrayList<String[]> arbres){
        ArrayList<String[] > arbresNonRemarquables = new ArrayList<>();
        for(String[] line: arbres){
            if(line[15] != ""){
                arbresNonRemarquables.add(line);
            }
        }
        return arbresNonRemarquables;
    }

    public void toString(ArrayList<String[]> arbres){
        int i = 0;
        for(String[] line: arbres) {
            for (String string : line) {
                System.out.print(string + "; ");
            }
            System.out.println();
            i++;
        }
        System.out.println(i);
    }



    public static void main(String[] args){
        Arbre arbre = new Arbre();
        ArrayList<String[]>  arbres = Arbre.getArbres("les-arbres.csv");
        ArrayList<String[]>  arbresRemarquables = Arbre.getRemarquable(arbres);
        ArrayList<String[]>  arbresNonRemarquables = Arbre.getNonRemarquable(arbres);
       arbre.toString(arbresNonRemarquables);


    }


}
