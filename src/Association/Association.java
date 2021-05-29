package Association;

import Municipalite.Arbre;

import java.util.ArrayList;
import java.util.Date;

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
    private ArrayList<Visite> visitesRemarquables;
    private StringBuilder rapportActivite;
    private int annee;
    public ArrayList<String> messagerie;

    private class Visite
    {
        public Membre membre;
        public Arbre arbre;
        public Date date;

        @Deprecated
        public Visite(Membre membre, Arbre arbre, Date date)
        {
            this.membre = membre;
            this.arbre = arbre;
            this.date = new Date(date.getYear(), date.getMonth(), date.getDate());
        }
    }


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
        this.rapportActivite = new StringBuilder("Création association '" + nom + "' avec un solde de " + this.solde + "\n");
        this.messagerie = new ArrayList<>();
    }

    public void ajoutPresident(President president)
    {
        this.president = president;
    }

    public String getNom()
    {
        return nom;
    }

    public ArrayList<Membre> getListeMembres() {
        return listeMembres;
    }

    public ArrayList<Donateur> getListeDonateurs() {
        return listeDonateurs;
    }

    public ArrayList<int[]> getRecommandationsMembres() {
        return recommandationsMembres;
    }

    public ArrayList<Visite> getVisitesRemarquables() {
        return visitesRemarquables;
    }

    public double getSolde() {
        return solde;
    }

    public President getPresident() {
        return president;
    }


    /**
     * Permet d'ajouter un nouveau membre à la liste des membres.
     * @param nom Le nouveau membre à ajouter
     * @param dateNaissance La date de naissance du membre
     * @param adresse l'adresse du membre
     * @param datePremiereInscription date de son inscription
     */
    public void ajoutMembre(String nom, String dateNaissance, String adresse, String datePremiereInscription)
    {
        Membre membre = new Membre(nom, dateNaissance, adresse, datePremiereInscription, this);
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

    public void defrayer(Membre membre, double montant)
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
        double total = 0;
        for (double montant:membre.getCotisation())
        {
            cotisation.append(montant + "€ ");
            total+= montant;
        }
        cotisation.append("\n total = " + total + "€\n");
        return cotisation;
    }

    public boolean ajoutVisiteProgrammee(Membre membre, Arbre arbre, Date date)
    {
        Visite newVisite = new Visite(membre, arbre, date);
        boolean dejaVisite = false;
        for (Visite visite:visitesRemarquables)
        {
            if(visite.arbre.getId() == arbre.getId())
            {
                return false;
            }
        }
            visitesRemarquables.add(newVisite);
            return true;
    }

    public void afficherNouveauxMessages()
    {
        if(messagerie == null)
        {
            System.out.println("pas de nouveau message");
        }
        else
        {
            for (String message:messagerie)
            {
                System.out.println(message);
            }
            messagerie = new ArrayList<>();
        }
    }

    public void finExerciceBudgetaire()
    {
        for (Membre membre:listeMembres)
        {
            if(!membre.getAPaye())
            {
                System.out.println(membre.getNom());
                listeMembres.remove(membre);
            }
        }
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