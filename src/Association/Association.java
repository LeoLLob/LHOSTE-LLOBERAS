package Association;

import java.util.ArrayList;

public class Association
{
    private String nom;
    private President president;
    private ArrayList<Membre> listeMembres;
    private ArrayList<Donateur> listeDonateurs;
    private double solde;
    private double recettes;
    private double depenses;
    private ArrayList<int[]> recommandationsMembres;
    private ArrayList<int[]> visitesRemarquables;
    private StringBuilder rapportActivite;
    private int annee;
    public StringBuilder messagerie;

    public Association(String nom)
    {
        this.nom = nom;
        this.solde = 1000;
        this.recettes = 0;
        this.depenses = 0;
        this.listeDonateurs = new ArrayList();
        this.listeMembres = new ArrayList();
        this.recommandationsMembres = new ArrayList();
        this.visitesRemarquables = new ArrayList();
        rapportActivite = new StringBuilder("Création association '" + nom + "' avec un solde de " + this.solde + "\n");
    }

    public void ajoutPresident(President president)
    {
        this.president = president;
        ajoutMembre(president);
    }

    public String getNom()
    {
        return nom;
    }

    /**
     * Permet d'ajouter un nouveau membre à la liste des membres.
     * @param membre Le nouveau membre à ajouter
     */
    public void ajoutMembre(Membre membre)
    {
        listeMembres.add(membre);
    }

    /**
     * Permet de retirer un membre de la liste des membres.
     * @param membre Le membre à retirer
     */
    public void suppressionMembre(Membre membre)
    {
        for (Membre memb:listeMembres)
        {
            if (memb.getNom() == membre.getNom())
            {
                memb.effacerDonneesPerso();
            }
        }
    }

    /**
     * Permet d'ajouter un nouveau donateur à la liste des donateurs.
     * @param donateur Le nouveau donateur à ajouter
     */
    public void ajoutDonateur(Donateur donateur)
    {
        listeDonateurs.add(donateur);
    }

    public void suppressionDonateur(Donateur donateur)
    {
        for (Donateur dona:listeDonateurs)
        {
            if (dona.getNom() == donateur.getNom())
            {
                listeDonateurs.remove(dona);
                break;
            }
        }
    }

    public void payerFacture(double facture)
    {
        if(solde - facture >= 0)
        {
            solde -= facture;
            depenses += facture;
            rapportActivite.append("Reglement d'une facture d'un montant de " + facture + "\n");
        }
    }

    public void defrayer(Membre membre, int montant)
    {
        if(solde - montant >= 0)
        {
            solde -= montant;
            depenses += montant;
            rapportActivite.append("Defraiement de " + membre.getNom() + " d'un montant de " + montant + "\n");
        }
    }

    public StringBuilder recetteMembre(Membre membre)
    {
        StringBuilder cotisation = new StringBuilder("Cotisations annuelles de " + membre.getNom() + " :\n");
        for (String montant:membre.getCotisation())
        {

        }

        return cotisation;
    }





}

/*
créer méthode :
fin ex budgétaire et tout ce qu'elle implique
toutes les actions décrites page 4
envoi liste des 5 arbres votés
demander don à liste donateurs

créer attributs:
nom asso
nom président
solde
liste membres
liste donateurs
recette (à incrémenter à chaque modif)
dépense (à incrémenter à chaque modif)
StringBuilder avec historique des dépenses/ recettes (rapport d'activité) avec au début une synthèse de l'exercice précédent
array des recommandations
array visites programmées

s'inscrire à la liste du service des espaces verts
 */