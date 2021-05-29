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
        ArrayList<String[] > arbresTemp = new ArrayList<>();
        ArrayList<String[] > arbres = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while((line = bufferedReader.readLine()) != null){
                arbre = line.split(";");
                arbresTemp.add(arbre);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        for(String[] fullLine: arbresTemp){
            String[] reducedLine = new String[10];
            reducedLine[0] = fullLine[0];
            reducedLine[1] = fullLine[9];
            reducedLine[2] = fullLine[10];
            reducedLine[3] = fullLine[8];
            reducedLine[4] = fullLine[12] + " cm";
            reducedLine[5] = fullLine[13] + " m";
            reducedLine[6] = fullLine[14];
            reducedLine[7] = fullLine[4] + " " + fullLine[6] + " " + fullLine[3];
            reducedLine[8] = fullLine[16];
            reducedLine[9] = fullLine[15];
            arbres.add(reducedLine);
        }


        return arbres;
    }


    public static ArrayList<String[]> getRemarquable(ArrayList<String[]> arbres){
        ArrayList<String[] > arbresRemarquables = new ArrayList<>();
        for(String[] line: arbres){
            if(line[9] == ""){
                arbresRemarquables.add(line);
            }
        }
        return arbresRemarquables;
    }

    public static ArrayList<String[]> getNonRemarquable(ArrayList<String[]> arbres){
        ArrayList<String[] > arbresNonRemarquables = new ArrayList<>();
        for(String[] line: arbres){
            if(line[9] != ""){
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
        arbre.toString(arbres);


    }


}
