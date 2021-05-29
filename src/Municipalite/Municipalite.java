package Municipalite;

import Association.Association;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Municipalite {
    ArrayList<Arbre> listArbres;
    ArrayList<Arbre> listArbresRemarquables;
    ArrayList<Arbre> listArbresNonRemarquables;

     public Municipalite(String path){

         String line = "";
         String[] infosArbre = {};
         ArrayList<String[] > arbres = new ArrayList<>();
         try {
             BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
             while((line = bufferedReader.readLine()) != null){
                 infosArbre = line.split(";");
                 arbres.add(infosArbre);
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }catch (IOException e) {
             e.printStackTrace();
         }

         arbres.remove(0);
         this.listArbres = new ArrayList();

         for(String[] Line : arbres){
             int id = Integer.parseInt(Line[0]);
             String genre = Line[9];
             String espece = Line[10];
             String libelleFrancais = Line[8];
             int circunference = Integer.parseInt(Line[12]);
             int hauteur = Integer.parseInt(Line[13]);
             String stadeDeDeveloppement = Line[14];
             String adresse = Line[4] + " " + Line[6] + " " + Line[3];
             String geo_point_2d = Line[16];
             boolean estRemarquable;
             if(Line[15] == ""){
                 estRemarquable = true;
             }else{
                 estRemarquable = false;
             }

             Arbre arbre = new Arbre(id, genre, espece, libelleFrancais, circunference, hauteur, stadeDeDeveloppement,
                     adresse, geo_point_2d, estRemarquable);


             this.listArbres.add(arbre);

         }
         this.listArbresRemarquables = getRemarquable(this.listArbres);
         this.listArbresNonRemarquables = getNonRemarquable(this.listArbres);
     }

    public ArrayList<Arbre> getRemarquable(ArrayList<Arbre> listArbres){

        ArrayList<Arbre  > arbresRemarquables = new ArrayList<>();

        for(Arbre arbre : listArbres){
            if( arbre.getEstRemarquable()){
                arbresRemarquables.add(arbre);
            }
        }
        return arbresRemarquables;
    }

    public ArrayList<Arbre> getNonRemarquable(ArrayList<Arbre> listArbres){

        ArrayList<Arbre  > arbresNonRemarquables = new ArrayList<>();

        for(Arbre arbre : listArbres){
            if( arbre.getEstRemarquable() == false){
                arbresNonRemarquables.add(arbre);
            }
        }
        return arbresNonRemarquables;
    }

    public void toString(ArrayList<Arbre> arbres){
        StringBuilder  afficheArbres = new StringBuilder();
        for(Arbre arbre: arbres) {
            afficheArbres.append(arbre.getId() + "; " + arbre.getGenre() + "; " + arbre.getEspece() + "; " +
                    arbre.getLibellefrancais() + "; " + arbre.getCirconference() + "; " + arbre.getHauteur() + "; " +
                    arbre.getStadedeveloppement() + "; " + arbre.getAdresse() + "; " + arbre.getGeo_point_2d() + "; " +
                    arbre.getEstRemarquable() + "\n");
        }
        System.out.println(afficheArbres);
    }

   /* public void plantation(Municipalite municipalité, int id, String genre, String espece, String libellefrancais, int circonference, int hauteur,
                           String adresse, String geo_point_2d){
         Arbre nouvelArbre = new Arbre(id, genre, espece, libellefrancais, circonference, hauteur,
                 "Jeune (arbre)", adresse, geo_point_2d, false);

        municipalité.listArbres.add(nouvelArbre);
        municipalité.listArbresNonRemarquables.add(nouvelArbre);
        SeviceEspaceVert.notifcationPlantation(SeviceEspaceVert.listAssossiations associations, Arbre arbre);
    }
*/

    public static void main(String[] args){
        Municipalite paris = new Municipalite("les-arbres.csv");
        paris.toString(paris.listArbresNonRemarquables);

    }

}




