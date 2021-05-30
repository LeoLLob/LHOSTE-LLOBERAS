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

    /**
     * Permet de créer une municipalité.
     * @param path le chemin d'accès au fichier .csv contenant la liste des arbres
     */
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
             int circonference = Integer.parseInt(Line[12]);
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

             Arbre arbre = new Arbre(id, genre, espece, libelleFrancais, circonference, hauteur, stadeDeDeveloppement,
                     adresse, geo_point_2d, estRemarquable);


             this.listArbres.add(arbre);

         }
         this.listArbresRemarquables = setRemarquable();
         this.listArbresNonRemarquables = setNonRemarquable();
     }

    /**
     * Permet de créer un tableau contenant les arbres remarquables.
     * @return la liste contenant les arbres remarquables
     */
    public ArrayList<Arbre> setRemarquable(){

        ArrayList<Arbre> arbresRemarquables = new ArrayList<>();

        for(Arbre arbre : this.listArbres){
            if( arbre.getEstRemarquable()){
                arbresRemarquables.add(arbre);
            }
        }
        return arbresRemarquables;
    }
    /**
     * Permet de créer un tableau contenant les arbres non remarquables.
     * @return la liste contenant les arbres non remarquables
     */
    public ArrayList<Arbre> setNonRemarquable(){

        ArrayList<Arbre  > arbresNonRemarquables = new ArrayList<>();

        for(Arbre arbre : this.listArbres){
            if( arbre.getEstRemarquable() == false){
                arbresNonRemarquables.add(arbre);
            }
        }
        return arbresNonRemarquables;
    }

    /**
     * Permet de récupérer le tableau contenant les arbres.
     * @return la liste contenant les arbres
     */
    public ArrayList<Arbre> getListArbres() {
        return listArbres;
    }

    /**
     * Permet de récupérer le tableau contenant les arbres remarquables.
     * @return la liste contenant les arbres remarquables
     */
    public ArrayList<Arbre> getListArbresRemarquables() {
        return listArbresRemarquables;
    }

    /**
     * Permet récupérer le tableau contenant les arbres non remarquables.
     * @return la liste contenant les arbres non remarquables
     */
    public ArrayList<Arbre> getListArbresNonRemarquables() {
        return listArbresNonRemarquables;
    }

    /**
     * Définit la méthode toString de la liste contenant les arbres.
     * @param arbres le tableau des arbres
     */
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

    /**
     * Permet de planter un nouvel arbre.
     * @param serviceEspacesVerts le service qui envoie la notification
     * @param id l'id du nouvel arbre à ajouter
     * @param genre le genre de l'arbre
     * @param espece l'espèce de l'arbre
     * @param libellefrancais le nom de l'arbre
     * @param circonference la circonference de l'arbre en cm
     * @param hauteur la hauteur de l'arbre en m
     * @param adresse l'adresse de l'arbre
     * @param geo_point_2d les coordonnées GPS de l'arbre
     */

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

    /**
     * Permet d'abattre un arbre.
     * @param serviceEspacesVerts le service qui envoie la notification
     * @param id l'id de l'arbre à abattre
     */
    public void abattage(ServiceEspacesVerts serviceEspacesVerts, int id){
        for(Arbre arbre:this.listArbres){
            if(arbre.getId() == id && arbre.getEstRemarquable()){
                this.listArbres.remove(arbre);
                this.listArbresRemarquables.remove(arbre);
                break;
            }else if(arbre.getId() == id && !arbre.getEstRemarquable()){
                this.listArbres.remove(arbre);
                this.listArbresNonRemarquables.remove(arbre);
                break;
            }
        }
        serviceEspacesVerts.notificationAbattage(id);
    }

    /**
     * Permet de classifier un arbre.
     * @param serviceEspacesVerts le service qui envoie la notification
     * @param id l'id de l'arbre à classifier
     */
    public void classification(ServiceEspacesVerts serviceEspacesVerts, int id){
        for(Arbre arbre:this.listArbres){
            if(arbre.getId() == id){
                arbre.setEstRemarquable(true);
                this.listArbresNonRemarquables.remove(arbre);
                this.listArbresRemarquables.add(arbre);
                break;
            }
        }
        serviceEspacesVerts.notificationClassification(id);
    }

    public void accederCommentaireArbre(int idArbre)
    {
        boolean existe = false;
        for (Arbre arbre:listArbresRemarquables)
        {
            if(arbre.getId() == idArbre)
            {
                existe = true;
                for(int i = 0; i < arbre.getListeComptesRendus().size(); i++)
                {
                    System.out.println("Rédigé le " + arbre.getListeComptesRendus().get(i).date + "\n");
                    System.out.println(arbre.getListeComptesRendus().get(i).resume + "\n");
                }
                if(arbre.getListeComptesRendus().size() == 0)
                {
                    System.out.println("Cet arbre ne possède pas de compte-rendu.");
                }
                break;
            }
        }
        if(!existe)
        {
            System.out.println("Cet arbre n'existe pas ou n'est pas remarquable\n");
        }
    }

}




