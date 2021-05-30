import Association.*;
import Municipalite.*;
import ServiceEspaceVert.ServiceEspacesVerts;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Municipalite paris = new Municipalite("src/les-arbres.csv");
        ServiceEspacesVerts serviceEspacesVertsDeParis = new ServiceEspacesVerts();
        Association viveLesArbres = new Association("Vive les Arbres");
        serviceEspacesVertsDeParis.getListAssociations().add(viveLesArbres);
        President Mathis = new President("Mathis", "21/07/2000", "Fresnes", "29/05/2021", viveLesArbres);
        viveLesArbres.ajoutMembre("Leo", "16/05/2000", "Paris", "30/05/2021");
        boolean quitter = false;

        while (!quitter) {
            Scanner entree = new Scanner(System.in);
            System.out.println("Qui êtes vous?\n" +
                    " Association -> asso\n" +
                    " Membre -> membre\n" +
                    " Paris -> paris\n" +
                    " Donateur -> donateur\n" +
                    " Quitter -> quitter");

            String qui = entree.nextLine();
            boolean menu = false;

            if (qui.equals("membre")) {
                while (!menu) {
                    System.out.println("Membre\n" +
                            "Comment vous appelez vous ?\n" +
                            " Menu Principal -> menu");

                    String nom = entree.nextLine();
                    if (nom.equals("menu")) {
                        menu = true;
                    } else {
                        for (Membre membre : viveLesArbres.getListeMembres()) {
                            if (membre.getNom().equals(nom)) {
                                while (!menu) {
                                    System.out.println("Que voulez-vous faire ?\n" +
                                            " Information Personnelles -> infos\n" +
                                            " Se desinscrire de l'association -> desinscrire\n" +
                                            " Payer cotisation -> cotisation\n" +
                                            " Reserver visite -> visite\n" +
                                            " Liste des arbres remarquables -> remarquable\n" +
                                            " Liste des arbres non remarquables -> non remarquable\n" +
                                            " Votez pour classification -> vote\n" +
                                            " Menu Principal -> menu");
                                    String quoi = entree.nextLine();

                                    if (quoi.equals("menu")) {
                                        menu = true;

                                    } else if (quoi.equals("infos")) {
                                        membre.infosPersos();
                                        System.out.println();
                                    } else if (quoi.equals("desinscrire")) {
                                        System.out.println("Desinscription");
                                        viveLesArbres.suppressionMembre(membre.getNom());
                                    } else if (quoi.equals("cotisation")) {
                                        System.out.println("Payer cotisation");
                                        membre.payerCotisation();

                                    } else if (quoi.equals("visite")) {
                                        System.out.println("Reserver visite\n" +
                                                "Quel arbre voulez-vous visiter ?\n" +
                                                " Saisir id");
                                        int id = Integer.parseInt(entree.nextLine());
                                        System.out.println("Saisi de la date\n" +
                                                "année ?");
                                        int annee = Integer.parseInt(entree.nextLine());
                                        System.out.println("Saisi de la date\n" +
                                                "mois ? (en chiffre)");
                                        int mois = Integer.parseInt(entree.nextLine());
                                        System.out.println("Saisi de la date\n" +
                                                "jour ? ");
                                        int jour = Integer.parseInt(entree.nextLine());
                                        for (Arbre arbre : paris.getListArbresRemarquables()) {
                                            if (arbre.getId() == id) {
                                                membre.demanderVisite(arbre, annee, mois, jour);
                                                System.out.println("\n Vous avez visité l'arbre n°" + id +
                                                        ". Veuillez écrire votre compte-rendu");

                                                String rendu = entree.nextLine();
                                                membre.ecritureCompterendu(rendu, arbre, annee, mois, jour);

                                            }
                                        }

                                    } else if (quoi.equals("remarquable")) {
                                        paris.toString(paris.getListArbresRemarquables());
                                    }  else if (quoi.equals("non remarquable")) {
                                        paris.toString(paris.getListArbresNonRemarquables());
                                    }else if (quoi.equals("vote")) {
                                        System.out.println("Proposer vote\n" +
                                                "Quel arbre voulez-vous proposer ?\n" +
                                                "id ?");
                                        int id = Integer.parseInt(entree.nextLine());
                                        membre.proposerVote(id, paris);
                                        membre.getListeVotes().toString();
                                    }

                                }
                            }
                        }
                    }
                }
            } else if (qui.equals("paris")) {
                while (!menu) {
                    System.out.println("Paris\n" +
                            "Que voulez-vous faire ?\n" +
                            " Planter un arbre -> planter\n" +
                            " Abattre un arbre -> abattre\n" +
                            " Classifier un arbre -> classifier\n" +
                            " Classifier arbres proposés -> propositions\n" +
                            " Menu Principal -> menu");
                    String quoiP = entree.nextLine();

                    if (quoiP.equals("planter")) {
                        System.out.println("Planter un arbre\n" +
                                "id ?");
                        int id = Integer.parseInt(entree.nextLine());
                        System.out.println("genre ?");
                        String genre = entree.nextLine();
                        System.out.println("espece ?");
                        String espece = entree.nextLine();
                        System.out.println("libelle ?");
                        String libelleFrancais = entree.nextLine();
                        System.out.println("circonference en cm ?");
                        int circonference = Integer.parseInt(entree.nextLine());
                        System.out.println("hauteur en m ?");
                        int hauteur = Integer.parseInt(entree.nextLine());
                        System.out.println("adresse ?");
                        String adresseArbre = entree.nextLine();
                        System.out.println("Coordonnees ?");
                        String coord = entree.nextLine();
                        paris.plantation(serviceEspacesVertsDeParis, id, espece, genre, libelleFrancais, circonference,
                                hauteur, adresseArbre, coord);
                        paris.toString(paris.getListArbres());
                    } else if (quoiP.equals("abattre")) {
                        System.out.println("Abattre un arbre\n" +
                                "id ?");
                        int id = Integer.parseInt(entree.nextLine());
                        paris.abattage(serviceEspacesVertsDeParis, id);
                        paris.toString(paris.getListArbres());
                    } else if (quoiP.equals("classifier")) {
                        System.out.println("classifier un arbre\n" +
                                "id ?");
                        int id = Integer.parseInt(entree.nextLine());
                        paris.classification(serviceEspacesVertsDeParis, id);
                        paris.toString(paris.getListArbres());
                    } else if (quoiP.equals("propositions")) {
                        System.out.println("Analyse des propositions\n");
                        serviceEspacesVertsDeParis.traitementNouveauxRemarquables(paris);
                        paris.toString(paris.getListArbresRemarquables());
                    } else if (quoiP.equals("menu")) {
                        menu = true;
                    }
                }
            } else if (qui.equals("asso")) {
                while (!menu) {
                    System.out.println("Association\n" +
                            "Que voulez-vous faire ?\n" +
                            " Lancer fin exercice budgétaire -> fin\n" +
                            " Inscrire nouveau membre -> inscrire\n" +
                            " Désinscrire membre -> desinscrire\n" +
                            " Recette cotisation membre -> recette\n" +
                            " Paiement d'une facture -> paiement\n" +
                            " Ajouter un donateur -> ajout\n" +
                            " Suppression d'un donateur -> suppression\n" +
                            " Acceder à la messagerie -> messagerie\n" +
                            " Regarder compte rendu visite -> cr\n" +
                            " Consulter rapport d'activité -> rapport\n" +
                            " Menu Principal -> menu");

                    String quoiA = entree.nextLine();
                    if (quoiA.equals("fin")) {
                        System.out.println("Fin de l'exercice budgétaire actuel\n" +
                                "Compte-rendu de l'année :\n");
                        viveLesArbres.finExerciceBudgetaire(serviceEspacesVertsDeParis);
                    } else if (quoiA.equals("inscrire")) {
                        System.out.println("Inscription\n" +
                                "Nom ?");
                        String nom = entree.nextLine();
                        System.out.println("Date de naissance ?");
                        String dateDeNaissance = entree.nextLine();
                        System.out.println("Adresse ?");
                        String adresse = entree.nextLine();
                        System.out.println("Date de premiere inscription ?");
                        String datePremiereInscription = entree.nextLine();
                        viveLesArbres.ajoutMembre(nom, dateDeNaissance, adresse, datePremiereInscription);
                    } else if (quoiA.equals("desinscrire")) {
                        System.out.println("Desinscription\n" +
                                "Nom ?");
                        String nom = entree.nextLine();
                        viveLesArbres.suppressionMembre(nom);

                    } else if (quoiA.equals("recette")) {
                        System.out.println("Recette\n" +
                                "Nom ?");
                        String nom = entree.nextLine();
                        for (Membre membre : viveLesArbres.getListeMembres()) {
                            if (membre.getNom().equals(nom)) {
                                System.out.println(viveLesArbres.recetteMembre(membre));
                            }
                        }
                    } else if (quoiA.equals("paiement")) {
                        System.out.println("Paiement facture\n" +
                                "Quel est le montant à payer ?");
                        int facture = Integer.parseInt(entree.nextLine());
                        viveLesArbres.payerFacture(facture);
                    } else if (quoiA.equals("ajout")) {
                        System.out.println("Ajout donateur\n" +
                                "Nom ?");
                        String nom = entree.nextLine();
                        viveLesArbres.ajoutDonateur(nom);
                    } else if (quoiA.equals("suppression")) {
                        System.out.println("Suppression donateur\n" +
                                "Nom ?");
                        String nom = entree.nextLine();
                        viveLesArbres.suppressionDonateur(nom);

                    } else if (quoiA.equals("messagerie")) {
                        System.out.println("Messagerie");
                        viveLesArbres.afficherNouveauxMessages();
                        System.out.println();
                    } else if(quoiA.equals("cr")){
                        System.out.println("Compte-rendu\n" +
                                "De quel arbre voulez-vous le compte-rendu ?\n" +
                                "id ?");
                        int id = Integer.parseInt(entree.nextLine());
                        paris.accederCommentaireArbre(id);

                    }else if (quoiA.equals("rapport")){
                        System.out.println("Rapport d'activité\n");
                        viveLesArbres.afficherRapport();
                    }
                    else if (quoiA.equals("menu")) {
                        menu = true;
                    }
                }


            }else if(qui.equals("donateur")){
                System.out.println("Donateur\n" +
                        "Comment vous appelez vous ?\n" +
                        " Menu Principal -> menu");
                String nom = entree.nextLine();
                if (nom.equals("menu")) {
                    menu = true;
                } else {
                    for (Donateur donateur : viveLesArbres.getListeDonateurs()) {
                        if (donateur.getNom().equals(nom)) {
                            while (!menu) {
                                System.out.println("Que voulez-vous faire ?\n" +
                                        " Faire don -> don\n" +
                                        " Menu Principal -> menu");
                                String quoi = entree.nextLine();

                                if (quoi.equals("menu")) {
                                    menu = true;
                                } else if (quoi.equals("don")) {
                                    System.out.println("Faire un don");
                                    donateur.traiterSubventions(viveLesArbres);
                                }
                            }
                        }
                    }
                }
            }else if (qui.equals("quitter")) {
                    quitter = true;
            }
        }
    }
}


