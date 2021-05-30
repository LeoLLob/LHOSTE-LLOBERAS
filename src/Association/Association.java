package Association;

import Municipalite.Arbre;
import ServiceEspaceVert.ServiceEspacesVerts;

import java.util.ArrayList;
import java.util.Date;

public class Association
{
    private final String nom;
    private President president;
    private ArrayList<Membre> listeMembres;
    private ArrayList<Donateur> listeDonateurs;
    private double solde;
    private double recettes;
    private double depenses;
    private ArrayList<Vote> recommandationsMembres;
    private ArrayList<Visite> visitesRemarquables;
    private StringBuilder rapportActivite;
    public ArrayList<String> messagerie;
    private ArrayList<Vote> listeVoix;
    private final static double MONTANTCOTISATION = 25;

    private static class Visite
    {
        public Membre membre;
        public Arbre arbre;
        public Date date;

        /**
         * Permet de construire une nouvelle visite d'arbre remarquable.
         * @param membre Le membre qui visite
         * @param arbre l'arbre à visiter
         * @param date la date de la visite
         */
        @Deprecated
        public Visite(Membre membre, Arbre arbre, Date date)
        {
            this.membre = membre;
            this.arbre = arbre;
            this.date = new Date(date.getYear(), date.getMonth(), date.getDate());
        }
    }

    private static class Vote
    {
        public int id;
        public int voix;

        /**
         * Permet de construire un nouveau vote.
         * @param idArbre l'id de l'arbre voté
         */
        public Vote(int idArbre)
        {
            this.id = idArbre;
            this.voix = 0;
        }
    }

    /**
     * Permet de construire une association.
     * @param nom le nom de la nouvelle association
     */
    public Association(String nom)
    {
        this.nom = nom;
        this.solde = 1000;
        this.recettes = 0;
        this.depenses = 0;
        this.listeDonateurs = new ArrayList<>();
        this.listeMembres = new ArrayList<>();
        this.recommandationsMembres = new ArrayList<>();
        initialiserVotes();
        this.visitesRemarquables = new ArrayList<>();
        this.rapportActivite = new StringBuilder("Création association '" + nom + "' avec un solde de " + this.solde + "\n");
        this.messagerie = new ArrayList<>();
        this.listeVoix = new ArrayList<>();
    }

    /**
     * Permet d'ajouter un président à l'association.
     * @param president Le président à élire
     */
    public void ajoutPresident(President president)
    {
        this.president = president;
        listeMembres.add(president);
    }


    /**
     * Permet de récupérer le nom de l'association.
     * @return un String contenant le nom de l'association
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * Permet de récupérer la liste des membres de l'association
     * @return un ArrayList contenant la liste des membres de l'association
     */
    public ArrayList<Membre> getListeMembres() {
        return listeMembres;
    }

    /**
     * Permet de récupérer la liste des donateurs de l'association
     * @return un ArrayList contenant la liste des donateurs de l'association
     */
    public ArrayList<Donateur> getListeDonateurs() {
        return listeDonateurs;
    }

    /**
     * Permet de récupérer le montant de la cotisation fixé
     * @return un double contenant le montant de la contisation
     */
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
     * @param nom Le nom du membre à retirer
     */
    public void suppressionMembre(String nom)
    {
        boolean existe = false;
        for (Membre memb:listeMembres)
        {
            if (memb.getNom().equals(nom))
            {
                memb.effacerDonneesPerso();
                System.out.println("Les données personnelles de ce membre ont été effacées");
                existe = true;
                break;
            }
        }
        if(!existe)
            System.out.println("le membre renseigné n'existe pas dans l'association");
    }

    /**
     * Permet d'ajouter un nouveau donateur à la liste des donateurs.
     * @param nom Le nom du nouveau donateur à ajouter
     */
    public void ajoutDonateur(String nom)
    {
        Donateur donateur = new Donateur(nom);
        listeDonateurs.add(donateur);
        System.out.println(nom + "a été ajouté à la liste des donateurs de l'association\n");
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
        String message = "Réception d'un don d'un montant de " + don + "€ de la part de " + donateur.getNom() +"\n";
        rapportActivite.append(message);
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
            String message = "Reglement d'une facture d'un montant de " + facture + "€\n";
            rapportActivite.append(message);
        }else{
            System.out.println("Solde insuffisant !");
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
        String message = membre.getNom() + " a payé sa cotisation\n";
        rapportActivite.append(message);
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
            String message = "Defraiement de " + membre.getNom() + " d'un montant de " + montant + "\n";
            rapportActivite.append(message);
        }else{
            System.out.println("Solde insuffisant !");
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
        String message;
        for (double montant:membre.getCotisation())
        {
            message = montant + "€ ";
            cotisation.append(message);
            total+= montant;
        }
        message = "\n total = " + total + "€\n";
        cotisation.append(message);
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
                            " partie de l'association " + this.nom);
                    this.suppressionMembre(membre.getNom());
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
                            newVote.voix++;
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
        // Envoi de la liste finale des votes
        envoiVotesFinaux(serviceEspacesVerts);

        // Réinitialisation des voix
        listeVoix = new ArrayList<>();
        initialiserVotes();

        // Conclusion du rapport
        String message = "Récapitulatif de fin d'année : \n" +
                "Recettes : " + recettes + "€\n" + "Dépenses : " + depenses + "€\n" +
                "Solde : " + solde + "\n";
        rapportActivite.append(message);
        System.out.println(rapportActivite);

        // Ecriture nouveau rapport pour l'année suivante
        rapportActivite = new StringBuilder("Synthèse de l'année précédente :\n" +
                "Recettes : " + recettes + "€\n" + "Dépenses : " + depenses + "€\n" +
                "Solde final : " + solde + "€\n");

        // Envoi des demandes de subventions aux donateurs
        envoiDemandesSubvention(30);

        // Réinitialisation des visites annuelles
        visitesRemarquables = new ArrayList<>();
    }

    /**
     * Permet l'envoi des votes au service des espaces verts.
     * @param serviceEspacesVerts Le service auquel envoyer la liste des votes
     */
    public void envoiVotesFinaux(ServiceEspacesVerts serviceEspacesVerts)
    {
        for (Vote vote:recommandationsMembres)
        {
            if (vote.id != 0)
            {
                serviceEspacesVerts.ajoutListeProchainsRemarquables(vote.id);
                System.out.println("L'arbre numéro " + vote.id + " a été nominé pour devenir arbre remarquable\n");
            }
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

    /**
     * Permet de réinitialiser les votes.
     */
    public void initialiserVotes()
    {
        Vote vote = new Vote(0);
        for(int i = 0; i<5; i++)
        {
            recommandationsMembres.add(vote);
        }
    }
}