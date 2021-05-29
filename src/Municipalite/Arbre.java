package Municipalite;

import java.io.*;
import java.util.ArrayList;

public class Arbre{

    private int id;
    private String adresse;
    private String libellefrancais;
    private String genre;
    private String espece;
    private int circonference;
    private int hauteur;
    private String stadedeveloppement;
    private Boolean estRemarquable;
    private String geo_point_2d;

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

    }

    public boolean getEstRemarquable(){
        return this.estRemarquable;
    }
    public int getId() {
        return id;
    }
    public String getGenre() {
        return genre;
    }

    public String getEspece() {
        return espece;
    }

    public String getLibellefrancais() {
        return libellefrancais;
    }

    public int getCirconference() {
        return circonference;
    }

    public int getHauteur() {
        return hauteur;
    }

    public String getStadedeveloppement() {
        return stadedeveloppement;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGeo_point_2d() {
        return geo_point_2d;
    }
}

/*
mettre à jour pour enregistrer chaque élément
 */