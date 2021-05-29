package Association;

import Municipalite.Arbre;
import Municipalite.Municipalite;

import java.util.Date;

public class Membre
{
    private String nom;
    private String dateDeNaissance;
    private String adresse;
    private String dateDePremiereInscription;
    private double[] cotisation;
    private boolean aPaye;
    private int[] listeVotes;
    private Association association;
    private boolean estPresident;

    public Membre(String nom, String datenaissance, String adresse, String datePremiereInscription, Association association)
    {
        this.nom = nom;
        this.dateDeNaissance = datenaissance;
        this.adresse = adresse;
        this.dateDePremiereInscription = datePremiereInscription;
        this.association = association;
        this.estPresident = false;
        this.aPaye = false;
        this.listeVotes = new int[5];
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

    public double[] getCotisation() {
        return cotisation;
    }

    public boolean getAPaye() {
        return aPaye;
    }

    public int[] getListeVotes() {
        return listeVotes;
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

    public boolean demanderVisite(Arbre arbre, int annee, int mois, int jour)
    {
        @Deprecated
        Date date = new Date(annee, mois, jour);
        boolean rep = this.association.ajoutVisiteProgrammee(this, arbre, date);
        if(rep)
        {
            this.association.defrayer(this, 1.90);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void ecritureCompterendu(String compteRendu, Arbre arbre, int annee, int mois, int jour)
    {
        @Deprecated
        Date date = new Date(annee, mois, jour);
        arbre.ajoutCompteRendu(compteRendu, date);
    }

    public void proposerVote(int vote, Municipalite ville)
    {
        boolean estRemarquable = false;
        for (Arbre arbre: ville.getListArbres())
        {
            if(vote == arbre.getId())
            {
                if(arbre.getEstRemarquable())
                {
                    System.out.println("L'arbre pour lequel vous votez est déjà remarquable");
                    break;
                }
                else
                {
                    for(int i = 1; i<4 ; i++)
                    {
                        listeVotes[i-1] = listeVotes[i];
                    }
                    listeVotes[4] = vote;
                    System.out.println("Votre vote a été pris en compte\n");
                    break;
                }
            }
        }
        System.out.println("L'arbre "+ vote + " n'existe pas");
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
