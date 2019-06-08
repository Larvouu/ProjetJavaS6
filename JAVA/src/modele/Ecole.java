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
public class Ecole {
    
    //attributs
    private int id;
    private String nom;
   
    //constructeur par défaut
    public Ecole(){};
           
    //constructeur surchargé
    public Ecole(int id, String nom)
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
