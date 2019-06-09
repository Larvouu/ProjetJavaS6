/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 * Un niveau est défini par son id et son nom. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class Niveau {
    
     //attributs
    private int id;
    private String nom;
   
    //constructeur par défaut
    public Niveau(){};
           
    //constructeur surchargé
    public Niveau(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }
        
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
        

    
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    
    //getters
    public int getId(){return id;}
    public String getNom(){return nom;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    

    
}
