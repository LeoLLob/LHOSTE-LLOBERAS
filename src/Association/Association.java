package Association;

import Municipalite.Arbre;
import ServiceEspaceVert.ServiceEspacesVerts;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Association
{
    private String nom;
    private President president;
    private ArrayList<Membre> listeMembres;
    private ArrayList<Donateur> listeDonateurs;
    private double solde;
    private double recettes;
    private double depenses;
    private ArrayList<Vote> recommandationsMembres;
    private ArrayList<Visite> visitesRemarquables;
    private StringBuilder rapportActivite;
    private int annee;
    public ArrayList<String> messagerie;
    private ArrayList<Vote> listeVoix;
    private final static double MONTANTCOTISATION = 25;

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

    private class Vote
    {
        public int id;
        public int voix;

        public Vote(int idArbre)
        {
            this.id = idArbre;
            this.voix = 1;
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
        this.listeVoix = new ArrayList<>();
    }

    public void ajoutPresident(President president)
    {
        this.president = president;
        listeMembres.add(president);
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

    public ArrayList<Vote> getRecommandationsMembres() {
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

    public static double getMONTANTCOTISATION() {
        return MONTANTCOTISATION;
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
     * @param nom Le nom du nouveau donateur à ajouter
     */
    public void ajoutDonateur(String nom)
    {
        Donateur donateur = new Donateur(nom);
        listeDonateurs.add(donateur);
    }

    /**
     * Permet de retirer un donateur de la liste des donateurs.
     * @param nom Le donateur à retirer
     */
    public void suppressionDonateur(String nom)
    {
        boolean existe = false;
        for (Donateur donateur:listeDonateurs)
        {
            if (donateur.getNom().equals(nom))
            {
                existe = true;
                System.out.println(donateur.getNom() + " a été enlevé de la liste des donateurs");
                donateur.setEstInscrit(false);
                listeDonateurs.remove(donateur);
                break;
            }
        }
        if(!existe) {
            System.out.println(nom + " n'a pas été trouvé dans la liste des donateurs");
        }
    }

    /**
     * Permet de recevoir un don de la part d'un donateur.
     * @param don Le montant du don
     * @param donateur Le donateur
     */
    public void recevoirDon(double don, Donateur donateur)
    {
        solde = solde + don;
        recettes = recettes + don;
        rapportActivite.append("Réception d'un don d'un montant de " + don + "€ de la part de " + donateur.getNom() +"\n");
    }


    /**
     * Permet de payer la facture reçue.
     * @param facture la facture à régler
     */
    public void payerFacture(double facture)
    {
        if(solde - facture >= 0)
        {
            solde = solde - facture;
            depenses = depenses + facture;
            rapportActivite.append("Reglement d'une facture d'un montant de " + facture + "€\n");
        }
    }

    /**
     * Permet de recevoir la cotisation réglée par un membre.
     * @param membre Le membre qui règle sa cotisation
     */
    public void recevoirCotisation(Membre membre)
    {
        solde = solde + MONTANTCOTISATION;
        recettes = recettes + MONTANTCOTISATION;
        rapportActivite.append(membre.getNom() + " a payé sa cotisation\n");
    }

    /**
     * Permet de défrayer un membre lors d'une visite d'arbre.
     * @param membre Le membre à défrayer
     * @param montant Le montant à régler
     */
    public void defrayer(Membre membre, double montant)
    {
        if(solde - montant >= 0)
        {
            solde -= montant;
            depenses += montant;
            rapportActivite.append("Defraiement de " + membre.getNom() + " d'un montant de " + montant + "\n");
        }
    }

    /**
     * Permet d'obtenir les recettes apportées par un membre.
     * @param membre Le membre dont on veut vérifier les cotisations
     * @return Un StringBuilder contenant les cotisations annuelles du membre
     */
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

    /**
     * Permet de vérifier la possibilité de visiter un arbre remarquable.
     * @param membre Le membre qui veut visiter l'arbre
     * @param arbre L'arbre à visiter
     * @param date La date à laquelle le membre veut visiter
     * @return Un boolean disant si oui ou non la visite est possible
     */
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

    /**
     * Permet d'afficher les derniers messages reçus sur la messagerie.
     */
    public void afficherNouveauxMessages()
    {
        if(messagerie.isEmpty())
        {
            System.out.println("Aucun nouveau message n'a été reçu\n");
        }
        else
        {
            for (String message:messagerie)
            {
                System.out.println(message);
            }
            // Réinitialisation messagerie
            messagerie = new ArrayList<>();
        }
    }

    /**
     * Permet d'afficher le rapport d'activité actuel
     */
    public void afficherRapport()
    {
        System.out.println(rapportActivite);
    }


    /**
     * Permet de mettre fin à l'exercice budgétaire en cours
     * @param serviceEspacesVerts Le service gérant la mise à jour des arbres remarquables
     */
    public void finExerciceBudgetaire(ServiceEspacesVerts serviceEspacesVerts)
    {
        for (Membre membre:listeMembres)
        {
            if(!membre.getNom().equals("Personne ayant quitté l'association"))
            {
                if(!membre.getAPaye())
                {
                    // Radiation des membres n'ayant pas payé leur cotisation
                    System.out.println(membre.getNom() + " n'a pas payé sa cotisation et ne fait donc plus" +
                            " partie de l'association " + this.nom + "\n");
                    this.suppressionMembre(membre);
                }

                // Prise en compte des votes des membres
                for (int voteMembre:membre.getListeVotes())
                {
                    boolean estDejaNomine = false;
                    if (voteMembre != 0)
                    {
                        // Vérification dans la liste des votes déjà enregistrés
                        for (Vote vote:listeVoix)
                        {
                            if (vote.id == voteMembre)
                            {
                                vote.voix++;
                                estDejaNomine = true;
                            }
                        }
                        if (!estDejaNomine)
                        {
                            Vote newVote = new Vote(voteMembre);
                            listeVoix.add(newVote);
                        }
                        voteMembre = 0;
                    }
                }
                // Reinitialisation du nombre des visites de chaque membre
                membre.setNombreVisitesAnnuelle(0);
            }


        }

        // Dépouillage
        for (Vote vote:listeVoix)
        {
            int i = 0;
            if(!recommandationsMembres.isEmpty())
            {
                for(int j = 0; j < recommandationsMembres.size(); j++)
                {
                    if (recommandationsMembres.get(j).voix < vote.voix)
                    {
                        i = j;
                        break;
                    }
                }
                if(i != 5)
                {
                    for(int j = 4; j > i; j--)
                    {
                        recommandationsMembres.set(j, recommandationsMembres.get(j -1));
                    }
                    recommandationsMembres.set(i, vote);
                }
            }
            else
            {
                recommandationsMembres.add(vote);
            }

        }
        // Envoi de la liste finale des votes
        envoiVotesFinaux(serviceEspacesVerts);
        listeVoix = new ArrayList<>();
        recommandationsMembres = new ArrayList<>();

        // Conclusion du rapport
        rapportActivite.append("Récapitulatif de fin d'année : \n" +
                "Recettes : " + recettes + "€\n" + "Dépenses : " + depenses + "€\n" +
                "Solde : " + solde + "\n");
        System.out.println(rapportActivite);

        // Ecriture nouveau rapport pour l'année suivante
        rapportActivite = new StringBuilder("Synthèse de l'année précédente :\n" +
                "Recettes : " + recettes + "€\n" + "Dépenses : " + depenses + "€\n" +
                "Solde final : " + solde + "€\n");

        // Envoi des demandes de subventions aux donateurs
        envoiDemandesSubvention(30);

        // Réinitialisation des visites annuelles
        visitesRemarquables = new ArrayList();
    }

    /**
     * Permet l'envoi des votes au service des espaces verts.
     * @param serviceEspacesVerts Le service auquel envoyer la liste des votes
     */
    public void envoiVotesFinaux(ServiceEspacesVerts serviceEspacesVerts)
    {
        for (Vote vote:recommandationsMembres)
        {
            serviceEspacesVerts.ajoutListeProchainsRemarquables(vote.id);
            System.out.println("L'arbre numéro " + vote.id + " a été nominé pour devenir arbre remarquable\n");
        }
    }

    /**
     * Permet d'envoyer les demandes de subventions aux donateurs enregistrés.
     * @param montant le montant des subventions que l'association demande
     */
    public void envoiDemandesSubvention(double montant)
    {
        for (Donateur donateur:listeDonateurs)
        {
            donateur.demandesSubvention.add(montant);
            System.out.println("Une demande de subvention de " + montant + "€ a été envoyée à " + donateur.getNom());
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