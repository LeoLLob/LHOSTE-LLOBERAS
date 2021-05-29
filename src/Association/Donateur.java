package Association;

import java.util.ArrayList;
import java.util.Scanner;

public class Donateur
{
    private String nom;
    private boolean estInscrit;
    public ArrayList<double> demandesSubvention;

    /**
     * Permet de créer un donateur.
     * @param nom Le nom du donateur
     */
    public Donateur(String nom)
    {
        this.nom = nom;
        this.demandesSubvention = new ArrayList();
        this.estInscrit = true;
    }

    /**
     * Permet de récupérer le nom du donateur.
     */
    public String getNom()
    {
        return nom;
    }
    /**
     * Permet de changer l'état d'inscription du donateur.
     * @param estInscrit le boolean renseignant l'état d'inscription
     */
    public void setEstInscrit(boolean estInscrit)
    {
        this.estInscrit = estInscrit;
    }

    /**
     * Permet d'effectuer un don à une association.
     * @param association L'association qui reçoit le don
     * @param don Le montant à donner
     */
    public void effectuerDon(Association association, double don)
    {
        association.recevoirDon(don, this);
        System.out.println("Vous venez de donner " + don + "€ à l'association " + association.getNom() + "\n");
    }

    /**
     * Permet de traiter les subventions reçues.
     * @param association L'association qui demande les subventions
     */
    public void traiterSubventions(Association association)
    {
        for (double demande:demandesSubvention)
        {
            Scanner entree = new Scanner(System.in);
            System.out.println("Voulez-vous payer " + demande + "€ à l'association " + association.getNom() + " ?\n" +
                    " Oui -> oui\n" +
                    " Non -> non\n");

            String rep = entree.nextLine();
            if(rep.equals("oui"))
            {
                effectuerDon(association, demande);
                System.out.println("Vous avez payé " + demande + "€ à l'association " + association.getNom() + "\n");
                demandesSubvention.remove(demande);
            }
            else if (rep.equals("non"))
            {
                System.out.println("Vous avez refusé la demande de l'association " + association.getNom() + "\n");
                demandesSubvention.remove(demande);
            }
        }
    }

}


/*
nature

inscription liste donateurs
desinscription liste donateurs
 */
