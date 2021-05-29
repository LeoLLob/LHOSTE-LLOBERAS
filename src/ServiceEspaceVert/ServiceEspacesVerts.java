package ServiceEspaceVert;

import Association.Association;
import Municipalite.Arbre;
import Municipalite.Municipalite;

import java.util.ArrayList;

public class ServiceEspacesVerts {
    private ArrayList<Association> listAssociations;

    public void notificationAbatage(int id){
        StringBuilder notification = new StringBuilder();
        notification.append("L'arbre numéro " + id + "à été abattu.");
        for(Association association: this.listAssociations) {
            association.messagerie.append(notification);
        }
    }

    public void notificationClassification(int id){
        StringBuilder notification = new StringBuilder();
        notification.append("L'arbre numéro " + id + "à été classifié.");
        for(Association association: this.listAssociations) {
            association.messagerie.append(notification);
        }
    }

    public void notificationPlantation(int id){
        StringBuilder notification = new StringBuilder();
        notification.append("Un nouvelle arbre à été planté, son numéro est le " + id + ".");
        for(Association association: this.listAssociations) {
            association.messagerie.append(notification);
        }
    }

    public ArrayList<Association> getListAssociations() {
        return listAssociations;
    }
}

