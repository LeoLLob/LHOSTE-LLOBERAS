package Association;

import java.util.ArrayList;

public class Membre
{
    private String nom;
    private String dateDeNaissance;
    private String adresse;
    private String dateDePremiereInscription;
    private String[] cotisation;
    private boolean aPaye;
    private ArrayList<String> listeArbre;
    private Association association;
    private boolean estPresident;

    public Membre(String nom, String datenaissance, String adresse, String datePremiereInscription, Association association)
    {
        this.nom = nom;
        this.dateDeNaissance = datenaissance;
        this.adresse = adresse;
        this.dateDePremiereInscription = datePremiereInscription;
        this.association = association;
        this.association.ajoutMembre(this);
        this.estPresident = false;
        this.aPaye = false;
    }

    public void infosPersos(){
        StringBuilder infosPersos = new StringBuilder();
        infosPersos.append("NOM : " + nom);;
        infosPersos.append("\nDate de naissance : " + dateDeNaissance);
        infosPersos.append("\nAdresse : " + adresse);
        infosPersos.append("\nDate 1ere inscripton : " + dateDePremiereInscription);
        System.out.println(infosPersos);
    }

    public String getNom()
    {
        return nom;
    }

    public void setEstPresident(boolean estPresident)
    {
        this.estPresident = estPresident;
    }

    public void effacerDonneesPerso()
    {
        nom = "personne ayant quitté l'association";
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
