/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 * Une année scolaire est composée d'un seul attribut : la valeur de l'année. Cette classe n'a pas de méthode propre à elle.
 * @author ghias
 */
public class AnneeScolaire {
    
    //attributs
    private int id;
   
    //constructeur par défaut
    public AnneeScolaire(){};
           
    //constructeur surchargé
    public AnneeScolaire(int id)
    {
        this.id = id;
    }
    
    //////////////////////////////////
    // --------- METHODES --------- //
    //////////////////////////////////
    
    
    
    
    
    
    //////////////////////////////////
    // ------- METHODES FIN ------- //
    //////////////////////////////////
    
    //getters
    public int getId(){return id;}
    
    //setters
    public void setId(int id)
    {
        this.id = id;
    }
    
  
}
