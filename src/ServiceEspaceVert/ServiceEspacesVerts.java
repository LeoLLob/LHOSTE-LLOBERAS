package ServiceEspaceVert;

import Association.Association;
import Municipalite.Arbre;
import Municipalite.Municipalite;

import java.util.ArrayList;

public class ServiceEspacesVerts {
    private ArrayList<Association> listAssociations;
    private ArrayList<Integer> listeProchainsRemarquables;

    /**
     * Permet de créer un service espaces verts.
     */
    public ServiceEspacesVerts(){
        this.listAssociations = new ArrayList();
        this.listeProchainsRemarquables = new ArrayList();
    }

    /**
     * Permet d'envoyer une notification d'abattage à l'association.
     * @param id l'id de l'arbre abattu
     */
    public void notificationAbattage(int id){
        String notification;
        notification = ("L'arbre numéro " + id + " à été abattu.");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    /**
     * Permet d'envoyer une notification de classification d'arbre.
     * @param id l'id de l'arbre classifié
     */
    public void notificationClassification(int id){
        String notification;
        notification = ("L'arbre numéro " + id + " à été classifié.");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    /**
     * Permet d'envoyer une notification de plantation d'arbre.
     * @param id l'id de l'arbre planté
     */
    public void notificationPlantation(int id){
        String notification;
        notification = ("Un nouvelle arbre à été planté, son numéro est le " + id + ".");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    /**
     * Permet le traitement des arbres en attente de classification
     */
    public void traitementNouveauxRemarquables(Municipalite municipalite)
    {
        for (int id:listeProchainsRemarquables)
        {
            municipalite.classification(this, id);
            listeProchainsRemarquables.remove(id);
        }
    }

    /**
     * Permet de récupérer la liste des associations liées au service.
     * @return La liste des associations
     */
    public ArrayList<Association> getListAssociations() {
        return listAssociations;
    }

    /**
     * Permet d'ajouter un arbre à la liste des futurs arbres remarquables à traiter.
     * @param idArbre l'id de l'arbre à classifier
     */
    public void ajoutListeProchainsRemarquables(int idArbre) {
        this.listeProchainsRemarquables.add(idArbre);
    }
}

