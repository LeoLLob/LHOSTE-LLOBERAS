package Association;

import java.util.ArrayList;

public class Membre
{
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String adresse;
    private String dateDePremiereInscription;
    private String[] cotisation;
    private boolean aPaye;
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

    public String getNom()
    {
        return nom;
    }

    public String getPrenom()
    {
        return prenom;
    }


    public void effacerDonneesPerso()
    {
        nom = "personne ayant quitté l'association";
        prenom = "";
        dateDeNaissance = "";
        adresse = "";
        dateDePremiereInscription = "";
    }

}

/*
méthodes :
envoyer liste recommandation arbres remarquables (ajouter id pour pouvoir modifier)
modifier liste de recommandation
demander visite
payer cotisation à son asso

nom asso
 */
