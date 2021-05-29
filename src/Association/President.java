package Association;

public class President extends Membre
{
    /**
     * Permet de créer un président.
     * @param nom le nom du président
     * @param datenaissance la date de naissance du président
     * @param adresse l'adresse du président
     * @param datePremiereInscription la date de la première inscription
     * @param association l'association que gère le président
     */
    public President(String nom, String datenaissance, String adresse, String datePremiereInscription, Association association)
    {
        super(nom, datenaissance, adresse, datePremiereInscription, association);
        super.setEstPresident(true);
        association.ajoutPresident(this);
    }
}
