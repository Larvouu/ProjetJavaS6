/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author ghias
 */
public class Discipline {
    
     //attributs
    private String nom;
   
    //constructeur par défaut
    public Discipline(){};
           
    //constructeur surchargé
    public Discipline( String nom)
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
    
    //setters

    public void setNom(String nom)
    {
        this.nom = nom;
    }

       
}
