package ServiceEspaceVert;

import Association.Association;
import Municipalite.Arbre;
import Municipalite.Municipalite;

import java.util.ArrayList;

public class ServiceEspacesVerts {
    private ArrayList<Association> listAssociations;

    public ServiceEspacesVerts(){
        this.listAssociations = new ArrayList<>();
    }

    public void notificationAbatage(int id){
        String notification;
        notification = ("L'arbre numéro " + id + " à été abattu.");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    public void notificationClassification(int id){
        String notification;
        notification = ("L'arbre numéro " + id + " à été classifié.");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    public void notificationPlantation(int id){
        String notification;
        notification = ("Un nouvelle arbre à été planté, son numéro est le " + id + ".");
        for(Association association: this.listAssociations) {
            association.messagerie.add(notification);
        }
    }

    public ArrayList<Association> getListAssociations() {
        return listAssociations;
    }
}

