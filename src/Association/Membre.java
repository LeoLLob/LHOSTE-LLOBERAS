package Association;

import Municipalité.Arbre;

import java.util.ArrayList;

public class Membre extends Association{
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String adresse;
    private String dateDePremiereInscription;
    private String[] cotisation;
    private boolean aPaye;
    private boolean estInscrit;
    private ArrayList<String> listeArbre;

    private StringBuilder infosPersos(){
        StringBuilder infosPersos = new StringBuilder();
        infosPersos.append("NOM : " + nom);
        infosPersos.append("Prenom : " + prenom);
        infosPersos.append("Date de naissance : " + dateDeNaissance);
        infosPersos.append("Adresse : " + adresse);
        infosPersos.append("Date 1ere inscripton : " + dateDePremiereInscription);
        return infosPersos;
    }


}
