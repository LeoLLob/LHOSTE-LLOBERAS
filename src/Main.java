import Association.*;
import Municipalite.Municipalite;
import ServiceEspaceVert.ServiceEspacesVerts;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Municipalite paris = new Municipalite("les-arbres.csv");
        ServiceEspacesVerts serviceEspacesVertsDeParis = new ServiceEspacesVerts();
        Association viveLesArbres = new Association("Vive les Arbres");
        serviceEspacesVertsDeParis.getListAssociations().add(viveLesArbres);
        President Mathis = new President("Mathis", "21/07/2000", "Tour Eiffel", "29/05/2021", viveLesArbres);
        boolean quitter = false;

        while (!quitter) {
            Scanner entree = new Scanner(System.in);
            System.out.println("Qui êtes vous?\n" +
                    " Association -> asso\n" +
                    " Membre -> membre\n" +
                    " Paris -> paris\n" +
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
                                            " Information Personnelles -> infos\n"+
                                            " Menu Principal -> menu");
                                    String quoi = entree.nextLine();

                                    if (quoi.equals("menu")) {
                                        menu = true;

                                    } else if (quoi.equals("infos")) {
                                        membre.infosPersos();
                                        System.out.println();
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
                                " Classifier un arbre -> classifier\n " +
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
                            String adresse = entree.nextLine();
                            System.out.println("Coordonnees ?");
                            String coord = entree.nextLine();
                            paris.plantation(serviceEspacesVertsDeParis, id, espece, genre, libelleFrancais, circonference,
                                    hauteur, adresse, coord);
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
                            paris.abattage(serviceEspacesVertsDeParis, id);
                            paris.toString(paris.getListArbres());
                        }
                        if (quoiP.equals("menu")) {
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
                                " Menu Principal -> menu");

                        String quoiA = entree.nextLine();
                        if (quoiA.equals("fin")) {
                            viveLesArbres.finExerciceBudgetaire();
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
                            for (Membre membre : viveLesArbres.getListeMembres()) {
                                if (membre.getNom().equals(nom)) {
                                    viveLesArbres.suppressionMembre(membre);
                                }
                            }
                        } else if (quoiA.equals("recette")) {
                            System.out.println("Desinscription\n" +
                                    "Nom ?");
                            String nom = entree.nextLine();
                            for (Membre membre : viveLesArbres.getListeMembres()) {
                                if (membre.getNom().equals(nom)) {
                                    viveLesArbres.recetteMembre(membre);
                                }
                            }
                        } else if (quoiA.equals("paiment")) {
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
                            for (Donateur donateur : viveLesArbres.getListeDonateurs()) {
                                if (donateur.getNom().equals(nom)) {
                                    viveLesArbres.suppressionDonateur(donateur);
                                }
                            }
                        } else if (quoiA.equals("messagerie")) {
                            System.out.println("Messagerie");
                            viveLesArbres.afficherNouveauxMessages();
                        } else if (quoiA.equals("menu")) {
                            menu = true;
                        }
                    }


                } else if (qui.equals("quitter")) {
                    quitter = true;
                }
            }
        }
    }


