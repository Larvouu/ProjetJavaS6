/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 * Une classe est constituée d'un nom, d'une année scolaire, d'un niveau, et d'une école. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class Classe {
    
    //attributs

    private String nom;
    
    private AnneeScolaire anneeScolaire;
    private Niveau niveau;
    private Ecole ecole;
   
    //constructeur par défaut
    public Classe(){};
           
    //constructeur surchargé
    public Classe( String nom)
    {

        this.nom = nom;
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
 
        
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    
    //getters

    public String getNom(){return nom;}
    public AnneeScolaire getAnneeScolaire(){return anneeScolaire;}
    public Ecole getEcole(){return ecole;}
    public Niveau getNiveau(){return niveau;}
    
    //setters

    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setAnneeScolaire(AnneeScolaire anneeScolaire)
    {
        this.anneeScolaire = anneeScolaire;
    }
    
    public void setNiveau(Niveau niveau)
    {
        this.niveau = niveau;
    }
 
    public void setEcole(Ecole ecole)
    {
        this.ecole = ecole;
    }
    
}
