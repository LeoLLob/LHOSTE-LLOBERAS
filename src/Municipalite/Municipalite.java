package Municipalite;

import ServiceEspaceVert.ServiceEspacesVerts;

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
         this.listArbresRemarquables = setRemarquable();
         this.listArbresNonRemarquables = setNonRemarquable();
     }

    public ArrayList<Arbre> setRemarquable(){

        ArrayList<Arbre  > arbresRemarquables = new ArrayList<>();

        for(Arbre arbre : this.listArbres){
            if( arbre.getEstRemarquable()){
                arbresRemarquables.add(arbre);
            }
        }
        return arbresRemarquables;
    }

    public ArrayList<Arbre> setNonRemarquable(){

        ArrayList<Arbre  > arbresNonRemarquables = new ArrayList<>();

        for(Arbre arbre : this.listArbres){
            if( arbre.getEstRemarquable() == false){
                arbresNonRemarquables.add(arbre);
            }
        }
        return arbresNonRemarquables;
    }

    public ArrayList<Arbre> getListArbres() {
        return listArbres;
    }

    public ArrayList<Arbre> getListArbresRemarquables() {
        return listArbresRemarquables;
    }

    public ArrayList<Arbre> getListArbresNonRemarquables() {
        return listArbresNonRemarquables;
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

    public void plantation(ServiceEspacesVerts serviceEspacesVerts, int id, String genre, String espece, String libellefrancais,
                           int circonference, int hauteur, String adresse, String geo_point_2d)
    {
         for(Arbre arbre:this.listArbres){
             if(arbre.getId() == id){
                 System.out.println("ID déja existant");
                 break;
             }else{
                 Arbre nouvelArbre = new Arbre(id, genre, espece, libellefrancais, circonference, hauteur,
                         "Jeune (arbre)", adresse, geo_point_2d, false);

                 this.listArbres.add(nouvelArbre);
                 this.listArbresNonRemarquables.add(nouvelArbre);
                 serviceEspacesVerts.notificationPlantation(id);
                 break;
             }
         }

     }

    public void abattage(ServiceEspacesVerts serviceEspacesVerts, int id){
        for(Arbre arbre:this.listArbres){
            if(arbre.getId() == id){
                this.listArbres.remove(arbre);
                break;
            }
        }
        this.setRemarquable();
        this.setNonRemarquable();
        serviceEspacesVerts.notificationAbatage(id);
    }

    public void classification(ServiceEspacesVerts serviceEspacesVerts, int id){
        for(Arbre arbre:this.listArbres){
            if(arbre.getId() == id){
                arbre.setEstRemarquable(true);
                break;
            }
        }
        this.setRemarquable();
        this.setNonRemarquable();
        serviceEspacesVerts.notificationClassification(id);
    }

}




