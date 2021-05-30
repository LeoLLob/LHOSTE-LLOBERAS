package Association;

import Municipalite.Arbre;
import Municipalite.Municipalite;

import java.util.ArrayList;
import java.util.Date;

public class Membre
{
    private final static int VISITESMAX = 12;
    private String nom;
    private String dateDeNaissance;
    private String adresse;
    private String dateDePremiereInscription;
    private ArrayList<Double> cotisation;
    private boolean aPaye;
    private int[] listeVotes;
    private final Association association;
    private boolean estPresident;
    private int nombreVisitesAnnuelle;

    /**
     * Permet de créer un membre.
     * @param nom Le nom du membre
     * @param datenaissance la date de naissance du membre
     * @param adresse l'adresse du membre
     * @param datePremiereInscription la date à laquelle s'est inscrit le membre
     * @param association l'association du membre
     */
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
        this.nombreVisitesAnnuelle = 0;
        this.cotisation = new ArrayList<>();
    }

    /**
     * Permet l'affichage des infos personnelles du membre.
     */
    public void infosPersos(){
        StringBuilder infosPersos = new StringBuilder();
        infosPersos.append("NOM : " + nom);
        infosPersos.append("\nDate de naissance : " + dateDeNaissance);
        infosPersos.append("\nAdresse : " + adresse);
        infosPersos.append("\nDate 1ere inscripton : " + dateDePremiereInscription);
        System.out.println(infosPersos);
    }

    /**
     * Permet de récupérer le nom du membre.
     * @return Le String contenant le nom du membre
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * Permet de récupérer les cotisations effectuées par le membre.
     * @return Le tableau des cotisations du membre
     */
    public ArrayList<Double> getCotisation() {
        return cotisation;
    }

    /**
     * Permet de récupérer le booléen renseignant si le membre a payé.
     * @return le booléen renseignant si le membre a payé
     */
    public boolean getAPaye() {
        return aPaye;
    }

    /**
     * Permet de récupérer la liste des votes du membre.
     * @return l'array contenant la liste des votes
     */
    public int[] getListeVotes() {
        return listeVotes;
    }

    /**
     * Permet de changer l'état du membre en président.
     * @param estPresident le booléen renseignant l'état du membre.
     */
    public void setEstPresident(boolean estPresident)
    {
        this.estPresident = estPresident;
    }

    /**
     * Permet de définir le nombre de visites annuelles.
     * @param nombreVisitesAnnuelle le nouveau nombre.
     */
    public void setNombreVisitesAnnuelle(int nombreVisitesAnnuelle) {
        this.nombreVisitesAnnuelle = nombreVisitesAnnuelle;
    }

    /**
     * Permet de payer sa cotisation.
     */
    public void payerCotisation()
    {
        if(!aPaye)
        {
            association.recevoirCotisation(this);
            aPaye = true;
            cotisation.add(Association.getMONTANTCOTISATION());
            System.out.println("Vous venez de régler votre cotisation\n");
        }
        else
            System.out.println("Vous avez déjà réglé votre cotisation\n");
    }


    /**
     * Permet d'effacer les données personnelles du membre.
     */
    public void effacerDonneesPerso()
    {
        nom = "Personne ayant quitté l'association";
        dateDeNaissance = "";
        adresse = "";
        dateDePremiereInscription = "";
    }

    /**
     * Permet de demander une visite d'arbre.
     * @param arbre l'arbre que le membre veut visiter
     * @param annee l'année de la visite
     * @param mois le mois de la visite
     * @param jour le jour de la visite
     */
    public void demanderVisite(Arbre arbre, int annee, int mois, int jour)
    {
        @Deprecated
        Date date = new Date(annee, mois, jour);
        boolean rep = this.association.ajoutVisiteProgrammee(this, arbre, date);
        if(rep && nombreVisitesAnnuelle < VISITESMAX)
        {
            double montant = 5;
            this.association.defrayer(this, 5);
            nombreVisitesAnnuelle++;
            System.out.println("La visite a été validée, l'association vous défraie à hauteur de " + montant + "€\n");
        }
        else
        {
            if (nombreVisitesAnnuelle > VISITESMAX)
                System.out.println("La visite a été refusée, vous avez déjà réalisé vos " + VISITESMAX +
                        " visites annuelles\n");
            else
                System.out.println("La visite a été refusée\n");
        }
    }

    /**
     * Permet d'envoyer le compte rendu d'une visite à la fiche de l'arbre.
     * @param compteRendu le compte rendu rédigé
     * @param arbre l'arbre concerné
     * @param annee l'année de la visite
     * @param mois le mois de la visite
     * @param jour le jour de la visite
     */
    public void ecritureCompterendu(String compteRendu, Arbre arbre, int annee, int mois, int jour)
    {
        @Deprecated
        Date date = new Date(annee, mois, jour);
        arbre.ajoutCompteRendu(compteRendu, date);
    }

    /**
     * Permet de proposer un vote pour un arbre que l'on souhaite voir remarquable.
     * @param vote l'id de l'arbre que l'on veut voter pour
     * @param ville la municipalité tenant la liste des arbres
     */
    public void proposerVote(int vote, Municipalite ville)
    {
        boolean existe = false;
        for (Arbre arbre: ville.getListArbres())
        {
            if(vote == arbre.getId())
            {
                existe = true;
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
        if(!existe) {
            System.out.println("L'arbre " + vote + " n'existe pas");
        }
    }
}