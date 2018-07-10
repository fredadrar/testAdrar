package com.premier.fred.fredpremierandroid;

import java.util.Random;

public class EleveBean {
    private static final String[] tabImg = new String[]{
            "https://pixabay.com/static/uploads/photo/2014/04/02/17/02/girl-307747_960_720.png",
            "http://clamart-lafontaine-blog.e-monsite.com/medias/images/eleve.png",
            "http://ekladata.com/2NvmX2GdczA71ZMewxFwyR9CesE@350x586.png"
    };
    private static final Random rand = new Random();

    private String nom, prenom, url;

    public EleveBean(String nom, String prenom) {

        this.nom = nom;
        this.prenom = prenom;
        this.url = tabImg[rand.nextInt(tabImg.length)];
    }

    public String getNom() {
        return nom;
    }

    public String getUrl() {
        return url;
    }

    public String getPrenom() {
        return prenom;
    }
}
