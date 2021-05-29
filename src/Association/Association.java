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

    public Association(String nom, President president)
    {
        this.nom = nom;
        this.president = president;
        listeMembres = new ArrayList<>();
        listeMembres.add(this.president);
        listeDonateurs = new ArrayList<>();
        recommandationsMembres = new ArrayList<>();
        visitesRemarquables = new ArrayList<>();
        this.solde = 1000;
        this.recettes = 0;
        this.depenses = 0;
        rapportActivite = new StringBuilder("Création association '" + nom + "' avec un solde de " + this.solde + "\n");
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
            if (memb.getNom() == membre.getNom() && memb.getPrenom() == membre.getPrenom())
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
 */