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

    public void infosPersos(){
        StringBuilder infosPersos = new StringBuilder();
        infosPersos.append("NOM : " + nom);
        infosPersos.append("\nPrenom : " + prenom);
        infosPersos.append("\nDate de naissance : " + dateDeNaissance);
        infosPersos.append("\nAdresse : " + adresse);
        infosPersos.append("\nDate 1ere inscripton : " + dateDePremiereInscription);
        System.out.println(infosPersos);
    }


}

/*
méthodes :
envoyer liste recommandation arbres remarquables (ajouter id pour pouvoir modifier)
modifier liste de recommandation
demander visite
 */
