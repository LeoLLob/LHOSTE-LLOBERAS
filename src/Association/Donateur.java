package Association;

import java.util.ArrayList;

public class Donateur
{
    private String nom;
    private ArrayList<Association> listeAssociations;


    public String getNom()
    {
        return nom;
    }

    public void inscriptionAsso(Association association)
    {
        boolean inscrit = false;
        for (Association asso:listeAssociations)
        {
            if(asso.getNom() == association.getNom())
            {
                inscrit = true;
                break;
            }
        }
        if (!inscrit)
        {
            listeAssociations.add(association);
            association.ajoutDonateur(this);
        }
    }

    public void desincriptionAsso(Association association)
    {
        for (Association asso:listeAssociations)
        {
            if (asso.getNom() == association.getNom())
            {
                asso.suppressionDonateur(this);
                listeAssociations.remove(asso);
            }
        }
    }

}


/*
nature

inscription liste donateurs
desinscription liste donateurs
 */
