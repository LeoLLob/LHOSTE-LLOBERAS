package Association;

public class President extends Membre
{
    public President(String nom, String datenaissance, String adresse, String datePremiereInscription, Association association)
    {
        super(nom, datenaissance, adresse, datePremiereInscription, association);
        super.setEstPresident(true);
    }
}
