package Municipalite;

import java.util.ArrayList;
import java.util.Date;

public class Arbre{

    private final int id;
    private final String adresse;
    private final String libellefrancais;
    private final String genre;
    private final String espece;
    private final int circonference;
    private final int hauteur;
    private final String stadedeveloppement;
    private Boolean estRemarquable;
    private final String geo_point_2d;
    private ArrayList<CompteRendu> listeComptesRendus;

    public class CompteRendu
    {
        public String resume;
        public Date date;

        /**
         * Permet de créer un compte rendu.
         * @param texte le contenu du compte rendu
         * @param date la date de la visite
         */
        public CompteRendu(String texte, Date date)
        {
            this.resume = texte;
            this.date = date;
        }
    }

    /**
     * Permet de créer un arbre.
     * @param id l'id de l'arbre
     * @param genre le genre de l'arbre
     * @param espece l'espèce de l'arbre
     * @param libellefrancais le nom français
     * @param circonference la circonference de l'arbre en cm
     * @param hauteur la hauteur de l'arbre en m
     * @param stadedeveloppement le stade développement de l'arbre
     * @param adresse l'adresse de l'arbre
     * @param geo_point_2d les coordonnées GPS de l'arbre
     * @param estRemarquable si l'arbre est remarquable ou non
     */
    public Arbre(int id, String genre, String espece, String libellefrancais, int circonference, int hauteur,
                 String stadedeveloppement, String adresse, String geo_point_2d, boolean estRemarquable){
        this.id = id;
        this.genre = genre;
        this.espece = espece;
        this.libellefrancais = libellefrancais;
        this.circonference = circonference;
        this.hauteur = hauteur;
        this.stadedeveloppement = stadedeveloppement;
        this.adresse = adresse;
        this.geo_point_2d = geo_point_2d;
        this.estRemarquable = estRemarquable;
        this.listeComptesRendus = new ArrayList<>();

    }

    /**
     * Permet de récupérer le booléen estRemarquable.
     * @return le booléen
     */
    public boolean getEstRemarquable(){
        return this.estRemarquable;
    }
    /**
     * Permet de récupérer l'id de l'arbre.
     * @return l'id de l'arbre
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de récupérer le genre de l'arbre.
     * @return le genre de l'arbre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Permet de récupérer l'espèce de l'arbre.
     * @return l'espèce de l'arbre
     */
    public String getEspece() {
        return espece;
    }

    /**
     * Permet de récupérer le nom de l'arbre.
     * @return le nom de l'arbre
     */
    public String getLibellefrancais() {
        return libellefrancais;
    }

    /**
     * Permet de récupérer la circonférence de l'arbre.
     * @return la circonférence
     */
    public int getCirconference() {
        return circonference;
    }

    /**
     * Permet de récupérer la hauteur de l'arbre.
     * @return la hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Permet de récupérer le stade de développement de l'arbre.
     * @return le stade de développement de l'arbre
     */
    public String getStadedeveloppement() {
        return stadedeveloppement;
    }

    /**
     * Permet de récupérer l'adresse de l'arbre.
     * @return l'adresse de l'arbre
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Permet de récupérer les coordonnées GPS de l'arbre.
     * @return les coordonnées GPS de l'arbre
     */
    public String getGeo_point_2d() {
        return geo_point_2d;
    }

    /**
     * Permet de modifier la remarquabilité de l'arbre
     * @param estRemarquable si l'arbre est remarquable ou non
     */
    public void setEstRemarquable(Boolean estRemarquable) {
        this.estRemarquable = estRemarquable;
    }

    /**
     * Permet d'ajouter un compte rendu à la fiche de l'arbre.
     * @param resume le texte du compte rendu
     * @param date la date de la visite de l'arbre
     */
    public void ajoutCompteRendu(String resume, Date date)
    {
        CompteRendu compteRendu = new CompteRendu(resume, date);
        listeComptesRendus.add(compteRendu);
        System.out.println("L'arbre n°" + this.id + " a reçu un nouveau compte-rendu\n");
    }

    /**
     * Permet de renvoyer la liste des comptes rendus de l'arbre.
     * @return un arraylist contenant la liste des comptes rendus
     */
    public ArrayList<CompteRendu> getListeComptesRendus() {
        return listeComptesRendus;
    }
}